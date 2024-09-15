import java.awt.*;

/**
 * La clase Hobbit representa a un enemigo de tipo Hobbit en el juego.
 * Hereda de la clase Enemigo y tiene una habilidad especial que le permite absorber el daño recibido.
 * <p>Creado por William Miranda y Bagio Rocio.</p>
 */

public class Hobbit extends Enemigo {

    /**
     * Constructor que inicializa al Hobbit con su posición en el mapa y los valores predeterminados de salud, daño, rango de ataque y representación.
     *
     * @param posX la posición en el eje X del mapa.
     * @param posY la posición en el eje Y del mapa.
     */
    public Hobbit(int posX, int posY) {
        super(70, 2, 3, '\u26C7', posX, posY, 20, false);
    }

    /**
     * Activa o desactiva la habilidad especial del Hobbit.
     * Esta habilidad le permite absorber el daño recibido, recuperando parte de la salud perdida.
     */
    @Override
    public void activarHabilidadEspecial() {
        this.habilidadEnUso = !this.habilidadEnUso; // Bandera para saber si la habilidad del enemigo está activada o no
        absorberAtaque(20); // Las torres siempre hacen 25 de daño, el Hobbit absorbe 20 de ese daño
    }

    /**
     * Absorbe parte del daño recibido y recupera la salud del Hobbit.
     * Esta es la habilidad especial del Hobbit, activada mediante {@link #activarHabilidadEspecial()}.
     *
     * @param danioRecibido el daño que el Hobbit absorberá y añadirá a su salud.
     */
    public void absorberAtaque(int danioRecibido) {
        System.out.println("\033[0;33m" + "HOBBIT HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.salud += danioRecibido;
    }
}
