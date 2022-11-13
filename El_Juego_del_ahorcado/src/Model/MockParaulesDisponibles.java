package Model;

import java.io.IOException;
import java.util.ArrayList;

public class MockParaulesDisponibles {

    private ArrayList<String> paraulesDisponibles = new ArrayList<>();
    private String paraulaMisteriosa;

    public MockParaulesDisponibles(int dificultat) throws IOException {
        paraulaMisteriosa = "CASAS";
        this.paraulesDisponibles.add(paraulaMisteriosa);
    }

    public String getParaulaMisteriosa() {
       return paraulaMisteriosa;
    }

    public ArrayList<String> llegirTxt(String textFilePath) throws IOException {
        ArrayList<String> paraula = null;
        paraula.add("CASAS");
        return  paraula;
    }
}
