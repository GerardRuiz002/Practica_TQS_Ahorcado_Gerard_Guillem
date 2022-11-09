package Model;
import Vista.Dibuixar;

import java.io.*;
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

    public static boolean guardarRanking(String pathArxiu, Serializable objecte) {
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
