
package es.redmoon.makeentities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author antonio
 */
public class MakeEntities {

    /**
     * Pasar la estructura de una tabla PostgreSQL a un objeto JSON
     * @param tabla
     * @return
     * @throws SQLException 
     */
    public JSONObject ReadBodyTable(String tabla) throws SQLException {
        
        pruConn myConn= new pruConn("regantes","regantes_prodacon","acaPCB-13");
        Connection conn=myConn.PGconectar();
        
         PreparedStatement st = conn.prepareStatement("SELECT DISTINCT table_name, column_name,data_type,ordinal_position\n" +
                                "FROM information_schema.columns\n" +
                                "WHERE table_schema='public'\n" +
                                "AND table_name = ?\n" +
                                "ORDER BY 4");
         st.setString(1, tabla);
         ResultSet rs = st.executeQuery();
         
         //Gson obj=new Gson();
         JSONObject obj=new JSONObject();
         
        while (rs.next()) {
            
            obj.put(rs.getString("column_name"),rs.getString("data_type"));
            
        }
        conn.close();
        //System.out.print(obj);
        return obj;
    }
    
    
    /**
     * Crea un fichero con la clase de las tuplas de una tabla PostgreSQL
     * @param obj
     * @param tabla
     * @throws IOException 
     */
    public void MakeEntityClass(JSONObject obj, String tabla) throws IOException
    {
        String sFichero = "tuplas.java";

        String Tipo;

        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
        
        // escribir la cabecera de la clase
        bw.write("import lombok.Data;\n");
        bw.write("import java.io.Serializable;\n");
        bw.write("import javax.persistence.Entity;\n");
        bw.write("import javax.persistence.Id;\n");
        bw.write("\n");
        bw.write("\n");
        
        bw.write("@Data\n");
        bw.write("@Entity\n");
        bw.write("public class " + StringUtils.capitalize(tabla) + " implements Serializable {");
        bw.write("\n");
        
        // bloque de declaración de variables private final
        
        for (Object key : obj.keySet()) {
            //System.out.println(obj.get(key).toString());

            switch (obj.get(key).toString()) {
                case "character varying":
                    Tipo="String";
                    break;
                case "text":
                    Tipo="String";
                    break;
                case "character":
                    Tipo="String";
                    break;
                case "timestamp without time zone":
                    Tipo="timestamp";
                    break;
                default:
                    Tipo=obj.get(key).toString();
            }
            bw.write("private "+Tipo+" "+key.toString()+";\n");
        }
        bw.write("\n");
                
        // cerrar el pie de la clase
        bw.write("}\n");
        bw.close();
    }
    
    
    /**
     * Generate file for Repository
     * @param obj
     * @param tabla
     * @throws IOException 
     */
    public void MakeRepository(JSONObject obj, String tabla) throws IOException
    {
        String sFichero = "Repository.java";
        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
        String sNombreClass= StringUtils.capitalize(tabla)+"Repository";
        
        bw.write("import org.springframework.data.repository.CrudRepository;\n");
        bw.write("import org.springframework.data.repository.query.Param;\n");
        bw.write("import org.springframework.stereotype.Repository;\n");
        bw.write("\n");

        
        bw.write("public interface " + sNombreClass + " extends CrudRepository<"+StringUtils.capitalize(tabla)+"> {\n");
        
        bw.write("\n");
        
        // declaración de variables
        for (Object key : obj.keySet()) {
            bw.write("private String "+key.toString()+";\n");
        }
        
        bw.write("\n");
        
        // bloque setters
        for (Object key : obj.keySet()) {

            bw.write("public String set"+StringUtils.capitalize(key.toString())+"(String "+key.toString()+") {\n");
            bw.write("this."+key.toString()+"="+key.toString()+";\n");
            bw.write("}\n");
            bw.write("\n");
        }
        
        // bloque getters
        for (Object key : obj.keySet()) {

            bw.write("public String get"+StringUtils.capitalize(key.toString())+"() {\n");
            bw.write("return "+key.toString()+";\n");
            bw.write("}\n");
            bw.write("\n");
        }
        
        // cerrar el pie de la clase
        bw.write("}\n");
        bw.close();
        
    }
    
