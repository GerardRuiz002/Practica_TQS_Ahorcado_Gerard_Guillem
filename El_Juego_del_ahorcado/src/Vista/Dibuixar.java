package Vista;

import Model.Jugador;

public class Dibuixar {
    public void imprimirVidesDisponebles(int videsDisponibles) {
        System.out.println(videsDisponibles);
    }

    public void errorCaracterNoValid() { System.out.println("Caracter no valid. Introdueix únicament lletres majúscules."); }
    public void errorLletraUtilitzada() {
        System.out.println("Aquesta lletra ja ha estat utilitzada.");
    }
    public void errorLletraNoValida() { System.out.println("Aquesta lletra no correspon a cap lletra de la paraula misteriosa."); }
    public void errorParaulaNoCorrecta(String paraula) { System.out.println("La paraula misteriosa no era " + paraula + "!!"); }
    public void errorIntroduccoOpcio() { System.out.println("Has introduit una opció incorrecta."); }
    public void errorCarregaArxiu() { System.out.println("Error, no s'ha pogut carregar cap arxiu."); }
    public void errorNombreJugadors() { System.out.println("El número de jugador ha de ser minim 1 i màxim 4."); }
    public void errorDificultat() { System.out.println("La dificultat ha de ser com a minim 1 i com a maxim 3"); }

    public void missatgeIntroduirJugador() { System.out.print("Introdueix el nombre de jugadors de la partida: "); }
    public void missatgeIntroduirDificultat() { System.out.print("Introdueix el nivell de dificultat de la partida: "); }
    public void missatgeEscullOpcio() { System.out.println("Escull una de les opcions: \n 1: introduir lletra \n 2: introduir paraula"); }
    public void missatgeIntrodueixLletra() { System.out.print("Introdueix la lletra que dessitgis (en majuscula): "); }
    public void missatgeIntrodueixParaula() { System.out.print("Introdueix la paraula que dessitgis (tota en majuscules): "); }
    public void misstgeTornJudadorN(int n) { System.out.println("Es el torn del jugador " + n); }
    public void missatgeSortintJoc() { System.out.println("Sortint del joc");}
    public void missatgeIntrodueixNom() { System.out.print("Introdueix el teu nom: ");}
    public void misstageCarregaPartidaONovaPartida() {System.out.println("Escull opcio de inicialitzacio de partida: \n 1.Comencar partida nova \n 2.Carregar partida anterior");}
    public void missatgeGuardarPartida() { System.out.println("Vols guardar la puntuacio de la partida?  \n 1.No \n 2.Si");}
    public void missatgeGuardatCorrectament(boolean guardatCorrecte) {
        if (guardatCorrecte == false)
            System.out.println("No s'ha pogut guardar correctament la partida");
        else
            System.out.println("S'ha guardat correctament la partida");
    }

    public void mostrarVidesActuals(int vides) { System.out.println("Queden " + vides + " vides.");}
    public void mostrarEspaisDesxifrats(String espaisDesxifrats) { System.out.println("Espais desxifrats: " + espaisDesxifrats);}
    public void perdedor() { System.out.println("S'han acabat les vides, partida perduda.");}
    public void guanyador(int torn) { System.out.println("El guanyador es: " + torn + ".");}
    public void tornDe(int torn) { System.out.println("Es el torn del jugador: " + torn + ".");}
    public void presentacioJoc() { System.out.println("Benvingut al joc del ahorcado. ");}
    public void escullOpcioFinal() { System.out.println("   1: Veure Ranking i juga de nou \n   2: Veure el ranking i sortir del joc ");}
    public void mostraRanking(Jugador[] jug) {
        System.out.println("Puntuacio total: \n");
        for (int i = 0; i < jug.length; i++) {
            System.out.println("Jugador " + (i + 1) + ": " + jug[i].getPuntiacio() + " punts \n");
        }
    }
}
