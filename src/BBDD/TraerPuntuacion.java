package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import static BBDD.ConectarBBDD.conectarBD;

public class TraerPuntuacion {
    private static Connection connection = conectarBD();
   private LinkedList<TraerPuntuacion> puntuaciones = new LinkedList<>();

    public  LinkedList<TraerPuntuacion> obtenerPuntuaciones() {

        String TRAERPUNTUACIONES = "SELECT NOMBRE, SALUD, EJERCITO, FECHA FROM TOPSCORE ORDER BY SALUD DESC";
        try {
            PreparedStatement statement = connection.prepareStatement(TRAERPUNTUACIONES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String nombre = resultSet.getString("NOMBRE");
                int salud = resultSet.getInt("SALUD");
                String ejercito = resultSet.getString("EJERCITO");
                LocalDate fecha = resultSet.getDate("FECHA").toLocalDate();
                System.out.println("Nombre: " + nombre + ", Salud: " + salud + ", Ejercito: " + ejercito +
                        ", fecha" + fecha);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los datos de la tabla topscore", e);
        }
        return puntuaciones;
    }





}
