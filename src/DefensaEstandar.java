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

    public void recibirAtaque(Enemigo enemigo) {
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); 
            System.out.println("VIDA " + this.nombreDefensa + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La barrera " + this.nombreDefensa + " ha sido destruida!");
        }
    }
}


