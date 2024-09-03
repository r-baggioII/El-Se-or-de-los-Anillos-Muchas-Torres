import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    //Atributos
    private List<DefensaEstandar> miTorres;
    private List<DefensaEstandar> miBarrera;
    private int nivelActual;
    public Mapa mapa; 
    private int puntosMagiaIniciales = 100; 
    private int puntosMagiaActuales;
    private final int puntosMagiaMaximos = 600;
    private List<Nivel> niveles;
    private static Scanner sc = new Scanner(System.in);

    // Constructor
    public Juego() {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();
        this.nivelActual = 1;
        this.mapa = new Mapa();
        this.puntosMagiaActuales = puntosMagiaIniciales;
        this.niveles = new ArrayList<>();
    }
    
    //Getters y Setters 
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
        // Nivel 1 con 1 oleada
        Nivel nivel1 = new Nivel(1);
        nivel1.agregarOleada(new Oleada());
        niveles.add(nivel1);

        // Nivel 2 con 2 oleadas
        Nivel nivel2 = new Nivel(2);
        nivel2.agregarOleada(new Oleada());
        nivel2.agregarOleada(new Oleada());
        niveles.add(nivel2);

        // Nivel 3 con 3 oleadas
        Nivel nivel3 = new Nivel(3);
        nivel3.agregarOleada(new Oleada());
        nivel3.agregarOleada(new Oleada());
        nivel3.agregarOleada(new Oleada());
        niveles.add(nivel3);
    }

    public void gastarPuntosMagia(int costo) {
        if (this.puntosMagiaActuales - costo < 0) {
            this.puntosMagiaActuales = 0; // No puede ser negativo (debería indicar al usuario que no puede comprar la defensa)
            System.out.println("No tienes suficientes puntos de magia para esta compra.");
        } else {
            this.puntosMagiaActuales -= costo;
            System.out.println("Compra realizada. Puntos de magia restantes: " + this.puntosMagiaActuales);
        }
    }


    // Metodo para Inicar juego
    public void iniciarJuego() {
        System.out.println("El Señor de los anillos: Muchas Morres");
        System.out.println("Bienvenido Al Juego ");
        mapa.iniciarMapa(); // Iniciar Mapa
        mapa.imprimirMapa(mapa.getMapa()); // Imprimir Mapa
        inicializarNiveles(); // Inicializar Niveles
        System.out.println("Nivel: "+this.nivelActual); // Mostrar Nivel
        System.out.println("Magia: "+this.puntosMagiaActuales);  // Mostrar puntos de Magia actuales 
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        iniciarDefensa(sc); // Iniciar Defensa
        sc.close();
        iniciarOleada(sc, miTorres, miBarrera);
    }

    public void iniciarDefensa(Scanner sc){
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
                int posY = sc.nextInt();
                System.out.print("> Y:");
                int posX = sc.nextInt();
                sc.nextLine(); 
    
                if (posX + 1 <= this.mapa.getTamañoMapa() && posY + 1 <= this.mapa.getTamañoMapa()) {
                    switch (opcion.toLowerCase()) {
                        case "t":
                            Torre torre = new Torre();
                            if (this.puntosMagiaActuales >= torre.getCosto()) {
                                this.gastarPuntosMagia(torre.getCosto());
                                torre.colocarEnMapa(this.mapa, posX - 1, posY - 1, 't');
                                this.miTorres.add(torre);
                            } else {
                                System.out.println("No hay suficiente Magia");
                            }
                            break;
                        case "b":
                            Barrera barrera = new Barrera();
                            if (this.puntosMagiaActuales >= barrera.getCosto()) {
                                this.gastarPuntosMagia(barrera.getCosto());
                                barrera.colocarEnMapa(this.mapa, posX - 1, posY - 1, 'b');
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
                    System.out.println("Coordenadas fuera de los límites");
                }
            } else {
                System.out.println("Opcion no valida. Intenta de nuevo.");
            }
            this.mapa.imprimirMapa(mapa.getMapa());
    
            System.out.println("Colocar Torre:          T ");
            System.out.println("Colocar Barreras:       B ");
            System.out.println("Finalizar colocación:   Q ");
        }
    }
    
    // Metodo para ver el estado del jugador
    public void estado(Nivel nivel,Defensa miTorre,Defensa miBarrera) {
        System.out.println("Magia actual: "+this.puntosMagiaActuales);
        System.out.println("Nivel actual: "+this.nivelActual);
    }

    public void iniciarOleada(Scanner sc, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera) {
        Oleada play = new Oleada(this.mapa);
        play.iniciarOleada(this.nivelActual, miTorres, miBarrera);
    }


    ///MAIN
    public static void main(String[] args) {
        Juego game = new Juego(); 
        game.iniciarJuego(); //solo llama al metodo iniciarJuego y Juego hace el resto
    }
}
