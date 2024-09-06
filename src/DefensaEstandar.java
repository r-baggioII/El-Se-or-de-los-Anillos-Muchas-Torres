public abstract class DefensaEstandar extends Defensa {
    public int resistencia;
    public int costoMagia;

    public DefensaEstandar(int posX, int posY, char nombreDefensa, int resistencia, int costoMagia) {
        super(posX,posY,nombreDefensa);
        this.resistencia = resistencia;
        this.costoMagia = costoMagia;
    }

    public int getResistencia() { return resistencia; }
    public int getCosto() { return costoMagia; }

    public void restaurarVida() {}

    public boolean esEliminada(){
        if(this.resistencia <=0){
            return true;
        }
        return false;
    }

    public void informarEstado() {
        if (!esEliminada()) {
            System.out.println("Defensa " + this.nombreDefensa + " en (" + posX + ", " + posY + ") - VIDA: " + this.resistencia);
        } else {
            System.out.println("Defensa en (" + posX + ", " + posY + ") ha sido destruida.");
        }
    }

    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); //debería cambiarse por el poder de ataque del enemigo
        }
    }
}


