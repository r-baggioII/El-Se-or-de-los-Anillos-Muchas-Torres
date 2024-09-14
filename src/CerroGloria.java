/**
 * Clase que representa el Cerro de la Gloria, la defensa principal en el juego.
 * Esta clase extiende de {@code Defensa} y tiene un contador de vidas que indica cuántos ataques puede resistir.
 */
public class CerroGloria extends Defensa {
    /**
     * La cantidad de vidas del Cerro de la Gloria. Cada ataque reduce una vida.
     */
    public int vidas = 5;

    /**
     * Constructor que inicializa la posición del Cerro de la Gloria en el mapa.
     *
     * @param posX la posición en el eje X del Cerro de la Gloria.
     * @param posY la posición en el eje Y del Cerro de la Gloria.
     */
    public CerroGloria(int posX, int posY) {
        super(posX, posY, '\u26EB'); // El símbolo que representa el Cerro de la Gloria en el mapa.
    }

    /**
     * Obtiene la cantidad de vidas restantes del Cerro de la Gloria.
     *
     * @return el número de vidas actuales.
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Establece un nuevo valor para las vidas del Cerro de la Gloria.
     *
     * @param vidas el número de vidas que se va a establecer.
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * Reduce una vida del Cerro de la Gloria cuando recibe un ataque de un enemigo.
     *
     * @param enemigo el enemigo que ataca al Cerro de la Gloria.
     */
    @Override
    public void recibirAtaque(Enemigo enemigo) {
        this.vidas -= 1; // Cada vez que el Cerro recibe un ataque, pierde una vida.
    }
}
