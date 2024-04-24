package BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD;

public class CargarScore {
    private Connection subirScore = conectarBD();

    public CargarScore(Connection connection) {
        this.subirScore = connection;
    }

    public void cargarTopScore(List<String[]> registros) {
        String sql = "INSERT INTO topscore (Nombre, Ejercito, Fecha) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = this.subirScore.prepareStatement(sql);
            for (String[] registro : registros) {
                statement.setString(1, registro[0]);
                statement.setString(2, registro[1]);
                statement.setDate(3, java.sql.Date.valueOf(LocalDate.parse(registro[2])));
                statement.executeUpdate();
            }
            System.out.println("Datos insertados en la tabla topscore correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}