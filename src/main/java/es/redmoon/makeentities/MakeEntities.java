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

        bw.write("@Repository\n");
        bw.write("public interface " + sNombreClass + " extends CrudRepository<"+StringUtils.capitalize(tabla)+",> {\n");
        
        bw.write("\n");        
        
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
        
        String sFichero = "RestController.java";
        BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
        String sNombreClass= StringUtils.capitalize(tabla)+"Controller";
        
        // imports
        bw.write("import org.slf4j.Logger;\n");
        bw.write("import org.slf4j.LoggerFactory;\n");
        bw.write("import org.springframework.web.bind.annotation.*;\n");
        bw.write("\n");
        
        // @RestController
        bw.write("@RestController\n");
        bw.write("public class " + sNombreClass + " {\n");
        bw.write("\n");
        bw.write("private static final Logger logger = LoggerFactory.getLogger("+sNombreClass+".class);\n");
        bw.write("\n");
        bw.write("\n");
        
        /*
        @Autowired
        private final FincasRepository fincasRepository;

        public FincasController(FincasRepository fincasRepository) {
            this.fincasRepository = fincasRepository;
        }*/
        bw.write("@Autowired\n");
        bw.write("public "+sNombreClass+"("+StringUtils.capitalize(tabla)+"Repository "+tabla+"Repository) {\n");
        bw.write("this."+tabla+"Repository = "+tabla+"Repository;\n");
        bw.write("}\n");
        
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
