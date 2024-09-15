/**
 * La clase Elfo representa a un enemigo de tipo Elfo en el juego.
 * Hereda de la clase Enemigo y tiene una habilidad especial que le permite curarse a sí mismo.
 * <p>Creado por William Miranda y Bagio Rocio.</p>
 */
public class Elfo extends Enemigo {

    /**
     * Constructor que inicializa al Elfo con su posición en el mapa y los valores predeterminados
     * de salud, daño, rango de ataque y representación.
     *
     * @param posX la posición en el eje X del mapa.
     * @param posY la posición en el eje Y del mapa.
     */
    public Elfo(int posX, int posY) {
        super(120, 4, 10, '\u2694', posX, posY, 15, false);
    }

    /**
     * Activa o desactiva la habilidad especial del Elfo.
     * Esta habilidad le permite curarse a sí mismo.
     */
    @Override
    public void activarHabilidadEspecial() {
        this.habilidadEnUso = !this.habilidadEnUso; // Bandera para saber si la habilidad del enemigo está activada o no
        activarCuración(); // Llama a la función que ejecuta la curación
    }

    /**
     * Habilidad especial del Elfo que le permite curarse a sí mismo al recuperar 10 puntos de salud.
     */
    public void activarCuración() {
        System.out.println("\033[0;33m" + "ELFO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.salud += 10; // El Elfo recupera 10 puntos de salud
    }
}