     /**
     * Rest Controller
     * @param obj con los campos de la tabla y sus tipos de datos
     * @param tabla
     * @throws IOException 
     */
    public void MakeRestController(JSONObject obj, String tabla) throws IOException{
        
        String sFichero = "sql.java";
        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
        String sNombreClass= "SQL"+StringUtils.capitalize(tabla);
        
        // public class SQLCias extends PoolConn {
        bw.write("public class " + sNombreClass + " extends PoolConn {\n");
        bw.write("\n");
        bw.write("private final String version;\n");
        bw.write("\n");
        bw.write("public "+sNombreClass+"(String myPool) throws SQLException, NamingException {\n");
        bw.write("super(myPool);\n");
        bw.write("this.version = myPool;\n");
        bw.write("}\n");
        bw.write("\n");
        
        // Crear un bloque de lista de tuplas
        bw.write("public List<Tuplas"+StringUtils.capitalize(tabla)+"> getLista"+StringUtils.capitalize(tabla)+"() throws SQLException {\n");
        bw.write("Connection conn = PGconectar();\n");
        bw.write("List<Tuplas"+StringUtils.capitalize(tabla)+"> tp = new ArrayList<>();\n");
        
        bw.write("try {\n");
         

            bw.write("PreparedStatement st = conn.prepareStatement(\"SELECT * from "+tabla+"\");\n");
            
            bw.write("ResultSet rs = st.executeQuery();\n");
        
            
            bw.write("while (rs.next()) {\n");
                
                bw.write("tp.add( new Tuplas"+StringUtils.capitalize(tabla)+".\n");
                        bw.write("Builder().\n");
                        bw.write("build()\n");
                         bw.write(");\n");
                
            bw.write("}\n");
            
        bw.write("} catch (SQLException e) {\n");

            bw.write("System.out.println(\""+tabla+" Connection Failed!\");\n");

        bw.write("} finally {\n");

            bw.write("conn.close();\n");
        bw.write("}\n");
        
        bw.write("return tp;\n");
        bw.write("}\n");
        bw.write("\n");
        
        
        
        // Crear un bloque de lista de tuplas con paginación
        bw.write("public List<Tuplas"+StringUtils.capitalize(tabla)+"> getLista"+StringUtils.capitalize(tabla)+"(int NumPage, int SizePage, String ) throws SQLException {\n");
        bw.write("Connection conn = PGconectar();\n");
        bw.write("List<Tuplas"+StringUtils.capitalize(tabla)+"> tp = new ArrayList<>();\n");
        
        bw.write("try {\n");
         

            bw.write("int Offset = SizePage * (NumPage-1);\n");
            bw.write("PreparedStatement st = conn.prepareStatement(\"SELECT * from "+tabla+" where estanque = ? order by id desc LIMIT ? OFFSET ?\");\n");
            bw.write("st.setInt(1, Integer.parseInt(xEstanque) );\n");
            bw.write("st.setInt(2, SizePage);\n");
            bw.write("st.setInt(3, Offset);\n");
            
            bw.write("ResultSet rs = st.executeQuery();\n");
        
            
            bw.write("while (rs.next()) {\n");
                
                bw.write("tp.add( new Tuplas"+StringUtils.capitalize(tabla)+".\n");
                        bw.write("Builder().\n");
                        bw.write("build()\n");
                         bw.write(");\n");
                
            bw.write("}\n");
            
        bw.write("} catch (SQLException e) {\n");

            bw.write("System.out.println(\""+tabla+" Connection Failed!\");\n");

        bw.write("} finally {\n");

            bw.write("conn.close();\n");
        bw.write("}\n");
        
        bw.write("return tp;\n");
        bw.write("}\n");
        bw.write("\n");
        
        
        bw.write("}\n");
        bw.close();
    
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        // leer la estructura de la tabla
        MakeEntities mt = new MakeEntities();
        JSONObject obj= mt.ReadBodyTable(args[0]);
        
        mt.MakeEntityClass(obj,args[0]);
        
        mt.MakeRepository(obj, args[0]);
        
        mt.MakeRestController(obj, args[0]);
        
        //System.out.print(obj.size());
        /*
        for (Object key : obj.keySet()) {
            System.out.print(key.toString() + obj.get(key));
        }*/
        
        
        
        
    }
    
    
}
