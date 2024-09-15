/**
 * Clase que gestiona el puntaje mágico en el juego.
 * El puntaje mágico se utiliza para realizar compras y otras acciones dentro del juego.
 * @author William Miranda, Rocio Bagio
 * @version 2.0 Primero fue llamado CLASE Magia
 */
public class PuntajeMagico {
    /**
     * Cantidad inicial de puntos de magia disponibles.
     */
    private int puntosMagiaIniciales = 150;

    /**
     * Cantidad actual de puntos de magia disponibles.
     */
    private int puntosMagiaActuales;

    /**
     * Constructor para inicializar el puntaje mágico con la cantidad inicial.
     */
    public PuntajeMagico() {
        this.puntosMagiaActuales = puntosMagiaIniciales;
    }

    /**
     * Disminuye la cantidad de puntos de magia actuales al realizar una compra.
     * Si los puntos de magia actuales son insuficientes para cubrir el costo, se ajusta a 0.
     *
     * @param costo la cantidad de puntos de magia a gastar.
     */
    public void gastarPuntosMagia(int costo) {
        if (this.puntosMagiaActuales - costo < 0) {
            this.puntosMagiaActuales = 0;
            System.out.println("No tienes suficientes puntos de magia para esta compra.");
        } else {
            this.puntosMagiaActuales -= costo;
            System.out.println("Compra realizada. Puntos de magia restantes: " + this.puntosMagiaActuales);
        }
    }

    /**
     * Obtiene la cantidad actual de puntos de magia disponibles.
     *
     * @return la cantidad actual de puntos de magia.
     */
    public int getPuntosMagiaActuales() {
        return puntosMagiaActuales;
    }

    /**
     * Aumenta la cantidad de puntos de magia actuales.
     *
     * @param magia la cantidad de puntos de magia a añadir.
     */
    public void aumentarMagia(int magia) {
        this.puntosMagiaActuales += magia;
    }
}
