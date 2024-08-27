
import java.util.Scanner;
import Enemigos.*;

public class main {
    public static void main(String[] args) {
        //Iniciar Nivel
        Nivel nivel = new Nivel(1,100);
        // Elejir Mapa
        Mapa maps = new Mapa();
        maps.iniciarMapa();
        //Colocar Torres
        Torre torre = new Torre();
        torre.colocarTorre(maps,2,3);
        //Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.Start(maps,nivel,torre);
    }
}
