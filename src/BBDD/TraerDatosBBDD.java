package BBDD;

import componentes.personas.General; // Importación de la clase General.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static BBDD.ConectarBBDD.conectarBD; // Importación de un método estático desde otra clase.

public class TraerDatosBBDD {
    // Establecer una conexión a la base de datos al inicializar la clase.
    static Connection traerdesdeBBDD = conectarBD();

    // Lista estática para almacenar generales recuperados de la base de datos.
    static List<General> generalesBBDD = new ArrayList<>();

    // Método para obtener la lista de generales recuperados de la base de datos.
    public static List<General> getGeneralesBBDD() {
        return generalesBBDD;
    }

    // Método para establecer la lista de generales recuperados de la base de datos.
    public static void setGeneralesBBDD(List<General> generalesBBDD) {
        TraerDatosBBDD.generalesBBDD = generalesBBDD;
    }

    // Constructor que recibe una conexión, pero parece no usarse en el código.
    public TraerDatosBBDD(Connection traerdesdeBBDD) {
        TraerDatosBBDD.traerdesdeBBDD = traerdesdeBBDD;
    }

    // Método para obtener generales desde la base de datos y almacenarlos en la lista.
    public static List<General> obtenerGenerales() {
        String traerdeBBDDarray = "SELECT NOMBRE , ATAQUE , SALUD , DEFENSA FROM GENERAL";
        try {
            // Preparar la declaración SQL para obtener los generales de la base de datos.
            PreparedStatement statement = traerdesdeBBDD.prepareStatement(traerdeBBDDarray);
            ResultSet resultset = statement.executeQuery();

            // Iterar sobre el resultado y crear objetos General para cada registro.
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

                generalesBBDD.add(generalBBDD); // Agregar el General a la lista.
            }
            // Cerrar el ResultSet y el Statement.
            resultset.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Imprimir la traza de la excepción en caso de error.
        }
        return generalesBBDD; // Devolver la lista de generales recuperados.
    }
}
