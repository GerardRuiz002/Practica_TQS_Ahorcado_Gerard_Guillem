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

    public void missatgeIntroduirJugador() { System.out.print("Introdueix el nombre de jugadors de la partida: "); };
    public void missatgeIntroduirDificultat() { System.out.print("Introdueix el nivell de dificultat de la partida: "); }
    public void missatgeEscullOpcio () { System.out.println("Escull una de les opcions: "); }
    public void missatgeIntroduirLletra() { System.out.println("1: introduir lletra"); };
    public void missatgeIntrodueixLletra() { System.out.print("Introdueix la lletra que dessitgis (en majúscula): "); }
    public void missatgeIntroduirParaula() {System.out.println("2: introduir paraula"); };
    public void missatgeIntrodueixParaula() { System.out.print("Introdueix la paraula que dessitgis (tota en majúscules): "); }

    public void errorNombreJugadors() { System.out.println("El número de jugador ha de ser minim 1 i màxim 4."); }
    public void errorDificultat() { System.out.println("La dificultat ha de ser com a minim 1 i com a màxim 3"); }

    public void perdedor() {
        System.out.println("S'han acabat les vides, partida perduda.");
    }
    public void guanyador(int idJugador){
        System.out.println("El guanyador és: " + idJugador + ".");
    }


    /*
    //escriu la lletra que ha encertat l'usuari.
    public void escriuLletra(char lletra){
        for(int i = 0; i<this.paraulaMisteiosa.length(); i++){
            if(espaisDesxifrats[i] == lletra){}

        }
    }*/


}
