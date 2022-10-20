import org.junit.Test;

import static org.junit.Assert.*;


public class AhorcadoTest {
    @Test
    public void inicialitzaLletresDisponibles() {
        //Test de inicialització de les lletres disponibles
        Ahorcado ahorcadoIniciLletres = new Ahorcado(0,0);
        ahorcadoIniciLletres.inicialitzaLletresDisponibles();
        char resultat[] = ahorcadoIniciLletres.lletresDisponibles;
        char lletresEsperades[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z'};
        assertArrayEquals(lletresEsperades, resultat);
    }
}