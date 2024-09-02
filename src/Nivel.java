import java.util.ArrayList;
import java.util.List;

public class Nivel {
    int numeroNivel;
    private List<Oleada> oleadas;

    public Nivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
        this.oleadas = new ArrayList<>();
    }

    public int getNivelActual() {
        return numeroNivel;
    }

    public void setNivelActual(int nivelActual) {
        this.numeroNivel = nivelActual;
    }

    public void agregarOleada(Oleada oleada) {
        oleadas.add(oleada);
    }
    /*
     *   public void iniciarNivel() {
        System.out.println("Iniciando Nivel " + nivelActual);
        for (Oleada oleada : oleadas) {
            oleada.start();
    }   } 
     */
 
}
