package Model;

import java.io.IOException;
import java.util.ArrayList;

public class MockParaulesDisponibles {

    private ArrayList<String> paraulesDisponibles = new ArrayList<>();
    private String paraulaMisteriosa;

    public MockParaulesDisponibles(int dificultat) throws IOException { //simula únicament l'existència d'una paraula. (ja que no podem accedir al diccionari de moment!)
        paraulaMisteriosa = "CASAS";                                    //(eliminem el factor aleatorietat)
        this.paraulesDisponibles.add(paraulaMisteriosa);
    }

    public String getParaulaMisteriosa() {
       return paraulaMisteriosa;
    }  //retorna la paraula que ja sabem que es "CASAS"

    public ArrayList<String> llegirTxt(String textFilePath) throws IOException { //retornem arrayList amb "CASAS"
        ArrayList<String> paraula = null;
        paraula.add("CASAS");
        return  paraula;
    }
}
