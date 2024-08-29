
import java.util.ArrayList;
import java.util.List;

public class Torre extends Defensa {
    private int rangoAtaque;
    private int poderAtaque;

    // Constructor para inicializar la posición de la torre
    public Torre(int posX, int posY) {
        super(100, 50, posX, posY,'t'); // Llama al constructor de Defensa 
        this.rangoAtaque = 4;
        this.poderAtaque = 25;

    }
    //Construcotr por defecto sin arguemntos
    public Torre() {
        super(100, 50, 0, 0,'t'); //Por defecto la torre se coloca en la posición 0,0
        this.rangoAtaque = 4;
        this.poderAtaque = 25;
    }

    // Getters
    public int getRangoAtaque() {
        return rangoAtaque;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    // Métodos
    public void colocarTorre(Mapa maps, Magia magia,int x,int y, char nombreTorre ) {
            //Gastar magia al colocar la torre
            magia.gastarMagia(this.costo);
            this.nombreDefensa = nombreTorre;
            //Verifica que la posición elegida sea correcta s
            if (x == maps.getCerroGloriaX() && y == maps.getCerroGloriaY()) {
                throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
            } else if (x == maps.getTamañoMapa() / 2 || y == maps.getTamañoMapa() / 2) {
                throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las barreras.");
            }
            //Coloca la torre en el mapa
            this.posX = x;
            this.posY = y;
            colocarEnMapa(maps.getMapa());

    }
   
    // Método para recibir un ataque de un enemigo (implementación de recibirAtaque de la clase Defensa)
    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        if ((distanciaX <= enemigo.getrangoAtaque()) && (distanciaY <= enemigo.getrangoAtaque())) {
            System.out.println("("+distanciaX + "," + distanciaY+"):"+enemigo.getrangoAtaque());
            this.resistencia -= enemigo.getRecompensa(); 
            System.out.println("VIDA " + this.nombreDefensa + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La torre " + this.nombreDefensa + " ha sido destruida!");
        }
    }

    // Método para que la torre ataque a un enemigo
    public void lanzarAtaque(Enemigo enemigo) {
        if (enemigoEnRango(enemigo)) {
            int newPosX = this.posX + 1; //Se le suma uno a la posicón para mostrar al usuario
            int newPosY = this.posY + 1;
            enemigo.recibirDanio(this.poderAtaque);
            System.out.println("La torre " + this.nombreDefensa + " en la posicón"+ "(" + newPosX + " , " +  newPosY + ")" + " ha atacado al enemigo " + enemigo.getClass().getSimpleName() + " infligiendo " + this.poderAtaque + " de daño.");
            System.out.println("VIDA " + enemigo.getRepresentacion() + ": " + enemigo.getSalud());
        }
    }

    // Método para verificar si un enemigo está en el rango de ataque de la torre
    private boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        return distanciaX <= rangoAtaque && distanciaY <= rangoAtaque;
    }
}
