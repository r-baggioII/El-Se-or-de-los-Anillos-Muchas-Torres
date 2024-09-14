/**
 * Clase abstracta que representa una defensa en el juego.
 * Las defensas tienen una posición en el mapa y un símbolo que las representa.
 * Las subclases deben implementar el método {@code recibirAtaque} para definir cómo una defensa maneja los ataques de los enemigos.
 */
public abstract class Defensa {
    /**
     * La posición en el eje X de la defensa.
     */
    public int posX;

    /**
     * La posición en el eje Y de la defensa.
     */
    public int posY;

    /**
     * El símbolo que representa la defensa en el mapa.
     */
    public char nombreDefensa;

    /**
     * Constructor para inicializar la posición y el símbolo de la defensa.
     *
     * @param posX la posición en el eje X de la defensa.
     * @param posY la posición en el eje Y de la defensa.
     * @param nombreDefensa el símbolo que representa la defensa en el mapa.
     */
    public Defensa(int posX, int posY, char nombreDefensa) {
        this.posX = posX;
        this.posY = posY;
        this.nombreDefensa = nombreDefensa;
    }

    /**
     * Obtiene la posición en el eje X de la defensa.
     *
     * @return la posición en el eje X.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Obtiene la posición en el eje Y de la defensa.
     *
     * @return la posición en el eje Y.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Obtiene el símbolo que representa la defensa en el mapa.
     *
     * @return el símbolo de la defensa.
     */
    public char getNombreDefensa() {
        return nombreDefensa;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases para definir cómo la defensa maneja los ataques de los enemigos.
     *
     * @param enemigo el enemigo que está atacando la defensa.
     */
    public abstract void recibirAtaque(Enemigo enemigo);
}
