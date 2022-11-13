package Model;
import Vista.Dibuixar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ranking {
    private Jugador[] jugadors = null;
    public Dibuixar vista;

    public void setJugadors(Jugador[] jugadors) { this.jugadors = jugadors; }

    public  Ranking() {
        vista = new Dibuixar();
    }


    public void mostraRanking(){
        vista.mostraRanking(jugadors);
    }

    public String CarregaPartidaONovaPartida() {
        vista.misstageCarregaPartidaONovaPartida();
        Scanner inputUser = new Scanner(System.in);

        String opcio;
        boolean sortir = false;
        do {
            //Demanem una de les dues opcions que té l'usuari
            opcio = inputUser.next();
            if (opcio.equals("1") || opcio.equals("2"))
                sortir = true;
            else
                vista.errorIntroduccoOpcio();
        } while (!sortir);

        return opcio;
    }

    public boolean guardarRanking(String pathArxiu, Serializable objecte) {
        boolean sw = false;
        try (FileOutputStream fos = new FileOutputStream(pathArxiu);
                ObjectOutputStream sortida = new ObjectOutputStream(fos); ) {
            sortida.writeObject(objecte);
            sw = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sw;
    }

    public static <E> E carregaRanking(String pathArxiu, Class<E> casseObjecte) {
        E objecte = null;
        try (FileInputStream fis = new FileInputStream(pathArxiu);
                ObjectInputStream entrada = new ObjectInputStream(fis)) {
            objecte = (E) entrada.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objecte;
    }



}
