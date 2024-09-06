import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nivel {
    private int numeroNivel;
    private List<Oleada> oleadas;

    public Nivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
        this.oleadas = new ArrayList<>();
        configurarNivel();  // Configura las oleadas según el nivel
    }

    //Segun el numero de nivel crea una oleada con los enemigos apropiados
    public void configurarNivel() {
        Random rand = new Random();

        //Agrega a lista distintos tipos y cantidad de enemigos segun el nivel
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

    //Determina el tipo de enemigo según el numero
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


    private void agregarOleada(Oleada oleada) {
        oleadas.add(oleada);
    }

    public List<Oleada> getOleadas() {
        return oleadas;
    }

    public int getNivel() {
        return numeroNivel;
    }
}
