import Controlador.Ahorcado;
import org.junit.Test;

import static org.junit.Assert.*;

public class AhorcadoTest {
    @Test
    public void inicialitzaLletresDisponibles() {
        //Test de inicialització de les lletres disponibles
        Ahorcado ahorcadoIniciLletres = new Ahorcado(0,0);
        ahorcadoIniciLletres.inicialitzaLletresDisponibles();
        char resultat[] = ahorcadoIniciLletres.getLLetresDisponibles();
        char lletresEsperades[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z'};
        assertArrayEquals(lletresEsperades, resultat);
    }

    //Testeja que s'inicilitzin correctaments els espais buits a l'hora de seleccionar la dificultat
    @Test
    public void generarEspaisParaulaMisteriosa() {
        //Test de inicialització amb dificultat fàcil (5 lletres) //Valor limit inferior
        Ahorcado paraulaMistBaix = new Ahorcado(0,1);
        paraulaMistBaix.generarEspaisParaulaMisteriosa();
        char resultatBaix[] = paraulaMistBaix.getEspaisDesxifrats();
        char resultatEsperatBaix[] = {'_','_','_','_','_'};
        assertArrayEquals(resultatEsperatBaix, resultatBaix);

        //Test de inicialització amb dificultat mitja (7 lletres)
        Ahorcado paraulaMistMig = new Ahorcado(0,2);
        paraulaMistMig.generarEspaisParaulaMisteriosa();
        char resultatMig[] = paraulaMistMig.getEspaisDesxifrats();
        char resultatEsperatMig[] = {'_','_','_','_','_','_','_'};
        assertArrayEquals(resultatEsperatMig, resultatMig);

        //Test de inicialització amb dificultat alta (10 lletres) //Valor limit superior
        Ahorcado paraulaMistAlt = new Ahorcado(0,3);
        paraulaMistAlt.generarEspaisParaulaMisteriosa();
        char resultatAlt[] = paraulaMistAlt.getEspaisDesxifrats();
        char resultatEsperatAlt[] = {'_','_','_','_','_','_','_','_','_','_'};
        assertArrayEquals(resultatEsperatAlt, resultatAlt);
    }

    @Test
    public void nivellDificultat() {
        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistBaix = new Ahorcado(0, 1);
        int resultatEsperatBaix = 1;
        assertEquals(resultatEsperatBaix, paraulaMistBaix.getNivellDificultat());

        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistMig = new Ahorcado(0, 2);
        int resultatEsperatMig = 2;
        assertEquals(resultatEsperatMig, paraulaMistMig.getNivellDificultat());

        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistAlt = new Ahorcado(0, 3);
        int resultatEsperatAlt = 3;
        assertEquals(resultatEsperatAlt, paraulaMistAlt.getNivellDificultat());

    }

    @Test
    public  void comprovaLletra(){ //CREAR UN MOCKOBJECT
        //Test comprovar que ens passen una lletra i no un altre caracter:
        Ahorcado paraulaMisteriosa = new Ahorcado(0,1);

        char testChar = 'c';
        int resultatEsperat1 = 0;

        char tesIntegerChar = '2';
        int resultatEsperat2 = -1;

        int valorRealCorrecte1 =  paraulaMisteriosa.comprovaLletra(testChar);
        int calorRealIncorrecte2 =  paraulaMisteriosa.comprovaLletra(tesIntegerChar);

        assertEquals(resultatEsperat1, valorRealCorrecte1);
        assertEquals(resultatEsperat2, calorRealIncorrecte2);



/*97-122*/
        /*assert(type(a) == int)
    assert(type(b) == int)*/
    };
}