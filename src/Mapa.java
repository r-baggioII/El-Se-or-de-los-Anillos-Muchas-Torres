
public class Mapa {

    private int cerroGloriaX;
    private int cerroGloriaY;
    public int tamañoMapa = 15;
    char[][] mapa = new char[tamañoMapa][tamañoMapa];

    Torre torre = new Torre();

    public Mapa() {}
    // Geters Y Seters
    public int getTamañoMapa(){
        return tamañoMapa;
    }
    public int getCerroGloriaX(){
        return this.cerroGloriaX;
    }
    public int getCerroGloriaY(){
        return this.cerroGloriaY;
    }
    public char[][] getMapa(){
        return mapa;
    }
    //Metodos

    public void iniciarMapa(){
        // Inicializar el mapa vacío
        for (int i = 0; i < this.tamañoMapa; i++) {
            for (int j = 0; j < this.tamañoMapa; j++) {
                // Dividir cuadrantes
                if (i == this.tamañoMapa/2 || j == this.tamañoMapa/2) {
                    mapa[i][j] = '*'; // Dividir verticalmente
                } else {
                    mapa[i][j] = '.'; // Resto del mapa
                }
            }
        }
        // Posición de la torre en el centro del mapa
        cerroGloriaX = tamañoMapa / 2;
        cerroGloriaY = tamañoMapa / 2;
        mapa[cerroGloriaX][cerroGloriaY] = 'T'; // 'T' representa la torre
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
