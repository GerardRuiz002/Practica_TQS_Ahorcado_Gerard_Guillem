import Controlador.Ahorcado;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AhorcadoTest {
    @Test
    public void testInicialitzaLletresDisponibles() {
        //Test de inicialització de les lletres disponibles
        Ahorcado ahorcadoIniciLletres = new Ahorcado(1,1);
        ahorcadoIniciLletres.inicialitzaLletresDisponibles();
        char resultat[] = ahorcadoIniciLletres.getLLetresDisponibles();
        char lletresEsperades[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z'};
        assertArrayEquals(lletresEsperades, resultat);
    }

    //Testeja que s'inicilitzin correctaments els espais buits a l'hora de seleccionar la dificultat
    @Test
    public void testGenerarEspaisParaulaMisteriosa() {
        //Test de inicialització amb dificultat fàcil (5 lletres) //Valor limit inferior
        Ahorcado paraulaMistBaix = new Ahorcado(1,1);
        paraulaMistBaix.generarEspaisParaulaMisteriosa();
        char resultatBaix[] = paraulaMistBaix.getEspaisDesxifratsArrayChar();
        char resultatEsperatBaix[] = {'_','_','_','_','_'};
        assertArrayEquals(resultatEsperatBaix, resultatBaix);

        //Test de inicialització amb dificultat mitja (7 lletres)
        Ahorcado paraulaMistMig = new Ahorcado(1,2);
        paraulaMistMig.generarEspaisParaulaMisteriosa();
        char resultatMig[] = paraulaMistMig.getEspaisDesxifratsArrayChar();
        char resultatEsperatMig[] = {'_','_','_','_','_','_','_'};
        assertArrayEquals(resultatEsperatMig, resultatMig);

        //Test de inicialització amb dificultat alta (10 lletres) //Valor limit superior
        Ahorcado paraulaMistAlt = new Ahorcado(1,3);
        paraulaMistAlt.generarEspaisParaulaMisteriosa();
        char resultatAlt[] = paraulaMistAlt.getEspaisDesxifratsArrayChar();
        char resultatEsperatAlt[] = {'_','_','_','_','_','_','_','_','_','_'};
        assertArrayEquals(resultatEsperatAlt, resultatAlt);
    }

    @Test
    public void testNivellDificultat() {
        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistBaix = new Ahorcado(1, 1);
        int resultatEsperatBaix = 1;
        assertEquals(resultatEsperatBaix, paraulaMistBaix.getNivellDificultat());

        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistMig = new Ahorcado(1, 2);
        int resultatEsperatMig = 2;
        assertEquals(resultatEsperatMig, paraulaMistMig.getNivellDificultat());

        //Test de inicialització amb dificultat fàcil (5 lletres)
        Ahorcado paraulaMistAlt = new Ahorcado(1, 3);
        int resultatEsperatAlt = 3;
        assertEquals(resultatEsperatAlt, paraulaMistAlt.getNivellDificultat());

        //Valors limit inferior esquerra
        Ahorcado nivellDifLimEsq = new Ahorcado(1, 0);
        boolean resultatLimEsq = true;
        assertEquals(resultatLimEsq, nivellDifLimEsq.errorCreation);

        //Valor limit superior dreta
        Ahorcado nivellDifLimDret = new Ahorcado(1, 4);
        boolean resultatLimDret = true;
        assertEquals(resultatLimDret, nivellDifLimDret.errorCreation);
    }

    @Test
    public void testNumJugadors() {
        //Valor limit inferior
        Ahorcado nJugadorsLimInf = new Ahorcado(1, 1);
        boolean resultatLimInf = false; //False ja que no ha de donar error
        assertEquals(resultatLimInf, nJugadorsLimInf.errorCreation);

        //Valor esquerra limit inferior
        Ahorcado nJugadorsLimInfEsq = new Ahorcado(0, 1);
        boolean resultatLimInfEsq = true;
        assertEquals(resultatLimInfEsq, nJugadorsLimInfEsq.errorCreation);

        //Valor dreta limit inferior
        Ahorcado nJugadorsLimInfDreta = new Ahorcado(2, 1);
        boolean resultatLimInfDreta = false; //False ja que no ha de donar error
        assertEquals(resultatLimInfDreta, nJugadorsLimInfDreta.errorCreation);

        //Valor limit superior
        Ahorcado nJugadorsLimSup = new Ahorcado(4, 1);
        boolean resultatLimSup = false; //False ja que no ha de donar error
        assertEquals(resultatLimSup, nJugadorsLimSup.errorCreation);

        //Valor dreta limit superior
        Ahorcado nJugadorsLimSupDret = new Ahorcado(5, 1);
        boolean resultatLimSupDret = true;
        assertEquals(resultatLimSupDret, nJugadorsLimSupDret.errorCreation);

        //Valor esquerra limit superior
        Ahorcado nJugadorsLimSupEsq = new Ahorcado(3, 1);
        boolean resultatLimSupEsq = false; //False ja que no ha de donar error
        assertEquals(resultatLimSupEsq, nJugadorsLimSupEsq.errorCreation);

        //Valor intermig
        Ahorcado nJugadorsIntermig = new Ahorcado(3, 1);
        boolean resultatIntermig = false; //False ja que no ha de donar error
        assertEquals(resultatIntermig, nJugadorsIntermig.errorCreation);
    }

    @Test
    public void testIntroduirParaulaMisteriosa() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;
        //Introducció paraula qualsevol en majuscula
        Ahorcado ipq = new Ahorcado(1,1);
        resultatEsperat = 0;
        resultatObtingut =  ipq.assignarParaulaMisteriosa("RATON");
        assertEquals(resultatEsperat,resultatObtingut);

        char [] resultatEsperatArray = {'R','A','T','O','N'};
        assertArrayEquals(resultatEsperatArray, ipq.getParaulaMisteriosaArray());

        //Introducció paraula qualsevol tota en minúscula
        Ahorcado ipqtm = new Ahorcado(1,1);
        resultatEsperat = -1;
        resultatObtingut =  ipqtm.assignarParaulaMisteriosa("raton");
        assertEquals(resultatEsperat,resultatObtingut);

        assertArrayEquals(null, ipqtm.getParaulaMisteriosaArray());

        //Introducció de qualsevol paraula amb minuscules i majuscules
        Ahorcado iqpmm = new Ahorcado(1,1);
        resultatEsperat = -1;
        resultatObtingut =  iqpmm.assignarParaulaMisteriosa("RaToN");
        assertEquals(resultatEsperat,resultatObtingut);

        assertArrayEquals(null, iqpmm.getParaulaMisteriosaArray());

        //Introducció caràcter invàlid
        Ahorcado ici = new Ahorcado(1,1);
        resultatEsperat = -1;
        resultatObtingut =  ici.assignarParaulaMisteriosa("çñ)A");
        assertEquals(resultatEsperat,resultatObtingut);

        assertArrayEquals(null, ici.getParaulaMisteriosaArray());
    }

    @Test
    public void testComprovaLletraCorrecta() {
        boolean resultatEsperat = false;
        boolean resultatObtingut = false;
        //Valor limit inferior
        Ahorcado vli = new Ahorcado(1,1);
        resultatEsperat = true;
        resultatObtingut = vli.comprovaLletraCorrecta('A');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor esquerra limit inferior
        Ahorcado veli = new Ahorcado(1,1);
        resultatEsperat = false;
        resultatObtingut = veli.comprovaLletraCorrecta('@');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor dreta limit inferior
        Ahorcado vdli = new Ahorcado(1,1);
        resultatEsperat = true;
        resultatObtingut = vdli.comprovaLletraCorrecta('Y');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor limit superior
        Ahorcado vls = new Ahorcado(1,1);
        resultatEsperat = true;
        resultatObtingut = vls.comprovaLletraCorrecta('Z');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor limit esquerra superior
        Ahorcado vles = new Ahorcado(1,1);
        resultatEsperat = true;
        resultatObtingut = vles.comprovaLletraCorrecta('Y');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor limit dreta superior
        Ahorcado vlds = new Ahorcado(1,1);
        resultatEsperat = false;
        resultatObtingut = vles.comprovaLletraCorrecta('[');
        assertEquals(resultatEsperat,resultatObtingut);

        //Valor intermig
        Ahorcado vi = new Ahorcado(1,1);
        resultatEsperat = true;
        resultatObtingut = vles.comprovaLletraCorrecta('J');
        assertEquals(resultatEsperat,resultatObtingut);
    }

    @Test
    public void testComprovaCaracterNoUtilitzat() {
        Ahorcado t = new Ahorcado(1,1);

    }

    @Test
    public void testIntroduirLletra() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;

        //Valor limit inferior
        Ahorcado li = new Ahorcado(1,1);
        li.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = 0;
        resultatObtingut = li.introduirLletra('A');
        char[] resultatEsperatParaulailq = {'_','A','_','A','_'};
        char[] resultatObtingutParaulailq = li.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParaulailq,resultatObtingutParaulailq);

        //Valor esquerra limit inferior
        Ahorcado eli = new Ahorcado(1,1);
        eli.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = -1;
        resultatObtingut = eli.introduirLletra('@');
        char[] resultatEsperatParaulali = {'_','_','_','_','_'};
        char[] resultatObtingutParaulali = eli.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParaulali,resultatObtingutParaulali);

        //Valor dreta limit inferior
        Ahorcado dli = new Ahorcado(1,1);
        dli.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = 0;
        resultatObtingut = dli.introduirLletra('Y');
        char[] resultatEsperatParauladli = {'_','_','_','_','_'};
        char[] resultatObtingutParauldli = dli.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParauladli,resultatObtingutParauldli);

        //Valor limit superior
        Ahorcado ls = new Ahorcado(1,1);
        ls.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = 0;
        resultatObtingut = ls.introduirLletra('Z');
        char[] resultatEsperatParauladls = {'_','_','_','_','_'};
        char[] resultatObtingutParauldls = ls.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParauladls,resultatObtingutParauldls);

        //Valor limit esquerra superior
        Ahorcado les = new Ahorcado(1,1);
        les.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = 0;
        resultatObtingut = les.introduirLletra('B');
        char[] resultatEsperatParauladles = {'_','_','_','_','_'};
        char[] resultatObtingutParauldles = les.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParauladles,resultatObtingutParauldles);

        //Valor limit dreta superior
        Ahorcado lds = new Ahorcado(1,1);
        lds.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = -1;
        resultatObtingut = lds.introduirLletra('[');
        char[] resultatEsperatParauladlds = {'_','_','_','_','_'};
        char[] resultatObtingutParauldlds = lds.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParauladlds,resultatObtingutParauldlds);

        //Valor intermig
        Ahorcado vi = new Ahorcado(1,1);
        vi.assignarParaulaMisteriosa("MAMAS");
        resultatEsperat = 0;
        resultatObtingut = vi.introduirLletra('M');
        char[] resultatEsperatParaulavi = {'M','_','M','_','_'};
        char[] resultatObtingutParaulavi = vi.getEspaisDesxifratsArrayChar();
        assertEquals(resultatEsperat, resultatObtingut);
        assertArrayEquals(resultatEsperatParaulavi,resultatObtingutParaulavi);

    }

    @Test
    public void testIntroduirParaula(){
        //paraula correcte:
        boolean resultatEsperat = true;
        boolean resultatObtingut = false;
        Ahorcado a = new Ahorcado(1,1);
        a.assignarParaulaMisteriosa("CABRA");
        resultatObtingut = a.introduirParaula("CABRA");
        assertEquals(resultatEsperat, resultatObtingut);

        //paraula incorrecte:
        boolean resultatEsperatIn = false;
        boolean resultatObtingutIn = false;
        Ahorcado b = new Ahorcado(1,1);
        b.assignarParaulaMisteriosa("CABRA");
        resultatObtingut = b.introduirParaula("MOBIL");
        assertEquals(resultatEsperatIn, resultatObtingutIn);

        //paraula mes curta:
        boolean resultatEsperatCurt = false;
        boolean resultatObtingutCurt = false;
        Ahorcado c = new Ahorcado(1,1);
        c.assignarParaulaMisteriosa("PATOS");
        resultatObtingut = c.introduirParaula("NO");
        assertEquals(resultatEsperat, resultatObtingut);


        //paraula mes llarga:
        boolean resultatEsperatLlarg = false;
        boolean resultatObtingutLlarg = false;
        Ahorcado d = new Ahorcado(1,1);
        d.assignarParaulaMisteriosa("ABRIR");
        resultatObtingut = d.introduirParaula("TECLADOS");
        assertEquals(resultatEsperat, resultatObtingut);

        //paraula minuscules
        boolean resultatEsperatMin = false;
        boolean resultatObtingutMin = false;
        Ahorcado e = new Ahorcado(1,1);
        e.assignarParaulaMisteriosa("TECLADO");
        resultatObtingut = e.introduirParaula("teclado");
        assertEquals(resultatEsperat, resultatObtingut);

    }




    @Test
    public void comprovaLletra(){ //CREAR UN MOCKOBJECT
        //Test comprovar que ens passen una lletra i no un altre caracter:
        Ahorcado paraulaMisteriosa = new Ahorcado(1,1);

        char testChar = 'c';
        int resultatEsperat1 = 0;

        char tesIntegerChar = '2';
        int resultatEsperat2 = -1;

        int valorRealCorrecte1 =  paraulaMisteriosa.introduirLletra(testChar);
        int calorRealIncorrecte2 =  paraulaMisteriosa.introduirLletra(tesIntegerChar);

        assertEquals(resultatEsperat1, valorRealCorrecte1);
        assertEquals(resultatEsperat2, calorRealIncorrecte2);

/*97-122*/
        /*assert(type(a) == int)
    assert(type(b) == int)*/
    };





            //vista controlador TDD caixa negra Caixa blanca Coverage(decision i condicion) 1 mock object
}



//Valor limit inferior

//Valor esquerra limit inferior

//Valor dreta limit inferior

//Valor limit superior

//Valor limit esquerra superior

//Valor limit dreta superior

//Valor intermig