package aad_ejercicio1;

import java.io.*;
import java.sql.*;

/**
 *
 * @author
 */
public class AAD_Ejercicio1 {

    public static void main(String[] args) throws IOException {

        //Declaracion de conectores
        /*Connection conexion = null;*/
        Statement sentencia = null;
        ResultSet rstAux = null;

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        int cont = 0, op = 0;

        //Declaracion de driver y url
        // **** elegir de 1 a 4. ****
        do{
            
        System.out.println("Elige el tipo de conexion:"
                + "\n1. sqlite"
                + "\n2. derby"
                + "\n3. h2"
                + "\n4. hsqldb");
        op = Integer.parseInt(lee.readLine());
        
        }while(op > 4 && op < 1);
        System.out.println("Elegida opción: " + op );
        ConexionBD.setTypeConnection(op);

        try {
            sentencia = ConexionBD.getStatement();
        } catch (SQLException e) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.out.println(e.toString());
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }
        CrearTablas.crearTabla(sentencia, ConexionBD.getTypeConnection());

        do {
            System.out.println("*****************************");
            System.out.println("MENU");
            System.out.println("1. Inserción de nuevas filas");
            System.out.println("2. Borrar filas");
            System.out.println("3. Modificar filas");
            System.out.println("4. Realizar consultas");
            System.out.println("0. Salir");
            op = Integer.parseInt(lee.readLine());
            switch (op) {
                case 0:
                    System.out.println("SALIENDO");
                    break;
                case 1:
                    InsertarFilas.insertarFila(sentencia, rstAux, lee);
                    break;
                case 2:
                    BorrarFilas.borrarFila(sentencia, rstAux, lee);
                    break;
                case 3:
                    ModificarFilas.modificarFila(sentencia, rstAux, lee);
                    break;
                case 4:
                    Consultar.consulta(sentencia, rstAux, lee);
                    break;
            }
        } while (op != 0);

    }

}
