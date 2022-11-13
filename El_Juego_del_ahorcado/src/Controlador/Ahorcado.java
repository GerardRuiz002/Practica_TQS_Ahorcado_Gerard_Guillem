package Controlador;

import Model.Jugador;
import Vista.Dibuixar;

import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Ahorcado implements Serializable{
    public boolean errorCreation; //Serà true en el cas de que s'hagi introduït malament la dificultat i el numero de jugadors
    private Dibuixar vista = new Dibuixar();
    private int videsDisponibles;

    private int nJugadors; //minim 1 jugador, maxim 4 jugadors
    private Jugador jugadors[];

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
            this.jugadors = new Jugador[nJugadors];
            this.nivellDificultat = nivellDificultat;
            this.paraulaMisteriosa = null;

            //Inicialitzem amb les lletres de l'abecedari:
            this.lletresDisponibles = new char[26];
            this.inicialitzaLletresDisponibles();

            //Aquí es genera el nombre d'espais de la paraula misteriosa).
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
            if (numJugadors > 4 || numJugadors < 1)
                vista.errorNombreJugadors();

            if (nivellDificultat > 3 || nivellDificultat < 1)
                vista.errorDificultat();

            this.errorCreation = true;
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
    public boolean getFipartida() { return this.fiPartida; }
    public int getMidaParaulaMisteriosa() { return this.midaParaulaMisteriosa; }
    public String getParaulaMisteriosa() { return  this.paraulaMisteriosa; }
    public int getTornJugadorN(int n) { return this.jugadors[n].getTornAssignat(); }
    public String getNomJugador(int n) { return this.jugadors[n].getNomJugador(); }
    public Jugador[] getJugadors()  { return this.jugadors; }

    //Setters:
    public void setVidesDisponibles(int vides) {this.videsDisponibles = vides;}
    public void setEspaisDesxifrats(char espaisDesxifrats[]) {this.espaisDesxifrats = espaisDesxifrats;}
    public void setNomJugador(String nom, int index) { this.jugadors[index].setNomJugador(nom); }
    public void setFiPartida(boolean fi) {this.fiPartida = fi;}


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

    public void assignaTornJugador() {
        for (int i = 0; i < nJugadors; i++) {
            jugadors[i] = new Jugador(i); //la i es la id que tindrà el jugador
            jugadors[i].setTornAssignat(i+1);
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
            aumentaPuntuacioParaulaCorrecta();
            vista.guanyador(torn);
            paraulaEncertada = true;
            fiPartida = true;
            return paraulaEncertada;
        }
        else {
            vista.errorParaulaNoCorrecta(paraula);
            videsDisponibles -= 1;
            return paraulaEncertada;
        }
    }

    public void aumentaPuntuacioParaulaCorrecta(){
        jugadors[torn-1].setPuntuacio(jugadors[torn-1].getPuntiacio()+1); //sumem 2 punts per haver encertat la paraula misteriosa
    }

    public int introduirLletra(char lletra) {
        boolean caracterNoUtilitzat = comprovaCaracterNoUtilitzat(lletra);

        //Comprova el ASCII
        if (comprovaLletraCorrecta(lletra) && (caracterNoUtilitzat == true)) {
            int i = 0;
            boolean trobada = false;
            while (!trobada && i < paraulaMisteriosa.length()) {
                if (paraulaMisteriosaArray[i] == lletra) { //si troba lletra cridem al coloca lletra.
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
            if (comprovaLletraCorrecta(lletra) == false)
                vista.errorCaracterNoValid();
            else if(caracterNoUtilitzat == false)
                vista.errorLletraUtilitzada();

            /*if (caracterNoUtilitzat == false)
                vista.errorLletraUtilitzada();*/

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
        } else {
            boolean paraulaNoCompleta = false;
            int i = 0;
            while ((!paraulaNoCompleta) && (i < paraulaMisteriosa.length())) {
                if (espaisDesxifrats[i] == '_')
                    paraulaNoCompleta = true;
                else
                    i++;
            }

            if (paraulaNoCompleta == false){
                aumentaPuntuacioParaulaCorrecta();
                vista.guanyador(torn);
                fiPartida = true;
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
}

