import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * Clase que representa el juego principal. Maneja la lógica del juego, incluyendo la inicialización de niveles,
 * el manejo de defensas, la interacción con el usuario, y el estado del juego.
 * @author William Miranda, Rocio Baggio
 * @see <a src="https://www.campusmvp.es/recursos/post/como-cambiar-los-colores-de-la-consola-con-java-y-system-out-println.aspx">Aqui podras encontrar informcacion de los colores</>
 *
 */
public class Juego {
    /**
     * Nivel actual en el juego.
     */
    private int nivelActual;

    /**
     * Puntaje mágico del jugador, utilizado para invocar defensas.
     */
    public PuntajeMagico puntajeMagico;

    /**
     * Lista de defensas actuales en el mapa.
     */
    List<DefensaEstandar> miDefensas;

    /**
     * Mapa del juego, que contiene el estado actual del juego.
     */
    public Mapa mapa;

    /**
     * Lista de niveles del juego.
     */
    private List<Nivel> niveles;

    /**
     * Scanner para la entrada del usuario.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructor que inicializa el juego, creando un mapa, niveles, y puntaje mágico.
     */
    public Juego() {
        this.miDefensas = new ArrayList<>();
        this.nivelActual = 1;
        this.mapa = new Mapa();
        this.niveles = new ArrayList<>();
        this.puntajeMagico = new PuntajeMagico();
    }

