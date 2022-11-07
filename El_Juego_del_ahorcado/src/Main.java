import Controlador.Ahorcado;
import Model.ParaulesDisponibles;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
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

    public static ArrayList<String> porvaLecturaArxiu() throws IOException {
        ParaulesDisponibles pd = new ParaulesDisponibles();
        ArrayList<String> paraules =  pd.llegirTxt("src/Text/facil.txt");
        return paraules;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> lectura = porvaLecturaArxiu();

    }



    /* while(dificultat < 1 || dificultat >3)
            System.out.println("Nivell de dificultat erroni. Torna a introduir el nivell de dificultat");*/
}