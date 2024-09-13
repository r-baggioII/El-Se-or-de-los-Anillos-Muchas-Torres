public class CerroGloria extends Defensa {
    public int vidas =5;
    public CerroGloria(int posX, int posY){
        super(posX,posY,'\u26EB');
    }
    public int getVidas(){
        return vidas;
    }
    public void setVidas(int vidas){
        this.vidas = vidas;
    }
    public void recibirAtaque(Enemigo enemigo){
        this.vidas -= 1;
    }
}
