/**
 * Clase abstracta que representa un enemigo en el juego.
 * Los enemigos tienen atributos como salud, rango de ataque, recompensa, posición, daño de ataque, y representación en el mapa.
 * Además, pueden recibir ataques y activar habilidades especiales.
 *
 * @author William Miranda, Rocio Bagio
 * @version 1.4
 */
public abstract class Enemigo {

    /**
     * Rango de ataque del enemigo.
     */
    protected int rangoAtaque;

    /**
     * Salud del enemigo.
     */
    protected int salud;

    /**
     * Recompensa otorgada al eliminar al enemigo.
     */
    protected int recompensa;

    /**
     * Posición en el eje X en el mapa.
     */
    protected int posX;

    /**
     * Posición en el eje Y en el mapa.
     */
    protected int posY;

    /**
     * Daño de ataque del enemigo.
     */
    protected int danioAtaque;

    /**
     * Representación del enemigo en el mapa (carácter).
     */
    protected char representacion;

    /**
     * Bandera que indica si la habilidad especial del enemigo está activa.
     */
    protected boolean habilidadEnUso;

    /**
     * Constructor para inicializar un enemigo con todos sus atributos.
     *
     * @param saludInicial Salud inicial del enemigo.
     * @param rangoAtaque Rango de ataque del enemigo.
     * @param recompensa Recompensa por eliminar al enemigo.
     * @param representacion Carácter que representa al enemigo en el mapa.
     * @param inicialX Posición inicial en el eje X.
     * @param inicialY Posición inicial en el eje Y.
     * @param danioAtaque Daño de ataque del enemigo.
     * @param habilidadEnUso Indica si la habilidad especial está activa.
     */
    public Enemigo(int saludInicial, int rangoAtaque, int recompensa, char representacion, int inicialX, int inicialY, int danioAtaque, boolean habilidadEnUso) {
        this.salud = saludInicial;
        this.rangoAtaque = rangoAtaque;
        this.recompensa = recompensa;
        this.posX = inicialX;
        this.posY = inicialY;
        this.representacion = representacion;
        this.danioAtaque = danioAtaque;
        this.habilidadEnUso = habilidadEnUso;
    }

    // Setters y Getters

    /**
     * Establece la salud del enemigo.
     *
     * @param salud Salud a establecer.
     */
    public void setSalud(int salud) {
        this.salud = salud;
    }

    /**
     * Obtiene la recompensa por eliminar al enemigo.
     *
     * @return Recompensa del enemigo.
     */
    public int getRecompensa() {
        return recompensa;
    }

    /**
     * Obtiene la posición en el eje X del enemigo.
     *
     * @return Posición en el eje X.
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Establece la posición en el eje X del enemigo.
     *
     * @param posX Posición en el eje X a establecer.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Obtiene la posición en el eje Y del enemigo.
     *
     * @return Posición en el eje Y.
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Establece la posición en el eje Y del enemigo.
     *
     * @param posY Posición en el eje Y a establecer.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Obtiene la representación del enemigo en el mapa.
     *
     * @return Representación del enemigo.
     */
    public char getRepresentacion() {
        return representacion;
    }

    /**
     * Obtiene el daño de ataque del enemigo.
     *
     * @return Daño de ataque del enemigo.
     */
    public int getDanioAtaque() {
        return danioAtaque;
    }

    /**
     * Permite al enemigo recibir un ataque de una torre, reduciendo su salud.
     *
     * @param torre Torre que realiza el ataque.
     */
    public void recibirAtaque(Torre torre) {
        this.salud -= torre.getPoderAtaque();
    }

    /**
     * Permite al enemigo lanzar un ataque a una defensa.
     *
     * @param defensa Defensa objetivo del ataque.
     */
    public void lanzarAtaque(Defensa defensa) {
        int newPosX = this.posX + 1; // Se le suma uno a la posición para mostrar al usuario
        int newPosY = this.posY + 1;
        System.out.println("El enemigo " + this.representacion + " en la posición (" + newPosX + ", " + newPosY + ") ha atacado a la defensa " + defensa.getClass().getSimpleName() + " infligiendo " + this.danioAtaque + " de daño.");
    }

    /**
     * Informa el estado actual del enemigo, incluyendo su posición y salud.
     */
    public void informarEstado() {
        int newPosX = this.posX + 1;
        int newPosY = this.posY + 1;
        if (!esEliminado()) {
            System.out.println("Enemigo " + this.representacion + " en (" + newPosX + ", " + newPosY + ") - VIDA: " + this.salud);
        } else {
            System.out.println("Enemigo " + this.representacion + " en (" + newPosX + ", " + newPosY + ") eliminado.");
        }
    }

    /**
     * Verifica si una defensa está dentro del rango de ataque del enemigo.
     *
     * @param defensa Defensa a verificar.
     * @return {@code true} si la defensa está en el rango de ataque, {@code false} en caso contrario.
     */
    public boolean defensaEnRango(Defensa defensa) {
        int distanciaX = Math.abs(this.posX - defensa.getPosX());
        int distanciaY = Math.abs(this.posY - defensa.getPosY());
        return distanciaX <= this.rangoAtaque && distanciaY <= this.rangoAtaque;
    }

    /**
     * Activa la habilidad especial del enemigo.
     * Este método debe ser implementado por las clases derivadas.
     */
    public abstract void activarHabilidadEspecial();

    /**
     * Verifica si el enemigo ha sido eliminado.
     *
     * @return {@code true} si la salud del enemigo es menor o igual a 0, {@code false} en caso contrario.
     */
    public boolean esEliminado() {
        return salud <= 0;
    }

    /**
     * Mueve al enemigo hacia la posición de la torre en el mapa.
     * Si el enemigo está cerca de la torre, se ajusta su posición para estar directamente en ella.
     * Si el camino está bloqueado, busca una ruta alternativa.
     *
     * @param mapa Mapa en el que el enemigo se mueve.
     * @param torreX Posición en el eje X de la torre.
     * @param torreY Posición en el eje Y de la torre.
     */
    public void moverHacia(Mapa mapa, int torreX, int torreY) {
        int distanciaX = Math.abs(posX - torreX);
        int distanciaY = Math.abs(posY - torreY);

        if (distanciaX < 2 && distanciaY < 2) {
            posX = torreX;
            posY = torreY;
        } else {
            if (posX < torreX && mapa.getElemento(posX + 1, posY) == '.') {
                posX++;
            } else if (posX > torreX && mapa.getElemento(posX - 1, posY) == '.') {
                posX--;
            } else if (posY < torreY && mapa.getElemento(posX, posY + 1) == '.') {
                posY++;
            } else if (posY > torreY && mapa.getElemento(posX, posY - 1) == '.') {
                posY--;
            } else {
                if (mapa.getElemento(posX + 1, posY) == '.' && posX < mapa.getTamañoMapa() - 1) {
                    posX++;
                } else if (mapa.getElemento(posX - 1, posY) == '.' && posX > 0) {
                    posX--;
                } else if (mapa.getElemento(posX, posY + 1) == '.' && posY < mapa.getTamañoMapa() - 1) {
                    posY++;
                } else if (mapa.getElemento(posX, posY - 1) == '.' && posY > 0) {
                    posY--;
                }
            }
        }
    }
}
