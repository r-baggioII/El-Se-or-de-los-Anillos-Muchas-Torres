import java.util.Scanner;

public class Mapa {

    public CerroGloria cerroGloria;
    private int tamañoMapa = 17;
    private char[][] mapa = new char[tamañoMapa][tamañoMapa]; 

    public Mapa() {
        cerroGloria = new CerroGloria(0, 0); // Por defecto se coloca el cerro de la gloria en la posición (0,0)
    }

    // Getters and Setters

    // Devuelve el tamaño del mapa
    public int getTamañoMapa() {
        return tamañoMapa;
    }

    // Método para obtener un elemento del mapa
    public char getElemento(int x, int y) {
        return mapa[x][y];
    }

    // Método para establecer un elemento en el mapa
    public void setElemento(int x, int y, char elemento) {
        mapa[x][y] = elemento;
    }

    // Método para obtener el mapa completo
    public char[][] getMapa() {
        return mapa;
    }

    // Metodos
    public void iniciarMapa() {
        // Inicializar el mapa vacío
        for (int i = 0; i < this.tamañoMapa; i++) {
            for (int j = 0; j < this.tamañoMapa; j++) {
                // Dividir cuadrantes
                if (i == this.tamañoMapa / 2 || j == this.tamañoMapa / 2) {
                    mapa[i][j] = '*'; // Dividir verticalmente
                } else {
                    mapa[i][j] = '.'; // Resto del mapa
                }
            }
        }
        // Posición de la torre en el centro del mapa
        cerroGloria.posX = this.tamañoMapa / 2;
        cerroGloria.posY = this.tamañoMapa / 2;
        mapa[cerroGloria.getPosX()][cerroGloria.getPosY()] = 'T'; // 'T' representa la torre
    }

    // Método para imprimir el mapa
    public static void imprimirMapa(char[][] mapa) {
        // Limpiar la consola( ANSI escape sequences)
        for (int n = 0; n < mapa.length + 1; n++) {
            System.out.print(n % 10 + "  ");
        }
        System.out.println();

        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) % 10 + "  ");
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /*/*
     // Metodo para limpiar la pantalla o mover el cursor al principio
    public void clearScreen() {
        System.out.print("\r");
        for (int i = 0; i < 80; i++) {
            System.out.print(" ");
        }
        System.out.println();
    }
     */


}
