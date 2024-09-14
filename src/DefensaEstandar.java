/**
 * Clase abstracta que representa una defensa estándar en el juego.
 * Esta clase extiende de {@code Defensa} e incluye propiedades y métodos comunes
 * para todas las defensas estándar que pueden colocarse en el mapa y recibir ataques.
 */
public abstract class DefensaEstandar extends Defensa {
    /**
     * La resistencia de la defensa, que representa su vida.
     */
    public int resistencia;

    /**
     * El costo en puntos de magia para colocar la defensa.
     */
    public int costoMagia;

    /**
     * Constructor para inicializar una defensa estándar con su posición, símbolo, resistencia y costo mágico.
     *
     * @param posX           la posición en el eje X de la defensa.
     * @param posY           la posición en el eje Y de la defensa.
     * @param nombreDefensa  el símbolo que representa a la defensa en el mapa.
     * @param resistencia    la cantidad de vida de la defensa.
     * @param costoMagia     el costo en puntos de magia para colocar la defensa.
     */
    public DefensaEstandar(int posX, int posY, char nombreDefensa, int resistencia, int costoMagia) {
        super(posX, posY, nombreDefensa);
        this.resistencia = resistencia;
        this.costoMagia = costoMagia;
    }

    /**
     * Retorna la resistencia actual de la defensa.
     *
     * @return la resistencia de la defensa.
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     * Retorna el costo mágico de la defensa.
     *
     * @return el costo en puntos de magia.
     */
    public int getCosto() {
        return costoMagia;
    }

    /**
     * Verifica si la defensa ha sido destruida (cuando su resistencia es menor o igual a 0).
     *
     * @return {@code true} si la defensa ha sido destruida, {@code false} en caso contrario.
     */
    public boolean esEliminada() {
        return this.resistencia <= 0;
    }

    /**
     * Informa el estado de la defensa, indicando su posición y si ha sido destruida o no.
     * Muestra la resistencia restante si la defensa está activa.
     */
    public void informarEstado() {
        int newPosX = this.posX + 1;
        int newPosY = this.posY + 1;
        if (!esEliminada()) {
            System.out.println("Defensa " + this.nombreDefensa + " en (" + newPosX + ", " + newPosY + ") - VIDA: " + this.resistencia);
        } else {
            System.out.println("Defensa en (" + newPosX + ", " + newPosY + ") ha sido destruida.");
        }
    }

    /**
     * Reduce la resistencia de la defensa según el daño infligido por un enemigo.
     *
     * @param enemigo el enemigo que ataca a la defensa.
     */
    @Override
    public void recibirAtaque(Enemigo enemigo) {
        this.resistencia -= enemigo.getDanioAtaque();
    }

    /**
     * Método abstracto que verifica si un enemigo está en el rango de ataque de la defensa.
     *
     * @param enemigo el enemigo a evaluar.
     * @return {@code true} si el enemigo está en rango, {@code false} en caso contrario.
     */
    public abstract boolean enemigoEnRango(Enemigo enemigo);
}
