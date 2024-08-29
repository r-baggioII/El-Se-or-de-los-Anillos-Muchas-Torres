public class Barrera extends Defensa {
    //Constrcutor de la clase Barrera
    public Barrera(int resistencia, int costo, int posX, int posY,char nombreBarrera) {
        super(resistencia, costo, posX, posY,'$');
    }

    //Metodo que recibe un ataque de un enemigo (implemenación del método abstracto de la clase Defensa)
    @Override
    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX()); 
        int distanciaY = Math.abs(this.posY - enemigo.getPosY()); 
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); // Changed from vidaInicial to resistencia
            System.out.println("VIDA " + this.nombreDefensa + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La barrera " + this.nombreDefensa + " ha sido destruida!");
        }
    }

    
}
