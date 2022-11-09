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

    public void provaAhorcado() {
        //Declarem el ahorcado
        Ahorcado prova = new Ahorcado(1, 1);

        //Generem paraula misteriosa
        prova.introduirParaula("RATON");

        //Imprimir espais desxifrats
        String espaisDesxifrats = prova.getEspaisDesxifrats();
        System.out.println("Espais desxifrats: " + espaisDesxifrats);

        //Introduïm lletra
        int a = prova.introduirLletra('A');

        //Treiem la lletra de les lletres disponibles:
        espaisDesxifrats = prova.getEspaisDesxifrats();
        System.out.println("Espais desxifrats: " + espaisDesxifrats);

        //Tornem a ficar la mateixa lletra
        int b = prova.introduirLletra('Z');

        //Mirar vides (han d'haver-hi 7, perquè hem introduit una lletra incorrecta)
        int numVides = prova.getVidesDisponibles();
        System.out.println("Vides disponibles: " + numVides);

        //*************completem la paraula RATON:******************************************************
        int c = prova.introduirLletra('R');
        int d = prova.introduirLletra('T');
        int e = prova.introduirLletra('O');
        int f = prova.introduirLletra('N');
        //prints:
        espaisDesxifrats = prova.getEspaisDesxifrats();
        System.out.println("Espais desxifrats: " + espaisDesxifrats);

        //**************perdem la partida:************************************************************
        int ñ = prova.introduirLletra('U');
        int v = prova.introduirLletra('L');
        int x = prova.introduirLletra('I');
        int q = prova.introduirLletra('J');
        int g = prova.introduirLletra('K');
        int h = prova.introduirLletra('Y');
        int i = prova.introduirLletra('M');
        //prints:
        espaisDesxifrats = prova.getEspaisDesxifrats();
        System.out.println("Espais desxifrats: " + espaisDesxifrats);

        int numVides2 = prova.getVidesDisponibles();
        System.out.println("Vides disponibles: " + numVides2);
        //**************************************************************************
    }

    public static ArrayList<String> provaLecturaArxiu() throws IOException {
        ParaulesDisponibles pd = new ParaulesDisponibles(1);
        ArrayList<String> paraules =  pd.llegirTxt("src/Text/facil.txt");
        return paraules;
    }

    public static void provaScanner(){
        Scanner capt = new Scanner(System.in);
        System.out.print("Ingrese numero:\t: ");
        int a = capt.nextInt();
        System.out.println("Resultat:" + a);
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

        //Inicialitzar ahorcado
        Ahorcado ahorcado = null; //Si hacemos esto en otra función, habria que pasarle este ahorcado
        boolean inicialitzacioCorrecta = false;
        while (!inicialitzacioCorrecta) {
            //Demanem el nombre de jugadors
            vista.missatgeIntroduirJugador();
            nJugadors = inputUser.nextInt();

            //Demanem la dificultat del joc
            vista.missatgeIntroduirDificultat();
            dificultat = inputUser.nextInt();

            //Donem opció de carregar partida anterior o fer una nova
            int opcioCarregaOInicia = ranking.CarregaPartidaONovaPartida();
            if (opcioCarregaOInicia == 2) {
                //Carregar partida
                nJugadors = ;
                dificultat = ;
                //puntuacio de cada jugador
            }

            ahorcado = new Ahorcado(nJugadors, dificultat);

            //Inicialitzem ahorcado amb la dificultat i nombre de jugadors seleccionat per l'usuari
            if (ahorcado.errorCreation == true) {
                ahorcado = null;
            }
            else if (ahorcado.errorCreation == false) {
                inicialitzacioCorrecta = true;
            }
        }

        //Creem un paraulesDisponibles per obtenir la paraula misteriosa
        ParaulesDisponibles paraulesDisponibles = new ParaulesDisponibles(ahorcado.getNivellDificultat());

        if (inicialitzacioCorrecta == true) {
            //Una vegada hem seleccionat el nombre de jugadors i la dificultat correctament agafem la paraula a esbrinar de paraulesDisponibles
            ahorcado.assignarParaulaMisteriosa(paraulesDisponibles.getParaulaMisteriosa());

            while (!fiPartida) {



                ahorcado.escullOpcio();
                ahorcado.canviarTorn();
            }
        }

    }





    /* while(dificultat < 1 || dificultat >3)
            System.out.println("Nivell de dificultat erroni. Torna a introduir el nivell de dificultat");*/
}