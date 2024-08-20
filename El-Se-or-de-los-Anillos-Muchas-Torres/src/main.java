public class main {
    public static void main(String[] args) {
        // Elejir Mapa
        Mapas maps = new Mapas();
        maps.inicarMapa();

        //Inicar Oleada
        Oleada play = new Oleada();
        play.start();

    }
}
