package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import static BBDD.ConectarBBDD.conectarBD; // Importación de un método estático desde otra clase.

public class TraerPuntuacion {
    private static Connection connection = conectarBD(); // Establecer una conexión a la base de datos al inicializar la clase.
    private LinkedList<TraerPuntuacion> puntuaciones = new LinkedList<>(); // Lista para almacenar las puntuaciones recuperadas.

    // Método para obtener las puntuaciones de la base de datos y mostrarlas.
    public LinkedList<TraerPuntuacion> obtenerPuntuaciones() {
        // Consulta SQL para obtener las puntuaciones ordenadas por salud de manera descendente.
        String TRAERPUNTUACIONES = "SELECT NOMBRE, SALUD, EJERCITO, FECHA FROM TOPSCORE ORDER BY SALUD DESC";
        try {
            // Preparar la declaración SQL para obtener las puntuaciones de la base de datos.
            PreparedStatement statement = connection.prepareStatement(TRAERPUNTUACIONES);
            ResultSet resultSet = statement.executeQuery();

            // Iterar sobre el resultado y mostrar las puntuaciones.
            while (resultSet.next()) {
                String nombre = resultSet.getString("NOMBRE");
                int salud = resultSet.getInt("SALUD");
                String ejercito = resultSet.getString("EJERCITO");
                LocalDate fecha = resultSet.getDate("FECHA").toLocalDate();
                System.out.println("Nombre: " + nombre + ", Salud: " + salud + ", Ejercito: " + ejercito +
                        ", Fecha: " + fecha);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los datos de la tabla topscore", e); // Lanzar una excepción en caso de error.
        }
        return puntuaciones; // Devolver la lista de puntuaciones (que está vacía).
    }
}
