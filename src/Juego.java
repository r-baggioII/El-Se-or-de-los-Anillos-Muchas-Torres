import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {

    private List<DefensaEstandar> miTorres;
    private List<DefensaEstandar> miBarrera;
    private int nivelActual;
    public Mapa mapa;
    private int puntosMagiaIniciales = 100;
    private int puntosMagiaActuales;
    private final int puntosMagiaMaximos = 600;
    private List<Nivel> niveles;
    private static Scanner sc = new Scanner(System.in);

    public Juego() {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();
        this.nivelActual = 1;
        this.mapa = new Mapa();
        this.puntosMagiaActuales = puntosMagiaIniciales;
        this.niveles = new ArrayList<>();
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int getPuntosMagiaActuales() {
        return puntosMagiaActuales;
    }

    private void inicializarNiveles() {
        Nivel nivel1 = new Nivel(1);
        nivel1.agregarOleada(new Oleada());
        niveles.add(nivel1);

        Nivel nivel2 = new Nivel(2);
        nivel2.agregarOleada(new Oleada());
        nivel2.agregarOleada(new Oleada());
        niveles.add(nivel2);

        Nivel nivel3 = new Nivel(3);
        nivel3.agregarOleada(new Oleada());
        nivel3.agregarOleada(new Oleada());
        nivel3.agregarOleada(new Oleada());
        niveles.add(nivel3);
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

    public void iniciarJuego() {
        System.out.println("El Señor de los Anillos: Muchas Morres");
        System.out.println("Bienvenido al Juego");

        mapa.iniciarMapa();
        mapa.imprimirMapa(mapa.getMapa());

        inicializarNiveles();

        for (Nivel nivel : niveles) {
            this.nivelActual = nivel.getNivel();
            System.out.println("Nivel: " + this.nivelActual);
            System.out.println("Magia: " + this.puntosMagiaActuales);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

            iniciarDefensa();

            for (Oleada oleada : nivel.getOleadas()) {
                System.out.println("Iniciando oleada en Nivel: " + this.nivelActual);

                oleada.iniciarOleada(mapa, this.nivelActual, miTorres, miBarrera);

                if (!chequearEstadoJuego()) {
                    System.out.println("Has perdido. El juego ha terminado.");
                    return;
                }
            }

            this.puntosMagiaActuales += 10;
        }

        System.out.println("¡Felicidades! Has completado todos los niveles.");
    }

    private boolean chequearEstadoJuego() {
        return mapa.cerroGloria.getVidas() > 0;
    }

    public void iniciarDefensa() {
        boolean flag = true;
        System.out.println("Invocar Torre:          T ");
        System.out.println("Invocar Barreras:       B ");
        System.out.println("Finalizar Invocacion:   Q ");
        while (flag) {
            System.out.print(">");
            String opcion = sc.nextLine().toLowerCase();
            if (opcion.equals("q")) {
                System.out.println("Invocacion Exitosa.");
                break;
            } else if (opcion.equals("t") || opcion.equals("b")) {
                System.out.println("Cordenadas de la Defensa:");
                System.out.print("> X:");
                int posX = sc.nextInt();
                System.out.print("> Y:");
                int posY = sc.nextInt();
                sc.nextLine();

                if (posX >= 0 && posX < this.mapa.getTamañoMapa() && posY >= 0 && posY < this.mapa.getTamañoMapa()) {
                    if (verificarPosicion(posX-1, posY-1)) {
                        switch (opcion) {
                            case "t":
                                Torre torre = new Torre(posX-1, posY-1, 50, 25);
                                if (this.puntosMagiaActuales >= torre.getCosto()) {
                                    this.gastarPuntosMagia(torre.getCosto());
                                    torre.colocarEnMapa(this.mapa);
                                    this.miTorres.add(torre);
                                } else {
                                    System.out.println("No hay suficiente Magia");
                                }
                                break;
                            case "b":
                                Barrera barrera = new Barrera(posX-1, posY-1, 50, 25);
                                if (this.puntosMagiaActuales >= barrera.getCosto()) {
                                    this.gastarPuntosMagia(barrera.getCosto());
                                    barrera.colocarEnMapa(this.mapa);
                                    this.miBarrera.add(barrera);
                                } else {
                                    System.out.println("No hay suficiente Magia");
                                }
                                break;
                            default:
                                System.out.println("Opción no válida. Intenta de nuevo.");
                                break;
                        }
                    } else {
                        System.out.println("Coordenadas ocupadas por otro elemento de defensa.");
                    }
                } else {
                    System.out.println("Coordenadas fuera de los límites.");
                }
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
            this.mapa.imprimirMapa(mapa.getMapa());

            System.out.println("Colocar Torre:          T ");
            System.out.println("Colocar Barreras:       B ");
            System.out.println("Finalizar colocación:   Q ");
        }
    }

    public boolean verificarPosicion(int x, int y) {
        return this.mapa.getElemento(x, y) == '.'; // Si está desocupada la posición devuelve verdadero
    }

    public void estado(Nivel nivel, Defensa miTorre, Defensa miBarrera) {
        System.out.println("Magia actual: " + this.puntosMagiaActuales);
        System.out.println("Nivel actual: " + this.nivelActual);
    }

    public void iniciarOleada() {
        Oleada play = new Oleada();
        play.iniciarOleada(mapa, this.nivelActual, miTorres, miBarrera);
    }

    public static void main(String[] args) {
        Juego game = new Juego();
        game.iniciarJuego();
    }
}
