package Controlador;

import Vista.Dibuixar;

import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Ahorcado {
    public boolean errorCreation; //Serà true en el cas de que s'hagi introduït malament la dificultat i el numero de jugadors
    private Dibuixar vista = new Dibuixar();
    private int nJugadors; //minim 1 jugador, maxim 4 jugadors
    private int videsDisponibles;

    private int torn;
    boolean fiPartida;

    private String paraulaMisteriosa;
    private char paraulaMisteriosaArray[];

    private int midaParaulaMisteriosa;
    private char espaisDesxifrats[]; //Espais de la paraula que han sigut desxifrats (______) || (A_____) || (A____B)

    private char lletresDisponibles[]; //Si la lletra es un 0 significa que ja l'hem utilitzat

    private int nivellDificultat; //Baix = 5 lletres | Mig = 7 lletres | Alt = 10 lletres

    public Ahorcado(int numJugadors, int nivellDificultat) {
        if (((numJugadors > 0) && (numJugadors < 5) && (nivellDificultat > 0) && (nivellDificultat < 4))) {
            this.nJugadors = numJugadors;
            this.nivellDificultat = nivellDificultat;

            //Inicialitzem amb les lletres de l'abecedari:
            //  .Aquí omplim els espais de lletresDisponibles amb les lletres de l'abecedari sense la ñ, que e suna lletra que no podrem utilitzar).
            this.lletresDisponibles = new char[26];
            this.inicialitzaLletresDisponibles();

            //Inicialitzem nivell de dificultat:
            //  .Aquí es genera el nombre d'espais de la paraula misteriosa).
            this.generarMidaParaulaMisteriosa(nivellDificultat);

            //Inicialitzem vides de la partida (segons el nivell de dificultat)
            this.generarVidesPartida(nivellDificultat);

            //Incialitzem els espaisDesxifrats (inicialment sera tot ______):
            //  .Aquí es generen els espais ______.
            this.espaisDesxifrats = new char[midaParaulaMisteriosa];
            this.generarEspaisParaulaMisteriosa();

            this.torn = 1;
            this.fiPartida = false;

            this.errorCreation = false;
        }
        else {
            this.errorCreation = true;
            if (numJugadors > 4 || numJugadors < 1)
                vista.errorNombreJugadors();

            if (nivellDificultat > 3 || nivellDificultat < 1)
                vista.errorDificultat();
        }
    }

    //Getters
    public char[] getLLetresDisponibles() { return this.lletresDisponibles;}
    public char[] getEspaisDesxifratsArrayChar() { return this.espaisDesxifrats; }
    public String getEspaisDesxifrats() { return new String(this.espaisDesxifrats); }
    public int getNivellDificultat() { return this.nivellDificultat; }
    public int getVidesDisponibles() { return this.videsDisponibles; }
    public char[] getParaulaMisteriosaArray() { return  this.paraulaMisteriosaArray; }
    public int getTorn() { return  this.torn; }

    //Inicialitza les lletres disponibles a escollir (amb l'abecedari sense ñ)
    public void inicialitzaLletresDisponibles() {
        for (int i = 0; i < 26; i++)
            lletresDisponibles[i] = (char) (65 + i);
    }

    //Genera els espais (___) amb la longitud de la paraula misteriosa
    public void generarEspaisParaulaMisteriosa() {
        for (int i = 0; i < midaParaulaMisteriosa; i++)
            espaisDesxifrats[i] = '_';
    }

    public void generarMidaParaulaMisteriosa(int dificultat){
        if (dificultat == 1)
            midaParaulaMisteriosa = 5;
        else if(dificultat == 2)
            midaParaulaMisteriosa = 7;
        else if(dificultat == 3)
            midaParaulaMisteriosa = 10;
    }

    //generar vides
    public void generarVidesPartida(int dificultat) {
        if (dificultat == 1 )
            videsDisponibles = 8;
        else if (dificultat == 2)
            videsDisponibles = 5;
        else if (dificultat == 3)
            videsDisponibles = 3;
    }

    //Introdueix una paraula a la paraula misteriosa, es a dir, que introdueix la paraula a introduir
    public int assignarParaulaMisteriosa(String paraula) {
        char [] paraulaArray  = paraula.toCharArray();

        //Comprovació de que la paraula misteriosa esta en majuscula
        boolean lletraMinuscula = false;
        int i = 0;
        while((i < paraula.length()) && !lletraMinuscula) {
            if (paraulaArray[i] < 'A' || paraulaArray[i] > 'Z')
                lletraMinuscula = true;
            else
                i++;
        }

        if (lletraMinuscula == true) {
            vista.errorCaracterNoValid();
            return -1;
        }
        else {
            this.paraulaMisteriosa = paraula;
            this.paraulaMisteriosaArray = paraulaArray;
            return 0;
        }
    }

    //Comprova si la lletra introduida per l'usuari forma part de la paraula misteriosa. L'afegeix, o resta vida.
    public boolean comprovaLletraCorrecta(char lletra) {
        if ((lletra >= 65) && (lletra <= 90))
            return true;
        else
            return false;
    }

    public boolean comprovaCaracterNoUtilitzat(char caracter) {
        boolean caracterNoUtilitzat = false;
        for (int i = 0; i < lletresDisponibles.length; i++) {
            if (lletresDisponibles[i] == caracter) {
                caracterNoUtilitzat = true;
            }
        }
        return caracterNoUtilitzat;
    }

    public boolean introduirParaula(String paraula) {
        boolean paraulaEncertada = false;
        if (paraula.equals(paraulaMisteriosa)) {
            vista.guanyador(torn);
            paraulaEncertada = true;
            return paraulaEncertada;
        }
        else {
            vista.errorParaulaNoCorrecta(paraula);
            videsDisponibles -= 1;
            return paraulaEncertada;
        }
    }

    public int introduirLletra(char lletra) {
        boolean caracterNoUtilitzat = false;
        for (int i = 0; i < lletresDisponibles.length; i++) {
            if (lletresDisponibles[i] == lletra) {
                caracterNoUtilitzat = true;
            }
        }

        //Comprova el ASCII
        if (comprovaLletraCorrecta(lletra)) {
            int i = 0;
            boolean trobada = false;
            while (!trobada && i < paraulaMisteriosa.length()) {
                if (paraulaMisteriosaArray[i] == lletra) { //si troba lletra cridem al escriu lletra.
                    //Afegim a espais desxifrats la lletra ficada.
                    colocaLletra(lletra);

                    //Eliminem lletra de lletres disponibles perquè l'usuari no pugui tornar-la a utilitzar-la
                    eliminaLletraDisponible(lletra);
                    trobada = true;
                } else
                    i++;
            }

            //Restem vida si la lletra no es dins la paraula
            if (trobada == false) {
                videsDisponibles -= 1;
                eliminaLletraDisponible(lletra);

                //Notifiquem que la lletra no es dins de la paraula
                vista.errorLletraNoValida();
            }
            return 0;

        } else {
            //Cridem els dos tipus d'errors que pot haver-hi al introduir un caràcter
            if (caracterNoUtilitzat == false)
                vista.errorLletraUtilitzada();
            else
                vista.errorCaracterNoValid();

            videsDisponibles -= 1;
            return -1;
        }
    }

    //Desxifra la lletra que ha encertat l'usuari en la posició paraulaMisteriosaArray que li correspon i || exemple: _ _ _ A _ _ A
    public void colocaLletra(char lletra){
        //Desxifrem la lletra que ha ficat en el cas que sigui una lletra correcta
        for(int i = 0; i < paraulaMisteriosa.length(); i++) {
            if (paraulaMisteriosaArray[i] == lletra) {
                espaisDesxifrats[i] = lletra;
            }
        }
    }

    //Elimina de lletres disponibles la lletra utilitzada. || exemple: A B C D E '0' G H
    public void eliminaLletraDisponible(char lletra) {
        int i = 0;
        boolean trobat = false;
        while(i < 26 && !trobat) {
            if(lletresDisponibles[i] == lletra){
                lletresDisponibles[i] = '0';
                trobat = true;
            }
            else
                i++;
        }
    }

    public Boolean comprovaEstatPartida() { //nomes es cridara si escullim l opcio introduir lletra --> comprova que la paraula s'hagi completat al introduir una lletra.
        boolean partidaFinalitzada = false;
        if(videsDisponibles == 0){
            vista.perdedor();
            partidaFinalitzada = true;
            return partidaFinalitzada;
        } else{
            boolean paraulaNoCompleta = false;
            int i = 0;
            while ((!paraulaNoCompleta) && (i < paraulaMisteriosa.length())) {
                if (espaisDesxifrats[i] == '_')
                    paraulaNoCompleta = true;
                else
                    i++;
            }

            if (paraulaNoCompleta == false){
                vista.guanyador(torn);
                partidaFinalitzada = true;
            }
            return partidaFinalitzada;
        }
    }

    public void canviarTorn() {
        if (torn != nJugadors)
            torn += 1;
        else
            torn = 1;
    }

    public int escullOpcio() {
        Scanner inputUser = new Scanner(System.in);

        //Controlem que l'opció sigui corrcta
        int opcio;
        boolean sortir = false;
        do {
            //Demanem una de les dues opcions que té l'usuari
            vista.missatgeEscullOpcio();
            vista.missatgeIntroduirLletra();
            vista.missatgeIntroduirParaula();
            opcio = inputUser.nextInt();
            if (opcio != 1 || opcio != 2)
                sortir = true;
            else
                vista.errorIntroduccoOpcio();
        } while (!sortir);

        if (opcio == 2 || opcio == 1) {
            if (opcio == 1) {
                vista.missatgeIntrodueixLletra();
                char letra;
                letra = inputUser.next().charAt(0);
                this.introduirLletra(letra);
                fiPartida = this.comprovaEstatPartida();

            } else if (opcio == 2) {
                vista.missatgeIntrodueixParaula();
                String paraula;
                paraula = inputUser.next();

                //Comprovem que la paraula introduida sigui la correcta, en el cas de ser correcta, es finalitza la partida
                fiPartida = this.introduirParaula(paraula);
            }
            return 0;
        } else
            return -1;
    }


}


//toCharArray();

/*5,7,10*/
