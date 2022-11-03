package Controlador;

import java.util.*;
import java.io.*;
import java.util.Arrays;



public class Ahorcado {
    private int nJugadors;
    private int tornJugadorId;
    private int videsDisponibles;

    private String paraulaMisteriosa;
    private char paraulaMisteriosaArray[];

    private int midaParaulaMisteriosa;
    private char espaisDesxifrats[]; //Espais de la paraula que han sigut desxifrats (______) || (A_____) || (A____B)

    private char lletresUtilitzades[];
    private char lletresDisponibles[];

    private int nivellDificultat; //Baix = 5 lletres | Mig = 7 lletres | Alt = 10 lletres
    //Getters
    public char[] getLLetresDisponibles() { return this.lletresDisponibles;}
    public char[] getEspaisDesxifrats() {return this.espaisDesxifrats;}
    public int getNivellDificultat(){return this.nivellDificultat;}


    public Ahorcado(int numJugadors, int nivellDificultat) {
        this.nJugadors = numJugadors;

        //Inicialitzem amb les lletres de l'abecedari
        this.lletresDisponibles = new char[26];
        this.inicialitzaLletresDisponibles();

        //Inicialitzem nivell de dificultat
        this.nivellDificultat = nivellDificultat;
        this.nivellDificultat(nivellDificultat);

        //Incialitzem els espaisDesxifrats (inicialment sera tot ______)
        this.espaisDesxifrats = new char[midaParaulaMisteriosa];
    }

    //Inicialitza les lletres disponibles a escollir (amb l'abecedari sense ñ)
    public void inicialitzaLletresDisponibles() {
        for (int i = 0; i < 26; i++) {
            this.lletresDisponibles[i] = (char) (65 + i);
        }
    }

    //Genera els espais (___) amb la longitud de la paraula misteriosa
    public void generarEspaisParaulaMisteriosa() {
        for (int i = 0; i < midaParaulaMisteriosa; i++)
            this.espaisDesxifrats[i] = '_';
    }

    public void nivellDificultat(int dificultat){
        if(dificultat == 1)
            this.midaParaulaMisteriosa = 5;
        else if(dificultat == 2)
            this.midaParaulaMisteriosa = 7;
        else if(dificultat == 3)
            this.midaParaulaMisteriosa = 10;
    }

    //Comprova si la lletra introduida per l'usuari forma part de la paraula misteriosa. L'afegeix, o resta vida.
    public int comprovaLletra(char lletra){
        if((int)lletra >= 97 && (int)lletra <= 122) { //ASCII
            boolean trobada = false;
            int i = 0;
            while (!trobada && i < this.paraulaMisteriosa.length()) {
                if (this.paraulaMisteriosaArray[i] == lletra) { //si troba lletra cridem al escriu lletra.
                    escriuLletra(lletra, i);
                    trobada = true;
                }
                i++;
            }
            if (i == this.paraulaMisteriosa.length() - 1)
                this.videsDisponibles -= 1; //restem vida si la lletra no es dins la paraula
            return 0;
        }else{
            System.out.println("Caràcter no valid. Introdueix únicament lletres majuscules.");
            return -1;
        }
    }

    //escriu la lletra que ha encertat l'usuari en la posició que li correspon.
    public void escriuLletra(char lletra, int pos){
        espaisDesxifrats[pos] = lletra;
        int i = 0;
        boolean trobat = false;
        while(i < 26 && !trobat){
            if(lletresDisponibles[i] == lletra){
               lletresDisponibles[i] = '0';
               trobat = true;
            }
            ++i;
        }
    }

}

//toCharArray();

/*5,7,10*/
