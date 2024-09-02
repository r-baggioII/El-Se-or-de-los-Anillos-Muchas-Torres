import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private List<Defensa> miTorres;
    private List<Defensa> miBarrera;

    public Juego() {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();
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

        // Colocar Torres y defensa
        boolean flag = true;
        while (flag) {
            System.out.println("Colocar Torre: t ");
            System.out.println("Colocar Barreras: b ");
            System.out.println("Finalizar colocación: q ");
            String opcion = sc.nextLine().toLowerCase(); // Convert to lowercase to avoid case sensitivity

            if (opcion.equals("q")) {
                flag = false; // Set flag to false to exit the loop
                System.out.println("Colocación finalizada.");
            } else {
                System.out.println("Posición de la torre:");
                int posX = sc.nextInt();
                int posY = sc.nextInt();
                sc.nextLine(); // Consume the newline character after the integers

                if (posX + 1 <= maps.getTamañoMapa() && posY + 1 <= maps.getTamañoMapa()) {
                    switch (opcion) {
                        case "t":
                            Torre torre = new Torre();
                            torre.colocarTorre(maps, magia, posX-1, posY-1, 't');
                            game.miTorres.add(torre);
                            break; // Add break to prevent fall-through

                        case "b":
                            break; // Add break to prevent fall-through
                            //Barrera defensa = new Barrera(70, 25, posX, posY);
                            //defensa.colocarEnMapa(maps.getMapa());
                            //game.miBarrera.add(defensa);


                        default:
                            System.out.println("Opción no válida. Intenta de nuevo.");
                            break;
                    }
                } else {
                    System.out.println("Coordenadas fuera de los límites");
                }
            }
        }

        // Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.Start(maps, nivel, game.miTorres, game.miBarrera);
        sc.close();
    }
}
