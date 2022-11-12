import Controlador.Ahorcado;
import Model.ParaulesDisponibles;
import Model.Ranking;
import Vista.Dibuixar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        inicialitzacióJoc();
    }

    public static void inicialitzacióJoc() throws IOException {
        //Creem una vista per poder fer els outputs per consola
        Dibuixar vista = new Dibuixar();

        //Creem scanner per poder agafar inputs de l'usuari
        Scanner inputUser = new Scanner(System.in);

        //Inicialitzem ranking
        Ranking ranking = new Ranking();

        boolean fiPartida = false;
        int nJugadors = 0;
        int dificultat = 0;

        //Presentació del joc

        //Donem opció de carregar partida anterior o fer una nova

        int opcioCarregaOInicia = ranking.CarregaPartidaONovaPartida();
        Ahorcado ahorcado = null;
        boolean inicialitzacioCorrecta = false;
        while (!inicialitzacioCorrecta) {
            if (opcioCarregaOInicia == 1) {
                //Inicialitzar ahorcado
                //Demanem el nombre de jugadors
                vista.missatgeIntroduirJugador();
                nJugadors = inputUser.nextInt();

                //Demanem la dificultat del joc
                vista.missatgeIntroduirDificultat();
                dificultat = inputUser.nextInt();

                //Inicialitzem ahorcado amb la dificultat i nombre de jugadors seleccionat per l'usuari
                ahorcado = new Ahorcado(nJugadors, dificultat);

                if (ahorcado.errorCreation == true) {
                    ahorcado = null;
                } else if (ahorcado.errorCreation == false) {
                    //Assignem el torn a cada jugador
                    ahorcado.assignaTornJugador();
                    for (int i = 0; i < nJugadors; i++) {
                        //Assiganem eles jugadors
                        vista.missatgeIntrodueixNom();
                        String nomJugador = inputUser.next();
                        ahorcado.setNomJugador(nomJugador, i);
                    }
                    inicialitzacioCorrecta = true;
                }
            } else if (opcioCarregaOInicia == 2) {
                //Inicialitzem un ahorcado a partir del .txt amb les dades de la partida guardada
                ahorcado = Ranking.carregaRanking("path_arxiu", Ahorcado.class);
                if (ahorcado.errorCreation == false)
                    inicialitzacioCorrecta = true;
                else
                    vista.errorCarregaArxiu();
            }
        }

        //Creem un paraulesDisponibles per obtenir la paraula misteriosa
        ParaulesDisponibles paraulesDisponibles = new ParaulesDisponibles(ahorcado.getNivellDificultat());

        if (inicialitzacioCorrecta == true) {
            //Una vegada hem seleccionat el nombre de jugadors i la dificultat correctament agafem la paraula a esbrinar de paraulesDisponibles
            ahorcado.assignarParaulaMisteriosa(paraulesDisponibles.getParaulaMisteriosa());


            while (!fiPartida) {
                vista.missatgeEscullOpcio();
                String opcio = inputUser.next();
                ahorcado.escullOpcio(opcio);
                if (ahorcado.getFipartida() == false) {
                    vista.mostrarEspaisDesxifrats(ahorcado.getEspaisDesxifrats().toString());
                    vista.mostrarVidesActuals(ahorcado.getVidesDisponibles());
                }
                ahorcado.canviarTorn();

                fiPartida = ahorcado.getFipartida();
            }

            //Guardar estat partida o no
            vista.missatgeGuardarPartida();
            int opcioFi = inputUser.nextInt();
            if (opcioFi == 1) {
                vista.missatgeSortintJoc();
            }
            else if (opcioFi == 2) {
                boolean guardatCorrectament = ranking.guardarRanking("src/GuardatPartida/estat.Dat", ahorcado);
                vista.missatgeSortintJoc();
            }

        }

    }





    /* while(dificultat < 1 || dificultat >3)
            System.out.println("Nivell de dificultat erroni. Torna a introduir el nivell de dificultat");*/
}