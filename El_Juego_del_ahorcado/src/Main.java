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
        vista.presentacioJoc();

        //Donem opció de carregar partida anterior o fer una nova
        String opcioCarregaOInicia = ranking.CarregaPartidaONovaPartida();
        Ahorcado ahorcado = null;
        boolean inicialitzacioCorrecta = false;
        while (!inicialitzacioCorrecta) {
            if (opcioCarregaOInicia.equals("1")) {
                //Inicialitzar ahorcado
                boolean sortir = false;
                do {
                    //Demanem el nombre de jugadors
                    vista.missatgeIntroduirJugador();
                    String nJugStr = inputUser.next();

                    if(nJugStr.equals("1") || nJugStr.equals("2") || nJugStr.equals("3") || nJugStr.equals("4")) {
                        nJugadors = Integer.parseInt(nJugStr);
                        sortir = true;
                    }
                } while (!sortir);

                sortir = false;
                do {
                    //Demanem la dificultat del joc
                    vista.missatgeIntroduirDificultat();
                    String difStr = inputUser.next();

                    if(difStr.equals("1") || difStr.equals("2") || difStr.equals("3")) {
                        dificultat = Integer.parseInt(difStr);
                        sortir = true;
                    }
                } while (!sortir);

                //Inicialitzem ahorcado amb la dificultat i nombre de jugadors seleccionat per l'usuari
                ahorcado = new Ahorcado(nJugadors, dificultat);

                if (ahorcado.errorCreation == true) {
                    ahorcado = null;
                } else if (ahorcado.errorCreation == false) {
                    //Assignem el torn a cada jugador
                    ahorcado.assignaTornJugador();
                    for (int i = 0; i < nJugadors; i++) {
                        //Assignem els jugadors
                        vista.missatgeIntrodueixNom();
                        String nomJugador = inputUser.next();
                        ahorcado.setNomJugador(nomJugador, i);
                    }
                    inicialitzacioCorrecta = true;
                }
            } else if (opcioCarregaOInicia.equals("2")) {
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
                //Controlem que l'opció sigui correcta
                boolean sortir = false;
                do {
                    //Donem l'opció d'escollir entre introduir lletra o paraula
                    vista.missatgeEscullOpcio();
                    vista.tornDe(ahorcado.getTorn());
                    String opcio = inputUser.next();

                    //Demanem una de les dues opcions que té l'usuari
                    if (opcio.equals("1") || opcio.equals("2")) {
                        if (opcio.equals("1")) {
                            vista.missatgeIntrodueixLletra();
                            char letra = inputUser.next().charAt(0);
                            ahorcado.introduirLletra(letra);
                        }
                        else if (opcio.equals("2")){
                            vista.missatgeIntrodueixParaula();
                            String paraula = inputUser.next();

                            //Comprovem que la paraula introduida sigui la correcta, en el cas de ser correcta, es finalitza la partida
                            ahorcado.introduirParaula(paraula);
                        }
                        sortir = true;
                    }
                    else
                        vista.errorIntroduccoOpcio();
                } while (!sortir);

                if (ahorcado.getFipartida() == false) {
                    vista.mostrarEspaisDesxifrats(ahorcado.getEspaisDesxifrats().toString());
                    vista.mostrarVidesActuals(ahorcado.getVidesDisponibles());
                }
                ahorcado.canviarTorn();

                fiPartida = ahorcado.getFipartida();
                if (fiPartida == true) {
                    String opcioFinal = null;
                    do {
                        //Demanem el nombre de jugadors
                        vista.escullOpcioFinal();
                        opcioFinal = inputUser.next();

                        if(opcioFinal.equals("1") || opcioFinal.equals("2"))
                            sortir = true;
                        else
                            vista.errorIntroduccoOpcio();
                    } while (!sortir);

                    //Settegem els resultats al ranking
                    ranking.setJugadors(ahorcado.getJugadors());
                    //Fem el corresponent a cada opció
                    if (opcioFinal.equals("1")) {
                        ranking.mostraRanking();
                        ahorcado.assignarParaulaMisteriosa(paraulesDisponibles.getParaulaMisteriosa());
                        ahorcado.inicialitzaLletresDisponibles();
                        ahorcado.generarEspaisParaulaMisteriosa();
                        ahorcado.generarVidesPartida(dificultat);
                        ahorcado.setFiPartida(false);
                        fiPartida = false;
                    } else if (opcioFinal.equals("2")) {
                        ranking.mostraRanking();
                    }
                }
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