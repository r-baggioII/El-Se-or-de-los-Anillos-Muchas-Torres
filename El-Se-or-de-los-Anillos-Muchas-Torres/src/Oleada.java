import java.util.Random;

public class Oleada {

    Random rand = new Random();
    Mapas maps = new Mapas();

    int size = maps.getSize();
    char[][] mapa = maps.getMapa();
    int torreX = maps.getTorreX();
    int torreY = maps.getTorreY();

    public Oleada(){}

    public void start() {
        int oleada=3;
        int cont=0;


        // Posición inicial del bot en una esquina (0, 0)
        int botX = 5;
        int botY = 0;
        this.mapa[botX][botY] = 'B'; // 'B' representa al bot enemigo

        // Ciclo de movimiento del bot
        while (botX != this.torreX || botY != this.torreY) {
            clearScreen();
            imprimirMapa(this.mapa);

            // Limpiar la posición anterior del bot
            this.mapa[botX][botY] = '.';

            // Mover el bot hacia la torre
            if (botX < this.torreX) {
                botX++;
            } else if (botX > this.torreX) {
                botX--;
            }

            if (botY < this.torreY) {
                botY++;
            } else if (botY > this.torreY) {
                botY--;
            }

            // Actualizar la nueva posición del bot
            if (botX == this.torreX && botY == this.torreY && cont!=oleada) {
                imprimirMapa(this.mapa);
                botX = this.rand.nextInt(0,this.size);
                botY = 0;
                cont++;
            }else{
                this.mapa[botX][botY] = 'B';
            }
            // Pausa para ver el movimiento
            try {
                Thread.sleep(1000); // Pausa de 500 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        imprimirMapa(this.mapa);
        System.out.println("¡El bot ha alcanzado la torre! "+cont+" veces");
    }

    // Método para imprimir el mapa
    public static void imprimirMapa(char[][] mapa) {
        // Limpiar la consola( ANSI escape sequences)
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // Metodo para limpiar la pantalla o mover el cursor al principio
    public void clearScreen() {
        System.out.print("\r");
        for (int i = 0; i < 80; i++) {
            System.out.print(" ");
        }
        System.out.println();
    }
}