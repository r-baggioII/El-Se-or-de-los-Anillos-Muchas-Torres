/**
 * La clase Barrera es una defensa estática en el juego que hereda de la clase DefensaEstandar.
 * Se puede colocar en el mapa para bloquear el avance de los enemigos.
 * <p>Implementa la interfaz ColocableEnMapa para ubicarse en posiciones específicas del mapa.</p>
 */
public class Barrera extends DefensaEstandar implements ColocableEnMapa {

    /**
     * Constructor que inicializa una barrera con una posición, resistencia y costo de magia específicos.
     *
     * @param posX        la posición en el eje X del mapa.
     * @param posY        la posición en el eje Y del mapa.
     * @param resistencia la resistencia de la barrera.
     * @param costoMagia  el costo de magia para colocar la barrera.
     */
    public Barrera(int posX, int posY, int resistencia, int costoMagia) {
        super(posX, posY, '\u2592', resistencia, costoMagia); // Llama al constructor de DefensaEstandar
    }

    /**
     * Constructor por defecto de Barrera que inicializa con valores predeterminados.
     */
    public Barrera() {
        super(0, 0, '\u2592', 0, 0); // Llama al constructor de DefensaEstandar con valores predeterminados
    }

    /**
     * Coloca la barrera en el mapa, verificando que la posición sea válida.
     * No se permite colocar la barrera en la misma posición que la torre principal ni en las fronteras de los reinos.
     *
     * @param mapa el mapa del juego en el que se coloca la barrera.
     * @throws IllegalArgumentException si se intenta colocar la barrera en una posición no válida.
     */
    @Override
    public void colocarEnMapa(Mapa mapa) {
        if (this.posX == mapa.cerroGloria.getPosX() && this.posY == mapa.cerroGloria.getPosY()) {
            throw new IllegalArgumentException("No se puede colocar una barrera en la misma posición que la torre principal.");
        } else if (this.posX == mapa.getTamañoMapa() / 2 || this.posY == mapa.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una barrera en la misma posición que las fronteras de los cuatro reinos.");
        }
        // Coloca la barrera en el mapa
        mapa.setElemento(this.posX, this.posY, this.nombreDefensa);
    }

    /**
     * Verifica si un enemigo se encuentra en el rango de la barrera.
     * El rango de la barrera es de 1 casilla de distancia en cualquier dirección (horizontal, vertical o diagonal).
     *
     * @param enemigo el enemigo cuyo rango se quiere verificar.
     * @return true si el enemigo está en el rango de la barrera, false de lo contrario.
     */
    @Override
    public boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(enemigo.getPosX() - this.posX);
        int distanciaY = Math.abs(enemigo.getPosY() - this.posY);
        return (distanciaY == 1 && distanciaX == 0) || (distanciaY == 0 && distanciaX == 1) || (distanciaY == 1 && distanciaX == 1);
    }
}
