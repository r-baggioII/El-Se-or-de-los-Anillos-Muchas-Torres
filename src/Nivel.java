public class Nivel {
    int nivelActual;
    int magiaActual;

    public Nivel(int nivelActual, int magiaActual) {
        this.nivelActual = nivelActual;
        this.magiaActual = magiaActual;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getMagiaActual() {
        return magiaActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public void setMagiaActual(int magiaActual) {
        this.magiaActual = magiaActual;
    }
}
