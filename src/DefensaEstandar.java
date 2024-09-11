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


    public boolean esEliminada(){
        if(this.resistencia <=0){
            return true;
        }
        return false;
    }

    public void informarEstado() {
        int newPosX = this.posX + 1;
        int newPosY = this.posY + 1;
        if (!esEliminada()) {
            System.out.println("Defensa " + this.nombreDefensa + " en (" + newPosX + ", " + newPosY + ") - VIDA: " + this.resistencia);
        } else {
            System.out.println("Defensa en (" + newPosX + ", " + newPosY + ") ha sido destruida.");
        }
    }

    public void recibirAtaque(Enemigo enemigo) {
            this.resistencia -= enemigo.getDanioAtaque();
    }
    public abstract boolean enemigoEnRango(Enemigo enemigo);
}


