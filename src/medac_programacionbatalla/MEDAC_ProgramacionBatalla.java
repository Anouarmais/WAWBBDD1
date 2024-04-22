/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medac_programacionbatalla;

import batallas.Batalla;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author danie
 */
public class MEDAC_ProgramacionBatalla {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String bd = "waw";
        String USUARIOS = "root";
        String pass = "";
        String host = "localhost";
        String port = "3306";
        boolean connectOk = true;
        Connection connection = null;


        try {

            Calendar now = Calendar.getInstance();
            TimeZone zonahoraria =now.getTimeZone();
            //System.out.println("jkhkhkj"+Class.forName("com.mysql.cj.jdbc.Driver"));
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port +"/" + bd, USUARIOS , pass);

            connectOk = true;
            System.out.println(connectOk);

        } catch (SQLException | ClassNotFoundException  e ) {
            // } catch (SQLException  e ) {
            connectOk = false;


            System.out.println(e.getMessage());



            System.out.println(connectOk);
            if(connectOk == false){

                JOptionPane.showMessageDialog(null , "La coneccion no existe comprueva que existe la tabla");
            }

        }

//        String test = ExploradorFicheros.leerFichero();
        Batalla batalla = new Batalla();

    }
}
    

 
