package aad_ejercicio1;

import java.sql.*;

/**
 *
 * @author
 */
public class CrearTablas {

    public static void crearTabla(Statement sentencia, int typeConnection) {
        try {
            switch (typeConnection) {
                case 1:
                    sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES"
                            + "(dniAutor VARCHAR(30) NOT NULL,"
                            + "nombre VARCHAR(30) NOT NULL,"
                            + "nacionalidad VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (dniAutor))");
                    sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS "
                            + "(idLibro INT(5) NOT NULL,"
                            + "titulo VARCHAR(30) NOT NULL,"
                            + "precio INT NOT NULL,"
                            + "autor VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (idLibro),"
                            + " FOREIGN KEY (autor) references AUTORES(dniAutor)"
                            + " ON DELETE CASCADE"
                            + " ON UPDATE CASCADE)");
                    break;
                    
                case 2:
                    //   sentencia.execute("CREATE DATABASE IF NOT EXISTS LIBRERIADANI;");
                    //   sentencia.execute("USE LIBRERIADANI;");
                    sentencia.execute("CREATE TABLE AUTORES ("
                            + " dniAutor VARCHAR(30) NOT NULL,"
                            + "nombre VARCHAR(30) NOT NULL,"
                            + "nacionalidad VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (dniAutor)"
                            + " )");
                    sentencia.execute("CREATE TABLE LIBROS "
                            + "(idLibro INT(5) NOT NULL,"
                            + "titulo VARCHAR(30) NOT NULL,"
                            + "precio INT NOT NULL,"
                            + "autor VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (idLibro),"
                            + " FOREIGN KEY (autor) references AUTORES(dniAutor)"
                            + " ON DELETE CASCADE"
                            + " ON UPDATE CASCADE)");
                    break;
                    
                case 3:
                    sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES"
                            + "(dniAutor VARCHAR(30) NOT NULL,"
                            + "nombre VARCHAR(30) NOT NULL,"
                            + "nacionalidad VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (dniAutor))");
                    sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS "
                            + "(idLibro INT(5) NOT NULL,"
                            + "titulo VARCHAR(30) NOT NULL,"
                            + "precio INT NOT NULL,"
                            + "autor VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (idLibro),"
                            + " FOREIGN KEY (autor) references AUTORES(dniAutor)"
                            + " ON DELETE CASCADE"
                            + " ON UPDATE CASCADE)");
                    break;
                    
                case 4:
                    sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES ("
                            + "dniAutor VARCHAR(30) NOT NULL,"
                            + "nombre VARCHAR(30) NOT NULL,"
                            + "nacionalidad VARCHAR(30) NOT NULL,"
                            + "PRIMARY KEY (dniAutor)"
                            + " )");
                    sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS ("
                            + "idLibro INT NOT NULL, "
                            + "titulo VARCHAR(30) NOT NULL, "
                            + "precio INT NOT NULL, "
                            + "autor VARCHAR(30) NOT NULL, "
                            + "PRIMARY KEY (idLibro),"
                            + " FOREIGN KEY (autor) references AUTORES(dniAutor)"
                            + " )");
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);

        }
    }
}
