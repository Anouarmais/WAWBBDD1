package BBDD;

import componentes.personas.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD;

public class TopScore {
    private Connection subirScore = conectarBD();



    public TopScore(Connection connection) {
        this.subirScore = connection;
    }



    public void cargarTopScore(List<String[]> registros) {
        String sql = "INSERT INTO topscore (Nombre, Salud, Ejercito, Fecha) VALUES (?, ? ,?, ?)";

        try {
            PreparedStatement statement = this.subirScore.prepareStatement(sql);
            for (String[] registro : registros) {
                statement.setString(1, registro[0]);
                statement.setString(2, registro[1]);
                statement.setString(3, registro[2]);
                statement.setDate(4, java.sql.Date.valueOf(LocalDate.parse(registro[3])));
                statement.executeUpdate();
            }
            System.out.println("Datos insertados en la tabla topscore correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}