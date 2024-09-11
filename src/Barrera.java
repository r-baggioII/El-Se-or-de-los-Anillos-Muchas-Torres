public class Barrera extends DefensaEstandar implements ColocableEnMapa {
    //Constrcutor de la clase Barrera
    //int posX, int posY, char nombreDefensa, int resistencia, int costoMagia 
    public Barrera(int posX, int posY, int resistencia, int costoMagia) {
        super(posX, posY,'\u2592', resistencia, costoMagia); // Llama al constructor de Defensa
    }
    //Construcotr por defecto sin arguemntos
    public Barrera() {
        super(0, 0,'\u2592', 0, 0); // Llama al constructor de Defensa

    }

    @Override
    public void colocarEnMapa(Mapa mapa) {
        //Verifica que la posición elegida sea correcta s
        if (this.posX == mapa.cerroGloria.getPosX() && this.posY == mapa.cerroGloria.getPosY()) {
            throw new IllegalArgumentException("No se puede colocar una barrera en la misma posición que la torre principal.");
        } else if (this.posX == mapa.getTamañoMapa() / 2 || this.posY == mapa.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una barrera en la misma posición que las fronteras de los cuatro reinos.");
        }
        //Coloca la torre en el mapa
        mapa.setElemento(this.posX, this.posY, this.nombreDefensa); // 'b' representa la torre
    }

}
