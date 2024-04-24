package BBDD;

import componentes.personas.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD; // Importar un método estático de otra clase.

public class CargarDatosBBDD {
    Connection conectar = conectarBD(); // Crear una conexión a la base de datos.
    private Connection conection; // Una segunda conexión que no parece utilizarse.

    // Constructor que recibe una conexión, pero no parece usarse en el código.
    public CargarDatosBBDD(Connection connection){
        this.conection= conectar; // Asignar la conexión recibida a la conexión local, pero tiene un error de typo en el nombre de la variable.
    }

    // Método para cargar datos generales en la base de datos.
    public void cargarGenerales(List<General> generalesList) {
        String sql = "INSERT INTO general (nombre, ataque, salud, defensa) VALUES (?, ?, ?, ?)"; // Consulta SQL para insertar datos en la tabla general.

        TraerDatosBBDD.obtenerGenerales(); // Llama a un método estático de otra clase que parece no hacer nada en esta función.

        try {
            if(TraerDatosBBDD.getGeneralesBBDD().isEmpty()){ // Comprueba si la lista de generales de la base de datos está vacía.
                PreparedStatement statement = this.conectar.prepareStatement(sql); // Prepara la declaración SQL para la inserción.

                for (General general : generalesList) { // Itera sobre la lista de generales pasada como argumento.
                    statement.setString(1, general.getNombre()); // Establece el primer parámetro de la declaración SQL (nombre).
                    statement.setInt(2, general.getAtaque()); // Establece el segundo parámetro de la declaración SQL (ataque).
                    statement.setInt(3, general.getSalud()); // Establece el tercer parámetro de la declaración SQL (salud).
                    statement.setInt(4, general.getDefensa()); // Establece el cuarto parámetro de la declaración SQL (defensa).
                    statement.executeUpdate(); // Ejecuta la inserción en la base de datos.
                }
            } else {
                System.out.println("Ya hay generales en la base de datos"); // Mensaje de advertencia si ya hay generales en la base de datos.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error.
        }
    }
}
