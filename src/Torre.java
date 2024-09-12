
import java.util.ArrayList;
import java.util.List;

public class Torre extends DefensaEstandar implements ColocableEnMapa {
    private int rangoAtaque;
    private int poderAtaque;

    // Constructor para inicializar la posición de la torre
    public Torre(int posX, int posY, int resistencia, int costoMagia) {
        super(posX, posY, '\u2656', resistencia, costoMagia);
        this.rangoAtaque = 4;
        this.poderAtaque = 20;
    }

    public Torre() {
        super(0, 0, '\u2656', 0, 0);
        this.rangoAtaque = 4;
        this.poderAtaque = 20;
    }

    // Getters

    public int getPoderAtaque() {
        return poderAtaque;
    }

    // Métodos

    // Método para que la torre ataque a un enemigo
    public void lanzarAtaque(Enemigo enemigo) {
        int newPosX = this.posX + 1; //Se le suma uno a la posicón para mostrar al usuario
        int newPosY = this.posY + 1;
        System.out.println("La torre " + this.nombreDefensa + " en la posicón"+ "(" + newPosX + " , " +  newPosY + ")" + " ha atacado al enemigo " + enemigo.getClass().getSimpleName() + " infligiendo " + this.poderAtaque + " de daño.");
    }

    @Override
    // Método para verificar si un enemigo está en el rango de ataque de la torre
    public boolean enemigoEnRango(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        return distanciaX <= rangoAtaque && distanciaY <= rangoAtaque;
    }

    @Override
    public void colocarEnMapa(Mapa mapa) {
        //Verifica que la posición elegida sea correcta s
        if (this.posX == mapa.cerroGloria.getPosX() && this.posY == mapa.cerroGloria.getPosY()) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (this.posX == mapa.getTamañoMapa() / 2 || this.posY == mapa.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las fronteras de los cuatro reinos.");
        }
        //Coloca la torre en el mapa
        mapa.setElemento(this.posX, this.posY, this.nombreDefensa); // 't' representa la torre
    }
}
