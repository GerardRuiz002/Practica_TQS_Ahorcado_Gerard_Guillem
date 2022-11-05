package Model;

import java.io.*;
import java.util.ArrayList;

public class ParaulesDisponibles {
    private ArrayList<String> paraulesDisponibles;

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
