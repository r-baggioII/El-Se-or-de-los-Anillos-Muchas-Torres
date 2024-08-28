import Enemigos.Enemigo;
public class Defensa {
    public int resistencia; 
    public int costo; 
    public int posX; 
    public int posY; 

    //Constructor 
    public Defensa(int resistencia, int costo, int posX, int posY){
        this.resistencia = resistencia; 
        this.costo = costo; 
        this.posX = posX; 
        this.posY = posY; 
    }
    //Getters 
    public int getResistencia(){
        return resistencia; 
    }
    public int getCosto(){
        return costo; 
    }
    public int getPosX(){
        return posX; 
    }
    public int getPosY(){
        return posY; 
    }

    //Metodos 

    public void restaurarVida(){

    }
    public void recibirAtaque(Enemigo enemigo){

    }
}
