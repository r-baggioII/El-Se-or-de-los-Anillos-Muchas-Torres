public class main {
    public static void main(String[] args) {
        // Tamaño del mapa
        int size = 15;
        char[][] mapa = new char[size][size];
        Oleada play = new Oleada();
        play.start(mapa,size);

    }
}
