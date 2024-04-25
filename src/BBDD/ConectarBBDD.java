package BBDD;

import javax.swing.*;
import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

public class ConectarBBDD {
    public static String     tablaInformacion(){
        Connection conectarrr =  conectarBD();
        String mensaje= "";

        if (conectarrr != null) {
            try {
                // Crear una sentencia SQL para contar las filas en la tabla.
                Statement statement = conectarrr.createStatement();
                String query = "SELECT COUNT(*) FROM GENERAL";

                // Ejecutar la consulta y obtener el resultado.
                ResultSet resultSet = statement.executeQuery(query);

                // Procesar los resultados.
                if (resultSet.next()) {
                    int cantidadFilas = resultSet.getInt(1);
                    if (cantidadFilas > 0) {
                        mensaje = "La tabla  general  tiene cargados los generales.Pedes saltarte la opcion b";
                    } else {
                        mensaje = "La tabla general  está vacía. Pulas el botónn b para cargar el fichero 'Generales.txt' ";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mensaje = "Error al intentar comprobar la tabla.";
            } finally {
                // Cerrar la conexión.
                try {
                    conectarrr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            mensaje = "Error de conexión a la base de datos.";
        }

        return mensaje;
    }









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

