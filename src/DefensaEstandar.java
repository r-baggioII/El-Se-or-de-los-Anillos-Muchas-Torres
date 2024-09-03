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

    public abstract void recibirAtaque(Enemigo enemigo);
}


