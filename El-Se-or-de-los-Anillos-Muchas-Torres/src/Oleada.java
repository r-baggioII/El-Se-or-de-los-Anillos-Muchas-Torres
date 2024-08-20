import java.util.Random;

public class Oleada {

    Random rand = new Random();
    public Oleada(){}

    public void start(char[][] mapa, int size) {
        int oleada=3;
        int cont=0;
        // Inicializar el mapa vacío
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapa[i][j] = '*';
            }
        }

        // Posición de la torre en el centro del mapa
        int torreX = size / 2-3;
        int torreY = size / 2+4;
        mapa[torreX][torreY] = 'T'; // 'T' representa la torre

        // Posición inicial del bot en una esquina (0, 0)
        int botX = 5;
        int botY = 0;
        mapa[botX][botY] = 'B'; // 'B' representa al bot enemigo

        // Ciclo de movimiento del bot
        while (botX != torreX || botY != torreY) {
            clearScreen();
            imprimirMapa(mapa);

            // Limpiar la posición anterior del bot
            mapa[botX][botY] = '.';

            // Mover el bot hacia la torre
            if (botX < torreX) {
                botX++;
            } else if (botX > torreX) {
                botX--;
            }

            if (botY < torreY) {
                botY++;
            } else if (botY > torreY) {
                botY--;
            }

            // Actualizar la nueva posición del bot
            if (botX == torreX && botY == torreY && cont!=oleada) {
                imprimirMapa(mapa);
                botX = rand.nextInt(0,size);
                botY = 0;
                cont++;
            }else{
            mapa[botX][botY] = 'B';
            }
            // Pausa para ver el movimiento
            try {
                Thread.sleep(1000); // Pausa de 500 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        imprimirMapa(mapa);
        System.out.println("¡El bot ha alcanzado la torre!"+cont+"veces");
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