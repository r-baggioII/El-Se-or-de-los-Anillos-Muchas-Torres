import java.util.Scanner;
import Enemigos.*;
public class main {
    public static void main(String[] args) {
        //Iniciar Nivel
        Nivel nivel = new Nivel(1,100);
        // Elejir Mapa
        Mapas maps = new Mapas();
        maps.iniciarMapa();
        //Colocar Torres
        Torres torre = new Torres();
        torre.colocarTorre(maps,2,3);
        //Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.Start(maps,nivel,torre);
    }
}
