/**
 * La clase Torre representa una defensa en el juego que ataca a los enemigos dentro de su rango.
 * Extiende la clase DefensaEstandar e implementa la interfaz ColocableEnMapa.
 * @author William Miranda, Rocio Bagio
 * @version 1.2
 */
public class Torre extends DefensaEstandar implements ColocableEnMapa {
    private int rangoAtaque;
    private int poderAtaque;

    /**
     * Constructor para inicializar una torre con una posición específica, resistencia y costo de magia.
     * El rango de ataque predeterminado es 4 y el poder de ataque es 20.
     *
     * @param posX        la posición en el eje X del mapa.
     * @param posY        la posición en el eje Y del mapa.
     * @param resistencia la resistencia de la torre.
     * @param costoMagia  el costo de magia para colocar la torre.
     */
    public Torre(int posX, int posY, int resistencia, int costoMagia) {
        super(posX, posY, '\u2656', resistencia, costoMagia);
        this.rangoAtaque = 4;
        this.poderAtaque = 20;
    }

    /**
     * Constructor por defecto que inicializa una torre con valores predeterminados.
     * La posición se establece en (0,0), el rango de ataque es 4, y el poder de ataque es 20.
     */
    public Torre() {
        super(0, 0, '\u2656', 0, 0);
        this.rangoAtaque = 4;
        this.poderAtaque = 20;
    }

    /**
     * Obtiene el poder de ataque de la torre.
     *
     * @return el poder de ataque de la torre.
     */
    public int getPoderAtaque() {
        return poderAtaque;
    }

    /**
     * Lanza un ataque a un enemigo dentro del rango, infligiendo daño igual al poder de ataque de la torre.
     *
     * @param enemigo el enemigo al que se ataca.
     */
    public void lanzarAtaque(Enemigo enemigo) {
        int newPosX = this.posX + 1; // Ajuste visual de la posición
        int newPosY = this.posY + 1;
        System.out.println("La torre " + this.nombreDefensa + " en la posición " + "(" + newPosX + " , " +  newPosY + ")" +
                " ha atacado al enemigo " + enemigo.getClass().getSimpleName() +
                " infligiendo " + this.poderAtaque + " de daño.");
    }

    /**
     * Verifica si un enemigo está dentro del rango de ataque de la torre.
     * El rango de la torre es de 4 casillas en cualquier dirección.
     *
     * @param enemigo el enemigo cuya posición se desea verificar.
     * @return true si el enemigo está dentro del rango de ataque, false de lo contrario.
     */
    @Override
    public boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        return distanciaX <= rangoAtaque && distanciaY <= rangoAtaque;
    }

    /**
     * Coloca la torre en el mapa, asegurándose de que no esté en la misma posición que la torre principal o en las fronteras de los reinos.
     *
     * @param mapa el mapa en el que se coloca la torre.
     * @throws IllegalArgumentException si se intenta colocar la torre en una posición no permitida.
     */
    @Override
    public void colocarEnMapa(Mapa mapa) {
        if (this.posX == mapa.cerroGloria.getPosX() && this.posY == mapa.cerroGloria.getPosY()) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (this.posX == mapa.getTamañoMapa() / 2 || this.posY == mapa.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las fronteras de los cuatro reinos.");
        }
        // Coloca la torre en el mapa
        mapa.setElemento(this.posX, this.posY, this.nombreDefensa); // Representación gráfica de la torre
    }
}
