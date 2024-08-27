
import Enemigos.*; // Ensure this package import is correct

public class Torres extends Defensa {
    private int rangoAtaque;
    private char nombreTorre;
    private int poderAtaque;

    // Constructor para inicializar la posición de la torre
    public Torres(int posX, int posY) {
        super(100, 50, posX, posY); // Calls the Defensa constructor
        this.rangoAtaque = 4;
        this.nombreTorre = '?';
        this.poderAtaque = 25;
    }
    //Construcotr por defecto sin arguemntos
    public Torres() {
        super(100, 50, 0, 0); //Por defecto la torre se coloca en la posición 0,0
        this.rangoAtaque = 4;
        this.nombreTorre = '?';
        this.poderAtaque = 25;
    }

    // Getters
    public int getRangoAtaque() {
        return rangoAtaque;
    }

    public char getNombreTorre() {
        return nombreTorre;
    }

    // Métodos
    public void colocarTorre(Mapa maps, int x, int y) {
        if (x == maps.getCerroGloriaX() && y == maps.getCerroGloriaY()) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (x == maps.getTamañoMapa() / 2 || y == maps.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las barreras.");
        }

        this.posX = x; 
        this.posY = y; 
        colocarTorre(maps.getMapa());
    }

    // Método para colocar la torre en el mapa
    private void colocarTorre(char[][] mapa) {
        mapa[posX][posY] = this.nombreTorre; // '?' representa una torre adicional
    }

    @Override
    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); // Changed from vidaInicial to resistencia
            System.out.println("VIDA " + this.nombreTorre + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La torre " + this.nombreTorre + " ha sido destruida!");
        }
    }

    // Método para que la torre ataque a un enemigo
    public void lanzarAtaque(Enemigo enemigo) {
        if (enemigoEnRango(enemigo)) {
            enemigo.recibirDanio(this.poderAtaque);
            System.out.println("La torre " + this.nombreTorre + " ha atacado al enemigo " + enemigo.getClass().getSimpleName() + " infligiendo " + this.poderAtaque + " de daño.");
            System.out.println("VIDA " + enemigo.getRepresentacion() + ": " + enemigo.getSalud());
        }
    }

    // Método para verificar si un enemigo está en el rango de ataque de la torre
    private boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); // Inherited from Defensa
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); // Inherited from Defensa
        return distanciaX <= rangoAtaque && distanciaY <= rangoAtaque;
    }
}
