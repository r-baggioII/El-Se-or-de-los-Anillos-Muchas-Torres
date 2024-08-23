public class Mapas {

    public int cerroGloriaX;
    public int cerroGloriaY;
    int tamañoMapa = 15;
    char[][] mapa = new char[tamañoMapa][tamañoMapa];

    Torres torre = new Torres();

    public Mapas() {}

    public void iniciarMapa(){
        // Inicializar el mapa vacío
        for (int i = 0; i < tamañoMapa; i++) {
            for (int j = 0; j < tamañoMapa; j++) {
                mapa[i][j] = '*';
            }
        }
        // Posición de la torre en el centro del mapa
        cerroGloriaX = tamañoMapa / 2;
        cerroGloriaY = tamañoMapa / 2;
        mapa[cerroGloriaX][cerroGloriaY] = 'T'; // 'T' representa la torre
    }

    public int getSize(){
        return tamañoMapa;
    }
    public int getTorreX(){
        return cerroGloriaX;
    }
    public int getTorreY(){
        return cerroGloriaY;
    }
    public char[][] getMapa(){
        return mapa;
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
