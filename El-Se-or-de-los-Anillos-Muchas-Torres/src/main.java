public class main {
    public static void main(String[] args) {
        // Elejir Mapa
        Mapas maps = new Mapas();
        maps.iniciarMapa();

        //Inicar Oleada
        Oleada play = new Oleada(maps);
        play.start();

    }
}
