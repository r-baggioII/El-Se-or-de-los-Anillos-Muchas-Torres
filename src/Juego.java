import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private int nivelActual;
    public PuntajeMagico puntajeMagico;

    List<DefensaEstandar> miDefensas;
    private List<Nivel> niveles;
    public Mapa mapa;
    private static Scanner sc = new Scanner(System.in);

    public Juego() {
        this.miDefensas = new ArrayList<>();
        this.nivelActual = 1;
        this.mapa = new Mapa();
        this.niveles = new ArrayList<>();
        this.puntajeMagico = new PuntajeMagico();
    }

    public int getNivelActual() {
        return nivelActual;
    }
    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }
    private boolean chequearEstadoJuego() {
        return mapa.cerroGloria.getVidas() > 0;
    }

    private void inicializarNiveles() {
        Nivel nivel1 = new Nivel(1);
        niveles.add(nivel1);

        Nivel nivel2 = new Nivel(2);
        niveles.add(nivel2);

        Nivel nivel3 = new Nivel(3);
        niveles.add(nivel3);
    }

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
            System.out.println("Magia: " + puntajeMagico.getPuntosMagiaActuales());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

            iniciarDefensa();

            for (Oleada oleada : nivel.getOleadas()) {
                System.out.println("Cantidad de Oleadas en este nivel " + nivel.getOleadas().size());
                System.out.println("Iniciando oleada en Nivel: " + this.nivelActual);
                System.out.println("Tamaño de la oleada -->> " + oleada.enemigos.size());
                oleada.iniciarOleada(mapa,miDefensas,puntajeMagico);

                if (!chequearEstadoJuego()) {
                    System.out.println("Has perdido. El juego ha terminado.");
                    return;
                }
            }
            puntajeMagico.aumentarMagia(50); //Luego de cada nivel se otorgan al jugador 50 puntos
        }
        System.out.println("¡Felicidades! Has completado todos los niveles.");
    }

    public void iniciarDefensa() {
        boolean flag = true;
        do {
            System.out.println("Invocar Torre:          T");
            System.out.println("Invocar Barreras:       B");
            System.out.println("Finalizar Invocacion:   Q ");

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

                if (posX > 0 && posX < this.mapa.getTamañoMapa() && posY > 0 && posY < this.mapa.getTamañoMapa()) {
                    if (!mapa.verificarLugar(posX-1, posY-1)) {
                        switch (opcion) {
                            case "t":
                                Torre torre = new Torre(posX-1, posY-1, 100, 50);
                                if (puntajeMagico.getPuntosMagiaActuales() >= torre.getCosto()) {
                                    puntajeMagico.gastarPuntosMagia(torre.getCosto());
                                    torre.colocarEnMapa(this.mapa);
                                    this.miDefensas.add(torre);
                                } else {
                                    System.out.println("No hay suficiente Magia");
                                }
                                break;
                            case "b":
                                Barrera barrera = new Barrera(posX-1, posY-1, 50, 25);
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
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
            this.mapa.imprimirMapa(mapa.getMapa());
        } while (flag);
    }
    private void leerOpcionMenu() {
        int opcion;

        do {
            // Pedir al usuario que ingrese una opción
            System.out.print("\u001B[32m" + "Elige una opción (1-4): " + "\u001B[0m");
            opcion = sc.nextInt();
            sc.nextLine();

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
        } while (opcion != 4); // El menú sigue apareciendo hasta que el usuario elija la opción de salir
    }
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
        System.out.println("\u001B[36m" + " - Coloca hasta 5 elementos de defensa: torres o barreras.");
        System.out.println(" - Cada nivel tiene varias oleadas de enemigos que aumentan en dificultad.");
        System.out.println(" - Las torres atacan automáticamente a los enemigos cuando están en rango.");
        System.out.println(" - El objetivo es proteger la torre principal. Si cae, perderás.");
        System.out.println(" - Usa tus recursos con sabiduría y planifica tu defensa.");
        System.out.println("¡Buena suerte!" + "\u001B[0m");
    }
    public void mostrarHistoria() {

        System.out.println("\u001B[34m" + "El Señor de los Anillos: Muchas Morres" + "\u001B[0m"); //Azul

        System.out.print("\u001B[35m" + "----------------------------------------" + "\u001B[0m\n"); //púrpura

        System.out.println("\u001B[36m" + "En este reino, la última esperanza de la humanidad reside en la defensa de una antigua fortaleza en lo alto del cerro."); //cian
        System.out.println("El enemigo ha formado una poderosa alianza entre humanos, enanos, elfos y hobbits, dispuestos a conquistar el Cerro de la Gloria.");
        System.out.println("Tu misión es proteger la fortaleza utilizando torres y barreras estratégicamente para detener las oleadas de enemigos.");
        System.out.println("Si el cerro cae, el reino estará perdido para siempre.");
        System.out.println("¡Demuestra tu valía y defiende el Cerro de la Gloria a toda costa!" + "\u001B[0m");
    }
    private void iniciarJuego() {
        mostrarHistoria();
        mostrarMenuGuia();
        leerOpcionMenu();
    }
    public static void main(String[] args) {
        Juego game = new Juego();
        game.iniciarJuego();
    }
}