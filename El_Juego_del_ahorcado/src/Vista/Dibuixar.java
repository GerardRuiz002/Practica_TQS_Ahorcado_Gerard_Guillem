package Vista;

public class Dibuixar {
    public void imprimirVidesDisponebles(int videsDisponibles) {
        System.out.println(videsDisponibles);
    }

    public void errorCaracterNoValid() {
        System.out.println("Caràcter no valid. Introdueix únicament lletres majuscules.");
    }

    public void errorLletraUtilitzada() {
        System.out.println("Aquesta lletra ja ha estat utilitzada.");
    }

    public void errorLletraNoValida() {
        System.out.println("Aquesta lletra no correspon a cap lletra de la paraula misteriosa.");
    }

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
