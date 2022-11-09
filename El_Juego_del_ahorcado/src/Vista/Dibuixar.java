package Vista;

public class Dibuixar {
    public void imprimirVidesDisponebles(int videsDisponibles) {
        System.out.println(videsDisponibles);
    }

    public void errorCaracterNoValid() { System.out.println("Caràcter no valid. Introdueix únicament lletres majúscules."); }
    public void errorLletraUtilitzada() {
        System.out.println("Aquesta lletra ja ha estat utilitzada.");
    }
    public void errorLletraNoValida() { System.out.println("Aquesta lletra no correspon a cap lletra de la paraula misteriosa."); }
    public void errorParaulaNoCorrecta(String paraula) {System.out.println("La paraula misteriosa no era " + paraula + "!!");}
    public void errorIntroduccoOpcio() { System.out.println("Has introduit una opció incorrecta.");}
    public void errorCarregaArxiu() { System.out.println("Error, no s'ha pogur carregar cap arxiu."); }
    public void errorNombreJugadors() { System.out.println("El número de jugador ha de ser minim 1 i màxim 4."); }
    public void errorDificultat() { System.out.println("La dificultat ha de ser com a minim 1 i com a màxim 3"); }

    public void missatgeIntroduirJugador() { System.out.print("Introdueix el nombre de jugadors de la partida: "); };
    public void missatgeIntroduirDificultat() { System.out.print("Introdueix el nivell de dificultat de la partida: "); }
    public void missatgeEscullOpcio () { System.out.println("Escull una de les opcions: \n 1: introduir lletra \n 2: introduir paraula"); }
    public void missatgeIntrodueixLletra() { System.out.print("Introdueix la lletra que dessitgis (en majúscula): "); }
    public void missatgeIntrodueixParaula() { System.out.print("Introdueix la paraula que dessitgis (tota en majúscules): "); }
    public void misstgeTornJudadorN(int n) { System.out.println("Es el torn del jugador " + n);}

    public void missatgeIntrodueixNom() { System.out.print("Introdueix el teu nom: "); }

    public void misstageCarregaPartidaONovaPartida() { System.out.println("Escull opció de inicialització de partida: \n 1.Començar partida nova \n 2.Carregar partida anterior");}

    public void mostrarVidesActuals(int vides) {System.out.println("Queden " + vides + " vides."); }
    public  void mostrarEspaisDesxifrats(String espaisDesxifrats) { System.out.println("Espais desxifrats: " + espaisDesxifrats); }

    public void perdedor() {
        System.out.println("S'han acabat les vides, partida perduda.");
    }
    public void guanyador(int torn){
        System.out.println("El guanyador és: " + torn + ".");
    }


    /*
    //escriu la lletra que ha encertat l'usuari.
    public void escriuLletra(char lletra){
        for(int i = 0; i<this.paraulaMisteiosa.length(); i++){
            if(espaisDesxifrats[i] == lletra){}

        }
    }*/


}
