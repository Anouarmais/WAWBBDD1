package BBDD;

import componentes.personas.General; // Importación de una clase General que parece no ser utilizada en esta clase.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate; // Importación de LocalDate, que se utiliza para manejar fechas.
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD; // Importación de un método estático desde otra clase.

public class TopScore {
    private Connection subirScore = conectarBD(); // Establecer una conexión a la base de datos al inicializar la clase.

    // Constructor que recibe una conexión, pero parece no usarse en el código.
    public TopScore(Connection connection) {
        this.subirScore = connection; // Asignación de la conexión recibida a la conexión local.
    }

    // Método para cargar registros en la tabla topscore de la base de datos.
    public void cargarTopScore(List<String[]> registros) {
        String sql = "INSERT INTO topscore (Nombre, Salud, Ejercito, Fecha) VALUES (?, ? ,?, ?)"; // Consulta SQL para insertar datos en la tabla topscore.

        try {
            PreparedStatement statement = this.subirScore.prepareStatement(sql); // Preparar la declaración SQL para la inserción.
            for (String[] registro : registros) { // Iterar sobre la lista de registros pasada como argumento.
                statement.setString(1, registro[0]); // Establecer el primer parámetro de la declaración SQL (Nombre).
                statement.setString(2, registro[1]); // Establecer el segundo parámetro de la declaración SQL (Salud).
                statement.setString(3, registro[2]); // Establecer el tercer parámetro de la declaración SQL (Ejercito).
                statement.setDate(4, java.sql.Date.valueOf(LocalDate.parse(registro[3]))); // Establecer el cuarto parámetro de la declaración SQL (Fecha).
                statement.executeUpdate(); // Ejecutar la inserción en la base de datos.
            }
            System.out.println("Datos insertados en la tabla topscore correctamente."); // Mensaje de éxito después de la inserción.
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la traza de la excepción en caso de error.
        }
    }
}
