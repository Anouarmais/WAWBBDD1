/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallas;

import componentes.personas.General;
import BBDD.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static BBDD.ConectarBBDD.conectarBD;

/**
 * <p>Clase que representa una batalla entre dos ejércitos.</p>
 *
 * @author Daniel Ojados
 * @author Daniel Romero
 * @version 1.0
 */
public class Batalla {
    LinkedList<String[]> guardarGanadores = new LinkedList<>();
    private static final int MAX_RONDAS = 5;
    private final Ejercito ejercito1;
    private final Ejercito ejercito2;
    private final ArrayList<Ronda> rondas;
    private final Random random = new Random();
    private int numRondas;
    private Ejercito ganador;

    public Batalla() {
        ejercito1 = new Ejercito();
        ejercito2 = new Ejercito();
        numRondas = 0;
        rondas = new ArrayList<>();
        luchar();
    }




    private void luchar() {
        System.out.println(Message.BATALLA_INICIO + ejercito1.getNombre() + " vs " + ejercito2.getNombre() + "!");

        Ejercito atacante;
        Ejercito defensor;

        int resAtacante = random.nextInt(1, 3);
        if (resAtacante == 1) {
            atacante = ejercito1;
            defensor = ejercito2;
        } else {
            atacante = ejercito2;
            defensor = ejercito1;
        }

        if (ejercito1.getSalud() > 0 && ejercito2.getSalud() > 0) {
            for (numRondas = 0; numRondas < MAX_RONDAS; numRondas++) {
                rondas.add(new Ronda(numRondas, atacante, defensor));
                int resultado = rondas.getLast().getResultado();
                if (resultado > 0) {
                    atacante.recibirDano(resultado);
                } else {
                    defensor.recibirDano(Math.abs(resultado));
                }

                if (chequearGanador()) {
                    Ejercito ganador = getGanador();
                    LocalDate fecha = LocalDate.now();
                    General generalGanador = ganador.obtenerGeneral();
                    String[] registrarGanador = { generalGanador.getNombre() , String.valueOf(generalGanador.getSalud()),  ganador.getNombre() , fecha.toString()};
                    guardarGanadores.add(registrarGanador);
                  /*  System.out.println("Lista de ganadores:");
                    for (String[] registro : guardarGanadores) {
                        System.out.println(Arrays.toString(registro));
                    }*/
                    if (getGanador() == ejercito1) {
                        System.out.println(System.lineSeparator() + Message.EJERCITO_GANADOR +
                                ejercito1.getNombre());
                        System.out.println("El general del ejército " + ejercito1.getNombre() + " es: " + generalGanador.getNombre() );
                        System.out.println("Tiene una salud de :" + generalGanador.getSalud());
                        System.out.println("En el  dia " + fecha.toString());
                        TopScore subirScore = new TopScore(conectarBD());
                        subirScore.cargarTopScore(guardarGanadores);
                    } else {
                        System.out.println(System.lineSeparator() + Message.EJERCITO_GANADOR +
                                ejercito2.getNombre());
                        System.out.println("El general del ejército " + ejercito2.getNombre() + " es: " + generalGanador.getNombre() +"con una salud de:" + generalGanador.getSalud());
                        TopScore subirScore = new TopScore(conectarBD());
                        subirScore.cargarTopScore(guardarGanadores);

                    }

                    break;
                }

                if (atacante == ejercito1) {
                    atacante = ejercito2;
                    defensor = ejercito1;
                } else {
                    atacante = ejercito1;
                    defensor = ejercito2;
                }
            }
        }
    }

    private boolean chequearGanador() {
        if (ejercito1.getSalud() == 0) {
            ganador = ejercito2;
            return true;
        } else if (ejercito2.getSalud() == 0) {
            ganador = ejercito1;
            return true;
        }

        return false;
    }

    public Ejercito getGanador() {
        return ganador;
    }
}
