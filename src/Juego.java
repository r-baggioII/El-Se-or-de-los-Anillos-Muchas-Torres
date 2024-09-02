import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Juego {
    //Atributos
    private List<Defensa> miTorres;
    private List<Defensa> miBarrera;
    // Constructor
    public Juego() {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();
    }
    //Metodos
    // Metodo para Inicar juego
    public void iniciarJuego(Scanner sc,Mapa maps,Nivel nivel,Magia magia) {
        System.out.println("El Señor de los anillos: Muchas Morres");
        System.out.println("Bienvenido Al Juego ");
        maps.imprimirMapa(maps.getMapa());
        System.out.println("Nivel: "+nivel.getNivelActual());
        System.out.println("Magia: "+nivel.getMagiaActual());
    }
    // Metodo para Colocar Defensa
    public void iniciarDefensa(Scanner sc,Mapa maps,Magia magia){
        boolean flag = true;
        System.out.println("Invocar Torre:          T ");
        System.out.println("Invocar Barreras:       B ");
        System.out.println("Finalizar Invocacion:   Q ");
        while (flag) {
            System.out.print(">");
            String opcion = sc.nextLine().toLowerCase(); // Convert to lowercase to avoid case sensitivity
            if (opcion.equals("q")){
                System.out.println("Invocacion Exitosa.");
                break; // Set flag to false to exit the loop

            } else if (opcion.equals("t") || opcion.equals("b")) {
                System.out.println("Cordenadas de la Defensa:");
                System.out.print("> X:");
                int posY = sc.nextInt();
                System.out.print("> Y:");
                int posX = sc.nextInt();
                sc.nextLine(); // Consume the newline character after the integers
                if (posX + 1 <= maps.getTamañoMapa() && posY + 1 <= maps.getTamañoMapa()) {
                    switch (opcion.toLowerCase()) {
                        case "t":
                            Torre torre = new Torre();
                            if (magia.getMagiaActual() >= torre.getCosto()) {
                                torre.colocarEnMapa(maps, magia, posX - 1, posY - 1, 't');
                                this.miTorres.add(torre);
                            } else {
                                System.out.println("No hay sificiente Magia");
                            }
                            break; // Add break to prevent fall-through
                        case "b":
                            Barrera barrera = new Barrera();
                            if (magia.getMagiaActual() >= barrera.getCosto()) {
                                barrera.colocarEnMapa(maps, magia, posX - 1, posY - 1, 'b');
                                this.miBarrera.add(barrera);
                            } else {
                                System.out.println("No hay sificiente Magia");
                            }
                            break; // Add break to prevent fall-through
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
            maps.imprimirMapa(maps.getMapa());

            //System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Colocar Torre:          T ");
            System.out.println("Colocar Barreras:       B ");
            System.out.println("Finalizar colocación:   Q ");

        }

    }
    // Metodo para ver el estado del jugador
    public void estado(Nivel nivel, Mapa maps, Magia magia,Defensa miTorre,Defensa miBarrera) {
        System.out.println("Magia actual: "+magia.getMagiaActual());
        System.out.println("Nivel actual: "+nivel.getNivelActual());
    }

    public static void main(String[] args) {
        Juego game = new Juego(); // Create an instance of Main to access instance variables
        Scanner sc = new Scanner(System.in);
        // Iniciar Nivel
        Nivel nivel = new Nivel(1, 100);
        // Iniciar Magia
        Magia magia = new Magia();
        // Elejir Mapa
        Mapa maps = new Mapa();
        maps.iniciarMapa();
        // Interacion con el jugador
        game.iniciarJuego(sc,maps,nivel,magia);
        game.iniciarDefensa(sc,maps,magia);
        // Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.start(maps, nivel, game.miTorres, game.miBarrera);
        sc.close();
    }

}
