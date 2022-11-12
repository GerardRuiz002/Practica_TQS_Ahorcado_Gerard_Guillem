package Model;

public class Jugador {
    private String nomJugador;
    private int puntuacio;
    private int tornAssignat;
    private int idJugador;

    public Jugador(int idJugador) {
        this.nomJugador = null;
        this.puntuacio = 0;
        this.idJugador = idJugador;
    }

    public String getNomJugador() {return nomJugador;}
    public int getPuntiacio() {return puntuacio;}
    public int getTornAssignat() {return tornAssignat;}

    public void setPuntuacio(int punts) { this.puntuacio += punts; }
    public void setTornAssignat(int torn) { this.tornAssignat = torn; }
    public void setNomJugador(String nom) { this.nomJugador = nom; }
}
