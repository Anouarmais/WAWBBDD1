package BBDD;

import componentes.personas.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD;


public class TraerDatosBBDD {
    static Connection traerdesdeBBDD = conectarBD();

    public static List<General> getGeneralesBBDD() {
        return generalesBBDD;
    }

    public static void setGeneralesBBDD(List<General> generalesBBDD) {
        TraerDatosBBDD.generalesBBDD = generalesBBDD;
    }

    static List<General> generalesBBDD = new ArrayList<>();
    public TraerDatosBBDD(Connection traerdesdeBBDD) {
        TraerDatosBBDD.traerdesdeBBDD = traerdesdeBBDD;
    }

    public static List<General> obtenerGenerales() {

        String traerdeBBDDarray = "SELECT NOMBRE , ATAQUE , SALUD , DEFENSA FROM GENERAL";
        try {
            PreparedStatement statement = traerdesdeBBDD.prepareStatement(traerdeBBDDarray);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                General generalBBDD = new General();
                String nombre = resultset.getString("nombre");
                int ataque = resultset.getInt("ataque");
                int salud = resultset.getInt("salud");
                int defensa = resultset.getInt("defensa");
                generalBBDD.setNombre(nombre);
                generalBBDD.setAtaque(ataque);
                generalBBDD.setSalud(salud);
                generalBBDD.setDefensa(defensa);

                generalesBBDD.add(generalBBDD);
            }
            resultset.close();
            statement.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return generalesBBDD;
    }


}
