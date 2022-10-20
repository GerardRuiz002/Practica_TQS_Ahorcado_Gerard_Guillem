import  java.util.*;

public class Ahorcado {
    int nJugadors;
    int tornJugadorId;
    int videsDisponibles;
    String paraulaMisteriosa;
    char lletresUtilitzades[];
    char lletresDisponibles[];
    int nivellDificultat;


    public Ahorcado(int numJugadors, int nivellDificultat) {
        this.nJugadors = numJugadors;
        this.nivellDificultat = nivellDificultat;
        this.lletresDisponibles = new char[26];
    }

    public void inicialitzaLletresDisponibles() {
        for (int i = 0; i < 26; i++) {
            this.lletresDisponibles[i] = (char) (65 + i);
        }
    }
/*String b[] = new String[9];*/
}
