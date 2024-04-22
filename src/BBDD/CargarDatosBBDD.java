package BBDD;

import componentes.personas.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static medac_programacionbatalla.MEDAC_ProgramacionBatalla.conectarBD;

public class CargarDatosBBDD {
    Connection conectar = conectarBD();
    private Connection conection;

    public CargarDatosBBDD(){
        this.conection= conectar;
    }
    public void cargarGenerales(List<General> generalesList) {
        String sql = "INSERT INTO general (nombre, ataque, salud, defensa) VALUES (?, ?, ?, ?)";

        try {
            // Obtener un objeto PreparedStatement desde la conexión
            PreparedStatement statement = this.conectar.prepareStatement(sql);
            for (General general : generalesList) {
                statement.setString(1, general.getNombre());
                statement.setInt(2, general.getAtaque());
                statement.setInt(3, general.getSalud());
                statement.setInt(4, general.getDefensa());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


