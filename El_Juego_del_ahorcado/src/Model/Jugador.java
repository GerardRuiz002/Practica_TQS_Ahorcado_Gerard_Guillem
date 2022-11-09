package Model;

public class Jugador {
    private int idJugador;
    private String nomJugador;
    private int puntuacio;
    private int tornAssignat;

    Jugador(int idJugador, String nomJugador) {
        this.idJugador = idJugador;
        this.nomJugador = nomJugador;
    }

    public int getIdJugador() {return idJugador;}
    public String getNomJugador() {return nomJugador;}
}
