/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aad_ejercicio1;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author a20armandocb
 */
public class ConexionBD {

    private static final String LOCAL_DB_LOCAL_RUTE = "src\\aad_ejercicio1\\db\\";
    private static final String SQLITE_DB_RUTE = "jdbc:sqlite:" + LOCAL_DB_LOCAL_RUTE + "librosSqlite.db";
    private static final String DERBY_DB_RUTE = "jdbc:derby:" + LOCAL_DB_LOCAL_RUTE + "librosApache;create=true";
//    private static final String H2_RUTE = "jdbc:h2:"+ LOCAL_DB_LOCAL_RUTE + "librosH2";
    private static final String H2_RUTE = "jdbc:h2:~/src/aad_ejercicio1/db/librosH2";
//    private static final String H2_RUTE = "jdbc:h2:Z:\\ArGit\\AD\\AD-adaptar-ADD_Ejercicio1\\src\\aad_ejercicio1\\db\\librosH2";
    private static final String HSQLDB_DB_RUTE = "jdbc:hsqldb:"+ LOCAL_DB_LOCAL_RUTE + "librosHSQLdb";
    private static int typeConnection;
    private static String dbRute = "";
    private static final String DB_USER = "root";
    private static final String DB_USER_PWD = "usbw";
    private static Connection conexion;
    private static Statement statement;

    private ConexionBD() {

    }

    /**
     * Indica el tipo de conexion para antes de iniciar la 1ª conexión
     *
     * @param type 1 = sqlite, 2 = derby, 3 = h2, 4 = hsqldb
     */
    public static void setTypeConnection(int type) {
        if (statement == null) {
            switch (type) {
                case 1:
                    dbRute = SQLITE_DB_RUTE;
                    ConexionBD.typeConnection = type;
                    System.out.println("Seleccionado SQLITE: " + dbRute);
                    break;
                case 2:
                    dbRute = DERBY_DB_RUTE;
                    ConexionBD.typeConnection = type;
                    System.out.println("Seleccionado Derby: " + dbRute);
                    break;
                case 3:
                    dbRute = H2_RUTE;
                    ConexionBD.typeConnection = type;
                    System.out.println("Seleccionado H2: " + dbRute);
                    break;
                case 4:
                    dbRute = HSQLDB_DB_RUTE;
                    ConexionBD.typeConnection = type;
                    System.out.println("Seleccionado HSQLdb: " + dbRute);
                    break;
            }
        }
    }
    public static int getTypeConnection(){
        if(ConexionBD.typeConnection > 0 ){
           return ConexionBD.typeConnection; 
        }else{
            return -1;
        }
    }
    public static Statement getStatement() throws Exception {
        if (dbRute == "") {
            throw new Exception("No type connection selected");
        }
        
        if (statement == null) {
            conectar();
        }

        return statement;
    }

    private static boolean conectar() {
        boolean resultado = true;
        try {
            Properties propiedades = new Properties();

//            propiedades.setProperty("serverTimezone", "UTC");
//            propiedades.setProperty("user", DB_USER);
//            propiedades.setProperty("password", DB_USER_PWD);
            //conexion = DriverManager.getConnection(dbRute, DB_USER, DB_USER_PWD);
            conexion = DriverManager.getConnection(dbRute, propiedades);
            statement = conexion.createStatement();
            //executeSql("USE " +  EstructuraBD.DB_NAME);
        } catch (SQLException e) {
            System.out.println("Error al realizar la conexión a la base de datos.\n" + e.toString());
            resultado = false;
        }
        return resultado;
    }

    public static boolean executeSql(String sql, String log) throws Exception{
        boolean resultado = true;
        try {
            getStatement().execute(sql);           
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + "\nStatement:\n " + sql);            
            resultado = false;
        } catch (NullPointerException e) {
            resultado = false;
        }
        return resultado;
    }

    public static boolean executeSql(String sql) throws Exception{
        return executeSql(sql, "");
    }

    public static ResultSet executeQuerySql(String sql, String log) throws Exception{
        ResultSet resultado = null;
        try {
            resultado = getStatement().executeQuery(sql);           
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + "\nStatement:\n " + sql);
            resultado = null;
        } catch (NullPointerException e) {
            resultado = null;
        }
        return resultado;
    }

    public static ResultSet executeQuerySql(String sql) throws Exception{
        return executeQuerySql(sql, "");
    }
}
