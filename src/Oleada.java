import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import Enemigos.*;

public class Oleada {

    Random rand = new Random();
    char[][] mapa;
    int size ;
    int torreX;
    int torreY;

    public Oleada(Mapas maps){
        this.mapa = maps.getMapa();
        this.size = maps.getTamañoMapa();
        this.torreX = maps.getCerroGloriaX();
        this.torreY = maps.getCerroGloriaY();

    }

    public void Start(Mapas maps,Nivel nivel) {
        int oleada = nivel.getNivelActual() * 2;
        int cont = 0;

        // Generar enemigos al inicio de la oleada
        for (int i = 0; i < oleada; i++) {
            enemigos.add(new Humano(0, 0));  // Colocar a los enemigos en la posición inicial
        }

        while (!enemigos.isEmpty()) {
            maps.clearScreen();
            maps.imprimirMapa(this.mapa);

            List<Humano> eliminados = new ArrayList<>();
            for (Humano humano : enemigos) {
                this.mapa[humano.getPosX()][humano.getPosY()] = '.';

                humano.moverHacia(this.torreX, this.torreY);

                if (humano.getPosX() == this.torreX && humano.getPosY() == this.torreY) {
                    cont++;
                    System.out.println("El humano ha atacado a la torre.");
                    eliminados.add(humano);  // Marcar para eliminar
                } else {
                    this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion();
                }
            }

            enemigos.removeAll(eliminados);  // Eliminar los enemigos que llegaron a la torre

            try {
                Thread.sleep(1000); // Pausa para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("¡El bot ha alcanzado la torre " + cont + " veces!");
    }
}