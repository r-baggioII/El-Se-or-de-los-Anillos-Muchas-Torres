import java.util.List;
/**
 * Clase que representa el mapa del juego. Contiene el cerro de la gloria y un mapa bidimensional
 * que se utiliza para mostrar el estado del juego, incluyendo la colocación de defensas y enemigos.
 * @author William Miranda, Rocio Bagio
 * @version 1.3
 */
public class Mapa {

    /**
     * Cerro de la gloria, una defensa especial en el mapa.
     */
    public CerroGloria cerroGloria;

    /**
     * Tamaño del mapa.
     */
    private int tamañoMapa = 17;

    /**
     * Mapa bidimensional que representa el estado del juego.
     */
    private char[][] mapa = new char[tamañoMapa][tamañoMapa];

    /**
     * Constructor que inicializa el cerro de la gloria en la posición (0, 0).
     */
    public Mapa() {
        cerroGloria = new CerroGloria(0, 0); // Por defecto se coloca el cerro de la gloria en la posición (0,0)
    }

    // Getters and Setters

    /**
     * Devuelve el tamaño del mapa.
     *
     * @return Tamaño del mapa.
     */
    public int getTamañoMapa() {
        return tamañoMapa;
    }

    /**
     * Obtiene un elemento del mapa en la posición especificada.
     *
     * @param x Coordenada X en el mapa.
     * @param y Coordenada Y en el mapa.
     * @return Elemento en la posición especificada.
     */
    public char getElemento(int x, int y) {
        return mapa[x][y];
    }

    /**
     * Establece un elemento en el mapa en la posición especificada.
     *
     * @param x Coordenada X en el mapa.
     * @param y Coordenada Y en el mapa.
     * @param elemento Elemento a establecer.
     */
    public void setElemento(int x, int y, char elemento) {
        mapa[x][y] = elemento;
    }

    /**
     * Obtiene el mapa completo.
     *
     * @return Mapa bidimensional.
     */
    public char[][] getMapa() {
        return mapa;
    }

    // Métodos

    /**
     * Inicializa el mapa vacío y coloca el cerro de la gloria en el centro del mapa.
     */
    public void iniciarMapa() {
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
        // Posición del cerro de la gloria en el centro del mapa
        cerroGloria.posX = this.tamañoMapa / 2;
        cerroGloria.posY = this.tamañoMapa / 2;
        mapa[cerroGloria.getPosX()][cerroGloria.getPosY()] = cerroGloria.getNombreDefensa();
    }

    /**
     * Verifica si una posición en el mapa está ocupada.
     *
     * @param posX Coordenada X en el mapa.
     * @param posY Coordenada Y en el mapa.
     * @return Verdadero si la posición está ocupada, falso si está libre.
     */
    public boolean verificarLugar(int posX, int posY) {
        return this.mapa[posX][posY] != '.';
    }

    /**
     * Quita las defensas eliminadas del mapa.
     *
     * @param miDefensasEliminados Lista de defensas eliminadas a quitar del mapa.
     */
    public void quitarDefensas(List<DefensaEstandar> miDefensasEliminados) {
        for (DefensaEstandar defensaEstandar : miDefensasEliminados) {
            this.mapa[defensaEstandar.getPosX()][defensaEstandar.getPosY()] = '.';
        }
    }

    /**
     * Imprime el mapa en la consola con colores para diferentes tipos de elementos.
     *
     * @param mapa Mapa bidimensional a imprimir.
     */
    public static void imprimirMapa(char[][] mapa) {
        // Limpiar la consola (ANSI escape sequences)
        for (int n = 0; n < mapa.length + 1; n++) {
            System.out.print(n % 10 + "  ");
        }
        System.out.println();

        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) % 10 + "  ");
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '.') {
                    System.out.print("\u001B[32m" + mapa[i][j] + "  " + "\u001B[0m");
                } else if (mapa[i][j] == '*') {
                    System.out.print("\u001B[31m" + mapa[i][j] + "  " + "\u001B[0m");
                } else if (mapa[i][j] == '\u2656') {
                    System.out.print("\u001B[35m" + mapa[i][j] + "  " + "\u001B[0m");
                } else if (mapa[i][j] == '\u2592') {
                    System.out.print("\u001B[36m" + mapa[i][j] + "  " + "\u001B[0m");
                } else {
                    System.out.print(mapa[i][j] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
