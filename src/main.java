import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public List<Defensa> miTorres;
    public List<Defensa> miBarrera;
    Scanner sc = new Scanner(System.in);

    public void main(String[] args) {
        this.miTorres = new ArrayList<>();
        this.miBarrera = new ArrayList<>();

        //Iniciar Nivel
        Nivel nivel = new Nivel(1,100);
        //Iniciar Magia
        Magia magia=new Magia();
        // Elejir Mapa
        Mapa maps = new Mapa();
        maps.iniciarMapa();

        //Colocar Torres y defensa
        boolean flag = true;
        while (flag){
            System.out.println("Colocar Torre: t ");
            System.out.println("Colocar Barreras: b ");
            String opcion = sc.nextLine();
            System.out.println("Posicion de la torre:");
            int posX = sc.nextInt();
            int posY= sc.nextInt();
            if (posX+1 <= maps.getTamañoMapa() && posY+1 <= maps.getTamañoMapa() ){
                switch (opcion.toLowerCase()){
                    case "t":
                        Torre torre = new Torre();
                        torre.colocarTorre(maps,magia,0,0,'I');
                        this.miTorres.add(torre);
                    case "b":
                        Barrera defensa= new Barrera(70,25,posX,posY);
                        defensa.colocarEnMapa(maps.getMapa());
                        this.miBarrera.add(defensa);
                    default:
                        System.out.println("Torres y barreras colocados");
                        flag = false;
                }
            } else {
                System.out.println("cordenadas fuera de los limites");
            }
        }
        //Iniciar Oleada
        Oleada play = new Oleada(maps);
        play.Start(maps,nivel,this.miTorres,this.miBarrera);
    }
}
