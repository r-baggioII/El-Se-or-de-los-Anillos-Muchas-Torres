public class Magia {
    private int puntosMagiaIniciales = 100;
    private int puntosMagiaActuales;
    private final int puntosMagiaMaximos = 600;
    public Magia() {
        this.puntosMagiaActuales = puntosMagiaIniciales;
    }

    public void gastarPuntosMagia(int costo) {
        if (this.puntosMagiaActuales - costo < 0) {
            this.puntosMagiaActuales = 0;
            System.out.println("No tienes suficientes puntos de magia para esta compra.");
        } else {
            this.puntosMagiaActuales -= costo;
            System.out.println("Compra realizada. Puntos de magia restantes: " + this.puntosMagiaActuales);
        }
    }

    public int getPuntosMagiaActuales() {
        return puntosMagiaActuales;
    }

    public void aumentarMagia(int magia){
        this.puntosMagiaActuales += magia;
    }
}
