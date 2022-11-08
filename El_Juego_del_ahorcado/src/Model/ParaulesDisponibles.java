package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ParaulesDisponibles {
    private ArrayList<String> paraulesDisponibles;
    private String paraulaMisteriosa;

    public ParaulesDisponibles(int dificultat) throws IOException {
        if (dificultat == 1)
            this.paraulesDisponibles = llegirTxt("src/Text/facil.txt");
        else if (dificultat == 2)
            this.paraulesDisponibles = llegirTxt("src/Text/normal.txt");
        else if (dificultat == 3)
            this.paraulesDisponibles = llegirTxt("src/Text/dificil.txt");

        //Seleccionem una paraula misteriosa random
        Random rand = new Random();
        int randomIndex = rand.nextInt(this.paraulesDisponibles.size());
        this.paraulaMisteriosa = paraulesDisponibles.get(randomIndex);
    }

    public String getParaulaMisteriosa() { return paraulaMisteriosa; }

    public ArrayList<String> llegirTxt(String textFilePath) throws IOException {
        ArrayList<String> paraulesLlegides = new ArrayList<>();
        File arxiu = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            arxiu = new File(textFilePath);
            fr = new FileReader(arxiu);
            br = new BufferedReader(fr);

            //Lectura del fitxer
            String linea;
            while ((linea = br.readLine()) != null) {
                paraulesLlegides.add(linea);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return paraulesLlegides;
    }

}
