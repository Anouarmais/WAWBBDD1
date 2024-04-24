package BBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

public class ConectarBBDD {
    // Método estático para establecer una conexión a la base de datos.
    public static Connection conectarBD() {
        // Detalles de la base de datos y credenciales de inicio de sesión.
        String bd = "waw";
        String USUARIOS = "root";
        String pass = "";
        String host = "localhost";
        String port = "3306";
        boolean connectOk = true;
        Connection connection = null;

        try {
            Calendar now = Calendar.getInstance();
            TimeZone zonahoraria = now.getTimeZone();
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el controlador JDBC para MySQL.
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + bd, USUARIOS, pass); // Establecer la conexión a la base de datos.
            connectOk = true;
            //System.out.println(connectOk);
        } catch (SQLException | ClassNotFoundException e) {
            connectOk = false;
            System.out.println(e.getMessage());
            //System.out.println(connectOk);
            if (!connectOk) {
                JOptionPane.showMessageDialog(null, "La conexión no existe, comprueba que existe la tabla"); // Mostrar un mensaje de error si la conexión falla.
            }
        }
        return connection; // Devolver la conexión, que puede ser nula si falla la conexión.
    }
}
