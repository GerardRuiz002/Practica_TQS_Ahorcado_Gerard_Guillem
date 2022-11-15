import Controlador.Ahorcado;
import Model.MockParaulesDisponibles;
import Model.Ranking;
import org.junit.Test;

import java.io.IOException;

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

    //Testeja que s'inicilitzin correctament els espais buits a l'hora de seleccionar la dificultat
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
    public void testGenerarMidaParaulaMisteriosa() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;

        //Dificultat 1 (frontera inferior)
        Ahorcado mida5 = new Ahorcado(1,1);
        resultatEsperat = 5;
        resultatObtingut = mida5.getMidaParaulaMisteriosa();
        assertEquals(resultatEsperat, resultatObtingut);

        //Dificultat 2 (intermig, dreta inferior, esquerra superior )
        Ahorcado mida7 = new Ahorcado(1,2);
        resultatEsperat = 7;
        resultatObtingut = mida7.getMidaParaulaMisteriosa();
        assertEquals(resultatEsperat, resultatObtingut);

        //Dificultat 3 (frontera superior)
        Ahorcado mida10 = new Ahorcado(1,3);
        resultatEsperat = 10;
        resultatObtingut = mida10.getMidaParaulaMisteriosa();
        assertEquals(resultatEsperat, resultatObtingut);
    }

    @Test
    public void testGenerarVidesPartida() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;

        //Dificultat 1 (frontera inferior)
        Ahorcado vid8 = new Ahorcado(1,1);
        resultatEsperat = 8;
        resultatObtingut = vid8.getVidesDisponibles();
        assertEquals(resultatEsperat, resultatObtingut);

        //Dificultat 2 (intermig, dreta inferior, esquerra superior )
        Ahorcado vid5 = new Ahorcado(1,2);
        resultatEsperat = 5;
        resultatObtingut = vid5.getVidesDisponibles();
        assertEquals(resultatEsperat, resultatObtingut);

        //Dificultat 3 (frontera superior)
        Ahorcado vid3 = new Ahorcado(1,3);
        resultatEsperat = 3;
        resultatObtingut = vid3.getVidesDisponibles();
        assertEquals(resultatEsperat, resultatObtingut);
    }

    //Partició equivalent
    @Test
    public void testAssignarParaulaMisteriosa() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;
        String paraula = null;
        String paraulaEsperada = null;

        //Paraula correcta 1
        Ahorcado pc1 = new Ahorcado(1,1);
        paraula = "PATAS";
        paraulaEsperada = "PATAS";
        resultatEsperat = 0;
        resultatObtingut = pc1.assignarParaulaMisteriosa(paraula);
        assertEquals(resultatEsperat, resultatObtingut);
        assertEquals(paraulaEsperada, pc1.getParaulaMisteriosa());

        //Paraula correcta 2
        Ahorcado pc2 = new Ahorcado(1,1);
        paraula = "GATOS";
        paraulaEsperada = "GATOS";
        resultatEsperat = 0;
        resultatObtingut = pc2.assignarParaulaMisteriosa(paraula);
        assertEquals(resultatEsperat, resultatObtingut);
        assertEquals(paraulaEsperada, pc2.getParaulaMisteriosa());

        //Paraula incorrecta 1
        Ahorcado pi1 = new Ahorcado(1,1);
        paraula = "cacas";
        paraulaEsperada = null;
        resultatEsperat = -1;
        resultatObtingut = pi1.assignarParaulaMisteriosa(paraula);
        assertEquals(resultatEsperat, resultatObtingut);
        assertEquals(paraulaEsperada, pi1.getParaulaMisteriosa());

        //Paraula incorrecta 2
        Ahorcado pi2 = new Ahorcado(1,1);
        paraula = "pecas";
        paraulaEsperada = null;
        resultatEsperat = -1;
        resultatObtingut = pi2.assignarParaulaMisteriosa(paraula);
        assertEquals(resultatEsperat, resultatObtingut);
        assertEquals(paraulaEsperada, pi2.getParaulaMisteriosa());
    }

    @Test
    public void testAssignaParaulaMisteriosaMock() throws IOException {
        MockParaulesDisponibles mock = new MockParaulesDisponibles(1);
        int resultatEsperat = 0;
        int resultatObtingut = 0;
        String paraula = null;
        String paraulaEsperada = null;

        //Paraula correcta 1
        Ahorcado pc1 = new Ahorcado(1,1);
        paraula = mock.getParaulaMisteriosa();
        paraulaEsperada = "CASAS";
        resultatEsperat = 0;
        resultatObtingut = pc1.assignarParaulaMisteriosa(paraula);
        assertEquals(resultatEsperat, resultatObtingut);
        assertEquals(paraulaEsperada, pc1.getParaulaMisteriosa());
    }


    @Test
    public void testAssignarTornJugador() {
        int tornJugador1Esperat = 0;
        int tornJugador2Esperat = 0;
        int tornJugador3Esperat = 0;
        int tornJugador4Esperat = 0;

        //Num jugadors 1 (frontera inferior)
        Ahorcado j1 = new Ahorcado(1,1);
        j1.assignaTornJugador();
        tornJugador1Esperat = 1;
        assertEquals(tornJugador1Esperat, j1.getTornJugadorN(0));

        //Num jugadors 4 (frontera superior)
        Ahorcado j4 = new Ahorcado(4, 1);
        j4.assignaTornJugador();
        tornJugador1Esperat = 1;
        tornJugador2Esperat = 2;
        tornJugador3Esperat = 3;
        tornJugador4Esperat = 4;
        assertEquals(tornJugador1Esperat, j4.getTornJugadorN(0));
        assertEquals(tornJugador2Esperat, j4.getTornJugadorN(1));
        assertEquals(tornJugador3Esperat, j4.getTornJugadorN(2));
        assertEquals(tornJugador4Esperat, j4.getTornJugadorN(3));
    }

    //Valors limit i frontera
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
    public void testIntroduirParaula(){
        //paraula correcte:
        boolean resultatEsperat = true;
        boolean resultatObtingut = false;
        Ahorcado a = new Ahorcado(1,1);
        a.assignaTornJugador();
        a.assignarParaulaMisteriosa("CABRA");
        resultatObtingut = a.introduirParaula("CABRA");
        assertEquals(resultatEsperat, resultatObtingut);

        //paraula incorrecte:
        boolean resultatEsperatIn = false;
        boolean resultatObtingutIn = false;
        Ahorcado b = new Ahorcado(1,1);
        b.assignaTornJugador();
        b.assignarParaulaMisteriosa("CABRA");
        resultatObtingut = b.introduirParaula("MOBIL");
        assertEquals(resultatEsperatIn, resultatObtingutIn);

        //paraula mes curta:
        boolean resultatEsperatCurt = false;
        boolean resultatObtingutCurt = false;
        Ahorcado c = new Ahorcado(1,1);
        c.assignaTornJugador();
        c.assignarParaulaMisteriosa("PATOS");
        resultatObtingut = c.introduirParaula("NO");
        assertEquals(resultatEsperatCurt, resultatObtingutCurt);


        //paraula mes llarga:
        boolean resultatEsperatLlarg = false;
        boolean resultatObtingutLlarg = false;
        Ahorcado d = new Ahorcado(1,1);
        d.assignaTornJugador();
        d.assignarParaulaMisteriosa("ABRIR");
        resultatObtingut = d.introduirParaula("TECLADOS");
        assertEquals(resultatEsperatLlarg, resultatObtingutLlarg);

        //paraula minuscules
        boolean resultatEsperatMin = false;
        boolean resultatObtingutMin = false;
        Ahorcado e = new Ahorcado(1,1);
        e.assignaTornJugador();
        e.assignarParaulaMisteriosa("TECLADO");
        resultatObtingut = e.introduirParaula("teclado");
        assertEquals(resultatEsperatMin, resultatObtingutMin);

    }

    //Limits i frontera
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
    public void TestComprovaLletraCorrectaDecitionCondition(){
        //Condition coverage:
        // (true && false)
        Ahorcado a = new Ahorcado(1,1);
        boolean resultatEsperat = false;
        boolean resultatObtingut = false;
        resultatObtingut = a.comprovaLletraCorrecta('h');
        assertEquals(resultatEsperat, resultatObtingut);

        //(false && true)
        Ahorcado b = new Ahorcado(1,1);
        boolean resultatEsperatB = false;
        boolean resultatObtingutB = false;
        resultatObtingutB = a.comprovaLletraCorrecta('3');
        assertEquals(resultatEsperatB, resultatObtingutB);


        //Decition Coverage
        // (false):
        Ahorcado c = new Ahorcado(1,1);
        boolean resultatEsperatC = false;
        boolean resultatObtingutC = false;
        resultatObtingutC = a.comprovaLletraCorrecta('2');
        assertEquals(resultatEsperatC, resultatObtingutC);

        //(true):
        Ahorcado d = new Ahorcado(1,1);
        boolean resultatEsperatD = true;
        boolean resultatObtingutD = false;
        resultatObtingutD = a.comprovaLletraCorrecta('A');
        assertEquals(resultatEsperatD, resultatObtingutD);
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
    public void testComprovaCaracterNoUtilitzat(){
        //caracter es troba en lletres disponibles
        Ahorcado a = new Ahorcado(1,1);
        boolean resultatEsperatA = true;
        boolean resultatObtingutA = false;
        a.assignarParaulaMisteriosa("RATON");
        a.introduirLletra('R');
        resultatObtingutA = a.comprovaCaracterNoUtilitzat('A');
        assertEquals(resultatEsperatA,resultatObtingutA);

        //caracter no es troba en lletres disponibles
        Ahorcado b = new Ahorcado(1,1);
        boolean resultatEsperatB = false;
        boolean resultatObtingutB = false;
        a.assignarParaulaMisteriosa("RATON");
        a.introduirLletra('A');
        resultatObtingutB = a.comprovaCaracterNoUtilitzat('A');
        assertEquals(resultatEsperatB,resultatObtingutB);
    }

    @Test
    public void testComprovaEstatPartida(){

        //videsDisponibles = 0  (true)
        Ahorcado a = new Ahorcado(1,1);
        a.assignaTornJugador();
        boolean resultatEsperat = true;
        boolean resultatObtingut = false;
        a.assignarParaulaMisteriosa("RATON");
        a.setVidesDisponibles(0);
        char array[] = {'R','A','T','O','N'};
        resultatObtingut = a.comprovaEstatPartida();
        assertEquals(resultatEsperat, resultatObtingut);

        //videsDisponibles != 0 ( false): i paraula completa a (true)
        Ahorcado b = new Ahorcado(1,1);
        b.assignaTornJugador();
        boolean resultatEsperat2 = true;
        boolean resultatObtingut2 = false;
        b.assignarParaulaMisteriosa("RATON");
        b.setVidesDisponibles(3);
        char array1[] = {'R','A','T','O','N'};
        b.setEspaisDesxifrats(array1);
        resultatObtingut2 = b.comprovaEstatPartida();
        assertEquals(resultatEsperat2, resultatObtingut2);

        //videsDisponibles a (false) i paraula completa a (false)
        Ahorcado c = new Ahorcado(1,1);
        c.assignaTornJugador();
        boolean resultatEsperat3 = false;
        boolean resultatObtingut3 = false;
        c.assignarParaulaMisteriosa("RATON");
        c.setVidesDisponibles(3);
        char array2[] = {'R','A','T','_','N'};
        c.setEspaisDesxifrats(array2);
        resultatObtingut3 = c.comprovaEstatPartida();
        assertEquals(resultatEsperat3, resultatObtingut3);
    }

    @Test
    public void testEliminaLletraDisponible(){
        //eliminacio Correcte:
        char[] resultEsperat = {'0','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        Ahorcado a1 = new Ahorcado(1,1);
        a1.assignaTornJugador();
        a1.assignarParaulaMisteriosa("GORDA");
        a1.setVidesDisponibles(3);
        a1.introduirLletra('A');
        assertArrayEquals(a1.getLLetresDisponibles(), resultEsperat);

        //eliminacio Incorrecte:
        int resultEsperat2 =-1;
        Ahorcado a2 = new Ahorcado(1,1);
        a2.assignaTornJugador();
        a2.assignarParaulaMisteriosa("GORDA");
        a2.setVidesDisponibles(3);
        int resultatObtingut2 = a2.introduirLletra('a');
        assertEquals(resultEsperat2, resultatObtingut2);

        //eliminacio Repetida:
        int  resultEsperat3 = -1;
        Ahorcado a3 = new Ahorcado(1,1);
        a3.assignaTornJugador();
        a3.assignarParaulaMisteriosa("GORDA");
        a3.setVidesDisponibles(3);
        a3.introduirLletra('A');
        int resultat = a3.introduirLletra('A');
        assertEquals(resultEsperat3, resultat);
    }

    @Test
    public void testCanviarTorn() {
        int resultatEsperat = 0;
        int resultatObtingut = 0;

        //Canvi torn amb 1 jugador (Frontera inferior)
        Ahorcado ct1 = new Ahorcado(1,1);
        ct1.canviarTorn();
        resultatEsperat = 1;
        resultatObtingut = ct1.getTorn();
        assertEquals(resultatEsperat, resultatObtingut);

        //Canvi torn 4 jugadors (Frontera superior)
        Ahorcado ct4 = new Ahorcado(4,1);
        ct4.canviarTorn();
        ct4.canviarTorn();
        ct4.canviarTorn();
        resultatEsperat = 4;
        resultatObtingut = ct4.getTorn();
        assertEquals(resultatEsperat, resultatObtingut);

        ct4.canviarTorn();
        resultatEsperat = 1;
        resultatObtingut = ct1.getTorn();
        assertEquals(resultatEsperat, resultatObtingut);
    }


    @Test
    public void testSetFiPartida(){
        Ahorcado c = new Ahorcado(1,1);
        c.setFiPartida(true);
        boolean resultEsperat = true;
        boolean resultObtingut = c.getFipartida();
        assertEquals(resultEsperat, resultObtingut);
    }

    @Test
    public void testSetNomJugador() {
        Ahorcado n = new Ahorcado(1,1);
        n.assignaTornJugador();
        n.setNomJugador("PACO", 0);
        String restultatEsperat = "PACO";
        String resultatObtingut = n.getNomJugador(0);
        assertEquals(restultatEsperat, resultatObtingut);
    }

    @Test
    public void testGetEspaisDesxifrats() {
        Ahorcado n = new Ahorcado(1,1);
        String resultatEsperat = "_____";
        String resultatObtingut = n.getEspaisDesxifrats();
        assertEquals(resultatEsperat,resultatObtingut);
    }


    @Test
    public void testGetJugadors(){
        //1 jugador:
        Ahorcado n = new Ahorcado(1,1);
        n.assignaTornJugador();
        n.setNomJugador("PEPE", 0);
        String valorEsperat = "PEPE";
        String valorObtingut = n.getJugadors()[0].getNomJugador();
        assertEquals(valorEsperat, valorObtingut);

        //4 jugadors
        Ahorcado n4 = new Ahorcado(4,1);
        n4.assignaTornJugador();
        n4.setNomJugador("ALEX", 0);
        n4.setNomJugador("PACA", 1);
        n4.setNomJugador("ZULEMA", 2);
        n4.setNomJugador("SANTIAGO", 3);
        String resultatObtingut1 = n4.getJugadors()[0].getNomJugador();
        String resultatObtingut2 = n4.getJugadors()[1].getNomJugador();
        String resultatObtingut3 = n4.getJugadors()[2].getNomJugador();
        String resultatObtingut4 = n4.getJugadors()[3].getNomJugador();
        assertEquals("ALEX", resultatObtingut1);
        assertEquals("PACA", resultatObtingut2);
        assertEquals("ZULEMA", resultatObtingut3);
        assertEquals("SANTIAGO", resultatObtingut4);
    }
            //vista controlador TDD caixa negra Caixa blanca Coverage(decision i condicion) 1 mock object
}