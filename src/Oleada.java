import java.util.Random;
import Enemigos.Humano;

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
        int oleada = nivel.getNivelActual()*2;
        int cont=0;

        // Enemigo cololado en el mapa
        Humano humano = new Humano(0,0);

        this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion(); // '' representa al bot enemigo

        // Ciclo de movimiento del bot
        while (humano.getPosX() != this.torreX || humano.getPosY() != this.torreY) {

            maps.clearScreen();
            maps.imprimirMapa(this.mapa);

            // Limpiar la posición anterior del bot
            this.mapa[humano.getPosX()][humano.getPosY()] = '.';

            // Mover el bot hacia la torre

            humano.moverHacia(this.torreX,this.torreY);

            // Actualizar la nueva posición del bot
            if (humano.getPosX() == this.torreX && humano.getPosY() == this.torreY && cont!=oleada) {
                maps.imprimirMapa(this.mapa);
                System.out.println("Bot a atacado a Torre");

                new Humano(0, this.size);

                cont++;
                this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion(); // 'B' representa al bot enemigo
            }else{
                this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion();
            }

            // Pausa para ver el movimiento
            try {
                Thread.sleep(1000); // Pausa de 500 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("¡El bot ha alcanzado la torre "+cont+" veces!");
    }


}