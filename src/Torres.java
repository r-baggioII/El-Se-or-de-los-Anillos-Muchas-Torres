public class Torres {
    private int vidaInicial;
    private int rangoAtaque;
    private String nombreTorre;
    private int posX;
    private int posY;

    // Constructor para inicializar la posición de la torre
    public Torres() {}

    public void colocarTorre(Mapas maps,int x, int y) {

        if (x == maps.getCerroGloriaX() && y == maps.getCerroGloriaY() ) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        }
        this.posX = x;
        this.posY = y;
        colocarTorre(maps.getMapa());
    }

    // Método para colocar la torre en el mapa
    private void colocarTorre(char[][] mapa) {
        mapa[posX][posY] = '?'; // '?' representa una torre adicional
    }

    // Métodos getters para obtener la posición de la torre
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getVidaInicial() {return vidaInicial;}


}