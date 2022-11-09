package Model;
import Vista.Dibuixar;

import java.util.ArrayList;
import java.util.Scanner;

public class Ranking {
    private ArrayList<Jugador> jugadors;
    public Dibuixar vista;

    public  Ranking() {
        vista = new Dibuixar();
    }

    public int CarregaPartidaONovaPartida() {
        vista.misstageCarregaPartidaONovaPartida();
        Scanner inputUser = new Scanner(System.in);

        int opcio;
        boolean sortir = false;
        do {
            //Demanem una de les dues opcions que té l'usuari
            opcio = inputUser.nextInt();
            if (opcio != 1 || opcio != 2)
                sortir = true;
            else
                vista.errorIntroduccoOpcio();
        } while (!sortir);

        return opcio;
    }

    public void guardarRanking() {

    }
    public void carregaRanking() {

    }

}