    /**
     * Verifica si el juego continúa basado en la vida del cerro de la gloria.
     *
     * @return Verdadero si el cerro de la gloria tiene vidas restantes, falso de lo contrario.
     */
    private boolean chequearEstadoJuego() {
        return mapa.cerroGloria.getVidas() > 0;
    }
    /**
     * Inicializa los niveles del juego.
     */
    private void inicializarNiveles() {
        Nivel nivel1 = new Nivel(1);
        niveles.add(nivel1);

        Nivel nivel2 = new Nivel(2);
        niveles.add(nivel2);

        Nivel nivel3 = new Nivel(3);
        niveles.add(nivel3);
    }
    /**
     * Comienza el juego, inicializando el mapa, mostrando los niveles y manejando las oleadas de enemigos.
     */
    private void empezarJuego(){
        char torre = '\u2656';    // ♖
        char barrera = '\u2592';  // ▒
        char enano = '\u2692';    // ⚒
        char elfo = '\u2694';     // ⚔
        char humano = '\u263C';   // ☼
        char hobbit = '\u26C7';   // ⚇

        System.out.println("Torre: " + torre);
        System.out.println("Barrera: " + barrera);
        System.out.println("Enano: " + enano);
        System.out.println("Elfo: " + elfo);
        System.out.println("Humano: " + humano);
        System.out.println("Hobbit: " + hobbit);

        mapa.iniciarMapa();
        mapa.imprimirMapa(mapa.getMapa());

        inicializarNiveles();

        for (Nivel nivel : niveles) {
            this.nivelActual = nivel.getNivel();
            System.out.println("Nivel: " + this.nivelActual);
            System.out.println("Puntos de Magia: " + puntajeMagico.getPuntosMagiaActuales());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
            mapa.cerroGloria.setVidas(5); //resetear las vidas del cerro de la gloria a 5 luego de cada nivel
            iniciarDefensa();

            for (Oleada oleada : nivel.getOleadas()) {
                System.out.println("Cantidad de Oleadas en este nivel " + nivel.getOleadas().size());
                System.out.println("Iniciando oleada en Nivel: " + this.nivelActual);
                System.out.println("Tamaño de la oleada -->> " + oleada.enemigos.size());
                oleada.iniciarOleada(mapa,miDefensas,puntajeMagico);

                if (!chequearEstadoJuego()) {
                    System.out.println("Has perdido. El juego ha terminado.");
                    System.exit(0); //Salir del juego automáticamente si el jugador pierde
                }
            }
            puntajeMagico.aumentarMagia(50); //Luego de cada nivel se otorgan al jugador 50 puntos
        }
        System.out.println("¡Felicidades! Has completado todos los niveles.");
    }
    /**
     * Permite al usuario invocar torres o barreras en el mapa.
     */
    public void iniciarDefensa() {
        boolean flag = true;
        do {
            System.out.println("Invocar Torre:          T" + " - Costo: 50");
            System.out.println("Invocar Barreras:       B" + " - Costo: 25");
            System.out.println("Finalizar Invocacion:   Q ");


            System.out.print(">");
            String opcion = sc.nextLine().toLowerCase();
            if (opcion.equals("q")) {
                System.out.println("Invocacion Exitosa.");
                break;
            } else if (opcion.equals("t") || opcion.equals("b")) {
                try {
                    System.out.println("Cordenadas de la Defensa:");
                    System.out.print("> X:");
                    int posX = sc.nextInt();
                    System.out.print("> Y:");
                    int posY = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer

                    if (posX > 0 && posX < this.mapa.getTamañoMapa() && posY > 0 && posY < this.mapa.getTamañoMapa()) {
                        if (!mapa.verificarLugar(posX - 1, posY - 1)) {
                            switch (opcion) {
                                case "t":
                                    Torre torre = new Torre(posX - 1, posY - 1, 100, 50);
                                    if (puntajeMagico.getPuntosMagiaActuales() >= torre.getCosto()) {
                                        puntajeMagico.gastarPuntosMagia(torre.getCosto());
                                        torre.colocarEnMapa(this.mapa);
                                        this.miDefensas.add(torre);
                                    } else {
                                        System.out.println("No hay suficiente Magia");
                                    }
                                    break;
                                case "b":
                                    Barrera barrera = new Barrera(posX - 1, posY - 1, 75, 25);
                                    if (puntajeMagico.getPuntosMagiaActuales() >= barrera.getCosto()) {
                                        puntajeMagico.gastarPuntosMagia(barrera.getCosto());
                                        barrera.colocarEnMapa(this.mapa);
                                        this.miDefensas.add(barrera);
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
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Asegúrate de ingresar un número.");
                    sc.nextLine(); // Limpiar el buffer en caso de excepción
                }
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
            this.mapa.imprimirMapa(mapa.getMapa());
        } while (flag);
    }

    /**
     * Lee la opción del menú seleccionada por el usuario y realiza la acción correspondiente.
     */
    private void leerOpcionMenu() {
        int opcion = -1; // Iniciar con un valor que no sea válido para entrar en el bucle

        do {
            try {
                // Pedir al usuario que ingrese una opción
                System.out.print("\u001B[32m" + "Elige una opción (1-4): " + "\u001B[0m");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                // Procesar la opción seleccionada
                switch (opcion) {
                    case 1:
                        // Comenzar el juego
                        System.out.println("\u001B[33m" + "Iniciando el juego..." + "\u001B[0m");
                        empezarJuego();
                        break;
                    case 2:
                        // Mostrar historia
                        System.out.println("\u001B[34m" + "Mostrando la historia..." + "\u001B[0m");
                        mostrarHistoria();
                        break;
                    case 3:
                        // Mostrar guía
                        System.out.println("\u001B[36m" + "Mostrando la guía..." + "\u001B[0m");
                        mostrarMenuGuia();
                        break;
                    case 4:
                        // Salir
                        System.out.println("\u001B[31m" + "Saliendo del juego. ¡Hasta la próxima!" + "\u001B[0m");
                        break;
                    default:
                        // Opción no válida
                        System.out.println("\u001B[31m" + "Opción no válida. Por favor, elige entre 1 y 4." + "\u001B[0m");
                }
            } catch (InputMismatchException e) {
                // Si el usuario ingresa algo que no es un número
                System.out.println("\u001B[31m" + "Entrada no válida. Debes ingresar un número entre 1 y 4." + "\u001B[0m");
                sc.nextLine(); // Limpiar el buffer en caso de excepción
            }
        } while (opcion != 4);
    }

    /**
     * Muestra el menú de guía al jugador.
     */
    private void mostrarMenuGuia() {
        // Título del menú en verde
        System.out.println("\u001B[32m" + "========== MENÚ ==========" + "\u001B[0m");

        // Opciones del menú en amarillo
        System.out.println("\u001B[33m" + "1. Comenzar Juego" + "\u001B[0m");
        System.out.println("\u001B[33m" + "2. Ver Historia" + "\u001B[0m");
        System.out.println("\u001B[33m" + "3. Ver Guía" + "\u001B[0m");
        System.out.println("\u001B[33m" + "4. Salir" + "\u001B[0m");

        // Línea de separación en púrpura
        System.out.println("\u001B[35m" + "==========================" + "\u001B[0m");

        // Título de la guía en azul
        System.out.println("\u001B[34m" + "Guía del Jugador:" + "\u001B[0m");

        // Contenido de la guía en cian
        System.out.println("\u001B[36m" + " - Coloca elementos de defensa utilizando los puntos de magia : torres o barreras.");
        System.out.println(" - Cada nivel tiene varias oleadas de enemigos que aumentan en dificultad.");
        System.out.println(" - Las torres atacan automáticamente a los enemigos cuando están en rango.");
        System.out.println(" - El objetivo es proteger la torre principal. Si cae, perderás.");
        System.out.println(" - Usa tus recursos con sabiduría y planifica tu defensa.");
        System.out.println("¡Buena suerte!" + "\u001B[0m");
    }
    /**
     * Muestra la historia del juego al jugador.
     */
    private void mostrarHistoria() {

        System.out.println("\u001B[34m" + "El Señor de los Anillos: Muchas Morres" + "\u001B[0m"); //Azul

        System.out.print("\u001B[35m" + "----------------------------------------" + "\u001B[0m\n"); //púrpura

        System.out.println("\u001B[36m" + "En este reino, la última esperanza de la humanidad reside en la defensa de una antigua fortaleza en lo alto del cerro."); //cian
        System.out.println("El enemigo ha formado una poderosa alianza entre humanos, enanos, elfos y hobbits, dispuestos a conquistar el Cerro de la Gloria.");
        System.out.println("Tu misión es proteger la fortaleza utilizando torres y barreras estratégicamente para detener las oleadas de enemigos.");
        System.out.println("Si el cerro cae, el reino estará perdido para siempre.");
        System.out.println("¡Demuestra tu valía y defiende el Cerro de la Gloria a toda costa!" + "\u001B[0m");
    }
    /**
     * Inicia el juego mostrando el menú de guía y luego lee la opción seleccionada por el usuario.
     */
    private void iniciarJuego() {
        mostrarMenuGuia();
        leerOpcionMenu();
    }
    /**
     * Método principal que crea una instancia del juego e inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        Juego game = new Juego();
        game.iniciarJuego();
    }
}