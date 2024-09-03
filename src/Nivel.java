import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private int numeroNivel;  // Cambiado a private para mayor encapsulación
    private List<Oleada> oleadas;

    public Nivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
        this.oleadas = new ArrayList<>();
    }

    // Getter para el número de nivel
    public int getNivel() {
        return numeroNivel;
    }

    // Método para agregar una oleada al nivel
    public void agregarOleada(Oleada oleada) {
        oleadas.add(oleada);
    }

    // Getter para obtener la lista de oleadas
    public List<Oleada> getOleadas() {
        return oleadas;
    }
}
