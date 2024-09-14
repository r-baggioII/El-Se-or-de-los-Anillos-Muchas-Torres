/**
 * La clase Enano representa a un enemigo de tipo Enano en el juego.
 * Hereda de la clase Enemigo y tiene una habilidad especial que le permite aumentar su rango de ataque.
 * <p>Creado por William Miranda y Bagio Rocio.</p>
 */
public class Enano extends Enemigo {

    /**
     * Constructor que inicializa al Enano con su posición en el mapa y los valores predeterminados
     * de salud, daño, rango de ataque y representación.
     *
     * @param posX la posición en el eje X del mapa.
     * @param posY la posición en el eje Y del mapa.
     */
    public Enano(int posX, int posY) {
        super(90, 2, 7, '\u2692', posX, posY, 5, false);
    }

    /**
     * Activa o desactiva la habilidad especial del Enano.
     * Esta habilidad le permite aumentar su rango de ataque.
     */
    @Override
    public void activarHabilidadEspecial() {
        this.habilidadEnUso = !this.habilidadEnUso; // Bandera para saber si la habilidad del enemigo está activada o no
        aumertarRangoAtaque(); // Aumenta el rango de ataque
    }

    /**
     * Aumenta el rango de ataque del Enano.
     * Esta es la habilidad especial del Enano, activada mediante {@link #activarHabilidadEspecial()}.
     */
    public void aumertarRangoAtaque() {
        System.out.println("\033[0;33m" + "ENANO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.rangoAtaque += 3; // Aumenta el rango de ataque en 3
    }
}
