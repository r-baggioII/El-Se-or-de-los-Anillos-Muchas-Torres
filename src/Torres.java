import Enemigos.*;

public class Torres {
    private int vidaInicial;
    private int rangoAtaque;
    private char nombreTorre;
    private int posX;
    private int posY;
    private int poderAtaque;

    // Constructor para inicializar la posición de la torre
    public Torres() {
        this.vidaInicial = 50;
        this.rangoAtaque = 4;
        this.nombreTorre = '?';
        this.poderAtaque = 25;
    }
    // Métodos getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getRangoAtaque() {
        return rangoAtaque;
    }

    public char getNombreTorre() {
        return nombreTorre;
    }

    //Metodos
    public void colocarTorre(Mapas maps,int x, int y) {

        if (x == maps.getCerroGloriaX() && y == maps.getCerroGloriaY() ) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (x == maps.getTamañoMapa()/2 || y == maps.getTamañoMapa()/2 ) {
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

    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque() ) {
            this.vidaInicial -= enemigo.getRecompensa(); // Supongamos que el daño recibido se basa en la recompensa del enemigo
            System.out.println("VIDA " + this.nombreTorre+": "+this.vidaInicial);
        }

        if (this.vidaInicial <= 0) {
            System.out.println("¡La torre " + this.nombreTorre + " ha sido destruida!");
        }
    }

    // Método para que la torre ataque a un enemigo
    public void lanzarAtaque(Enemigo enemigo) {
        if (enemigoEnRango(enemigo)) {
            enemigo.recibirDanio(this.poderAtaque);
            System.out.println("La torre " + this.nombreTorre + " ha atacado al enemigo " + enemigo.getClass().getSimpleName() + " infligiendo " + this.poderAtaque + " de daño.");
            System.out.println("VIDA "+enemigo.getRepresentacion()+": "+enemigo.getSalud());
        }
    }

    // Método para verificar si un enemigo está en el rango de ataque de la torre
    private boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        return distanciaX <= rangoAtaque && distanciaY <= rangoAtaque;
    }




}