package Model;

public class Jugador {
    private String nomJugador;
    private int puntuacio;
    private int tornAssignat;

    public Jugador(String nomJugador) {
        this.nomJugador = nomJugador;
        this.puntuacio = 0;
    }

    public String getNomJugador() {return nomJugador;}

    public void setPuntuacio(int punts) { this.puntuacio += punts; }
    public void setTornAssignat(int torn) { this.tornAssignat = torn; }
}
