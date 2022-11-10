package Controlador;

import Vista.Dibuixar;

import java.util.*;
import java.io.*;
import java.util.Arrays;

public class Ahorcado {
    private Dibuixar vista = new Dibuixar();
    private int nJugadors;
    private int tornJugadorId;
    private int videsDisponibles;

    private String paraulaMisteriosa;
    private char paraulaMisteriosaArray[];

    private int midaParaulaMisteriosa;
    private char espaisDesxifrats[]; //Espais de la paraula que han sigut desxifrats (______) || (A_____) || (A____B)

    private char lletresDisponibles[]; //Si la lletra es un 0 significa que ja l'hem utilitzat

    private int nivellDificultat; //Baix = 5 lletres | Mig = 7 lletres | Alt = 10 lletres

    public Ahorcado(int numJugadors, int nivellDificultat) {
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

        //Incialitzem els espaisDesxifrats (inicialment sera tot ______)
        //  .Aquí es generen els espais ______.
        this.espaisDesxifrats = new char[midaParaulaMisteriosa];
        this.generarEspaisParaulaMisteriosa();
    }

    //Getters
    public char[] getLLetresDisponibles() { return this.lletresDisponibles;}
    public char[] getEspaisDesxifratsArrayChar() { return this.espaisDesxifrats; }
    public String getEspaisDesxifrats() { return new String(this.espaisDesxifrats); }
    public int getNivellDificultat() { return this.nivellDificultat; }
    public int getVidesDisponibles() { return this.videsDisponibles; }

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

    //FALTA GENERAR LA PALABRA ALEATORIAMENTE LEYENDO DEL ARCHIVO TXT SEGUN LA DIFICULTAD
    public void generarParaula() { //harcodeado
        this.paraulaMisteriosa = "RATON";
        this.paraulaMisteriosaArray = paraulaMisteriosa.toCharArray();
    }

    //Comprova si la lletra introduida per l'usuari forma part de la paraula misteriosa. L'afegeix, o resta vida.
    public int introduirLletra(char lletra) {
        boolean caracterNoUtilitzat = false;
        for (int i = 0; i < lletresDisponibles.length; i++) {
            if (lletresDisponibles[i] == lletra) {
                caracterNoUtilitzat = true;
            }
        }

        //Comprova el ASCII
        if ((lletra >= 65) && (lletra <= 90) && caracterNoUtilitzat) {
            int i = 0;
            boolean trobada = false;
            while (!trobada && i < paraulaMisteriosa.length()) {
                if (paraulaMisteriosaArray[i] == lletra) { //si troba lletra cridem al escriu lletra.
                    //Afegim a espais desxifrats la lletra ficada.
                    colocaLletra(lletra);

                    //Eliminem lletra de lletres disponibles perquè l'usuari no pugui tornar-la a utilitzar-la
                    eliminaLletraDisponible(lletra);
                    trobada = true;
                    comprovaEstatPartida(); //nou
                } else
                    i++;
            }

            //Restem vida si la lletra no es dins la paraula
            if (trobada == false) {
                videsDisponibles -= 1;
                eliminaLletraDisponible(lletra);

                //Notifiquem que la lletra no es dins de la paraula
                vista.errorLletraNoValida();
                comprovaEstatPartida(); //nou
            }

            return 0;
        } else {
            //Cridem els dos tipus d'errors que pot haver-hi al introduir un caràcter
            if (caracterNoUtilitzat == false){
                videsDisponibles -= 1;
                vista.errorLletraUtilitzada();
                comprovaEstatPartida(); //nou
            }
            else{
                videsDisponibles -= 1;
                vista.errorCaracterNoValid();
                comprovaEstatPartida(); //nou
            }
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

    public void comprovaEstatPartida() {
        if(videsDisponibles == 0){
            vista.perdedor();
        } else if (videsDisponibles != 0) {
            boolean paraulaNoCompleta = false;
            int i = 0;
            while ((!paraulaNoCompleta) && (i < paraulaMisteriosa.length())) {
                if (espaisDesxifrats[i] == '_')
                    paraulaNoCompleta = true;
                else
                    i++;
            }

            if (paraulaNoCompleta == false)
                vista.guanyador(tornJugadorId);
        }
    }

    public int comprovarParaulaIntroduida(String paraula) {
        if (paraula == paraulaMisteriosa)
            return 0;
        else
            return -1;
    }
}