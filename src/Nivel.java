import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa un nivel en el juego, compuesto por varias oleadas de enemigos.
 * El nivel se configura según su número, con diferentes tipos y cantidades de enemigos en cada oleada.
 * @author William Miranda, Rocio Bagio
 * @version 1.2
 */
public class Nivel {
    /**
     * Número del nivel.
     */
    private int numeroNivel;

    /**
     * Lista de oleadas para este nivel.
     */
    private List<Oleada> oleadas;

    /**
     * Constructor para inicializar el nivel con un número específico.
     * Configura las oleadas según el número del nivel.
     *
     * @param numeroNivel Número del nivel a crear.
     */
    public Nivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
        this.oleadas = new ArrayList<>();
        configurarNivel();  // Configura las oleadas según el nivel
    }

    /**
     * Configura el nivel agregando oleadas de enemigos según el número del nivel.
     */
    public void configurarNivel() {
        Random rand = new Random();

        switch (numeroNivel) {
            case 1:
                for (int i = 0; i < 1; i++) {  // Solo 1 oleada en el nivel 1
                    List<Enemigo> tiposDeEnemigos = new ArrayList<>();
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(2))); // Agrega dos enemigos de tipo Humano o Enano
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(2)));
                    Oleada oleada = new Oleada(tiposDeEnemigos);
                    oleadas.add(oleada);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {  // 2 oleadas en el nivel 2
                    List<Enemigo> tiposDeEnemigos = new ArrayList<>();
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(3))); // Agrega 3 enemigos de tipo Humano, Enano, Elfo
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(3)));
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(3)));
                    Oleada oleada = new Oleada(tiposDeEnemigos);
                    oleadas.add(oleada);
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {  // 3 oleadas en el nivel 3
                    List<Enemigo> tiposDeEnemigos = new ArrayList<>();
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(4))); // Agrega 4 enemigos de tipo Humano, Enano, Elfo o Hobbit
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(4)));
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(4)));
                    tiposDeEnemigos.add(determinaTipoEnemigo(rand.nextInt(4)));
                    Oleada oleada = new Oleada(tiposDeEnemigos);
                    oleadas.add(oleada);
                }
                break;
        }
    }

    /**
     * Determina el tipo de enemigo basado en un número aleatorio.
     *
     * @param randomDigit Número aleatorio para determinar el tipo de enemigo.
     * @return El enemigo correspondiente al número aleatorio.
     */
    public Enemigo determinaTipoEnemigo(int randomDigit) {
        switch (randomDigit) {
            case 0:
                return new Humano(0, 0);
            case 1:
                return new Enano(0, 0);
            case 2:
                return new Elfo(0, 0);
            case 3:
                return new Hobbit(0, 0);
        }
        return null;
    }

    /**
     * Agrega una oleada al nivel.
     *
     * @param oleada Oleada a agregar.
     */
    private void agregarOleada(Oleada oleada) {
        oleadas.add(oleada);
    }

    /**
     * Obtiene la lista de oleadas del nivel.
     *
     * @return Lista de oleadas.
     */
    public List<Oleada> getOleadas() {
        return oleadas;
    }

    /**
     * Obtiene el número del nivel.
     *
     * @return Número del nivel.
     */
    public int getNivel() {
        return numeroNivel;
    }
}
