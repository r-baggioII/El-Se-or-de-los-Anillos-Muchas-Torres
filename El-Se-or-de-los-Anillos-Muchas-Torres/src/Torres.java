public class Torres {
    private int posX;
    private int posY;

    // Constructor para inicializar la posición de la torre
    public Torres() {}

    public void coocarTorre(int x, int y, char[][] mapa, int torrePrincipalX, int torrePrincipalY) {
        if (x == torrePrincipalX && y == torrePrincipalY) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        }
        this.posX = x;
        this.posY = y;
        colocarTorre(mapa);
    }

    // Método para colocar la torre en el mapa
    private void colocarTorre(char[][] mapa) {
        mapa[posX][posY] = 'T'; // 'T' representa una torre adicional
    }

    // Métodos getters para obtener la posición de la torre
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}