/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medac_programacionbatalla;

import BBDD.CargarDatosBBDD;
import BBDD.TraerDatosBBDD;
import batallas.Batalla;
import componentes.personas.General;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static BBDD.ConectarBBDD.conectarBD;

/**
 * @author danie
 */
public class MEDAC_ProgramacionBatalla {

    public static void main(String[] args) {
        Connection connection = conectarBD();
        if (connection != null) {
            Batalla batalla = new Batalla();

        } else {
            System.out.println("no se conecta");
        }


    }
}
    

 
