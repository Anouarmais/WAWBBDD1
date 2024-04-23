package BBDD;

import componentes.personas.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD;


public class CargarDatosBBDD {
    Connection conectar = conectarBD();
    private Connection conection;

    public CargarDatosBBDD(Connection connection){
        this.conection= conectar;
    }
    public void cargarGenerales(List<General> generalesList) {
        String sql = "INSERT INTO general (nombre, ataque, salud, defensa) VALUES (?, ?, ?, ?)";
        TraerDatosBBDD.obtenerGenerales();

        try {
            if(TraerDatosBBDD.getGeneralesBBDD().isEmpty()){
                PreparedStatement statement = this.conectar.prepareStatement(sql);
                for (General general : generalesList) {
                    statement.setString(1, general.getNombre());
                    statement.setInt(2, general.getAtaque());
                    statement.setInt(3, general.getSalud());
                    statement.setInt(4, general.getDefensa());
                    statement.executeUpdate();
                }
            } else {
                System.out.println("Ya hay generales de  en la base datos");
            }
            // Obtener un objeto PreparedStatement desde la conexión

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


