import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Defensa> miTorres;
    private List<Defensa> miBarrera;

    public Main() {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main game = new Main(); // Create an instance of Main to access instance variables
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
            String opcion = sc.nextLine();
            System.out.println("Posicion de la torre:");
            int posX = sc.nextInt();
            int posY = sc.nextInt();
            sc.nextLine(); // Consume the newline character after the integers

            if (posX + 1 <= maps.getTamañoMapa() && posY + 1 <= maps.getTamañoMapa()) {
                switch (opcion.toLowerCase()) {
                    case "t":
                        Torre torre = new Torre();
                        torre.colocarTorre(maps, magia, 0, 0, 'I');
                        game.miTorres.add(torre);
                        break; // Add break to prevent fall-through

                    case "b":
                        Barrera defensa = new Barrera(70, 25, posX, posY);
                        defensa.colocarEnMapa(maps.getMapa());
                        game.miBarrera.add(defensa);
                        break; // Add break to prevent fall-through

                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                        break;
                }
            } else {
                System.out.println("Coordenadas fuera de los límites");
            }
        }

        // Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.Start(maps, nivel, game.miTorres, game.miBarrera);
    }
}
