public class Barrera extends Defensa {
    char nombreBarrera;

    public Barrera(int resistencia, int costo, int posX, int posY) {
        super(resistencia, costo, posX, posY);
        this.nombreBarrera = '$';
    }
    @Override
    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); // Changed from vidaInicial to resistencia
            System.out.println("VIDA " + this.nombreBarrera + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La barrera " + this.nombreBarrera + " ha sido destruida!");
        }
    }

    public void colocarEnMapa(char[][] mapa){
        mapa[posX][posY] = this.nombreBarrera ; // '?' representa una torre adicional
    }
    
}
