/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.redmoon.makeentities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author antonio
 */
public class pruConn {
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static String URL = "jdbc:postgresql://192.168.99.100:5432/";
    private static String DATABASE = "regantes";
    private static String USUARIO = "regantes_prodacon";
    private static String CLAVE = "acaPCB-13";

    public pruConn(String Url, String Database, String Usuario, String Passwd) {
        pruConn.URL = Url;
        pruConn.DATABASE = Database;
        pruConn.USUARIO = Usuario;
        pruConn.CLAVE = Passwd;
    }

    public pruConn(String Database, String Usuario, String Passwd) {
        pruConn.DATABASE = Database;
        pruConn.USUARIO = Usuario;
        pruConn.CLAVE = Passwd;
    }

    public pruConn(String Usuario, String Passwd) {
        pruConn.USUARIO = Usuario;
        pruConn.CLAVE = Passwd;
    }

    /*
     * Conectar con PostgreSQL
     */
    public static Connection PGconectar() {
        try {

            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;

        }

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL + DATABASE, USUARIO, CLAVE);


        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }
        return connection;
    }    
}
