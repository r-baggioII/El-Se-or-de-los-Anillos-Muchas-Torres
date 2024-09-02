public abstract class Defensa {
    public int posX; 
    public int posY;
    public char nombreDefensa;

    //Constructor 
    public Defensa(int posX, int posY,char nombreDefensa){
        this.posX = posX; 
        this.posY = posY; 
        this.nombreDefensa = nombreDefensa;
    }
    //Getters 
  
    public int getPosX(){
        return posX; 
    }
    public int getPosY(){
        return posY; 
    }
    public char getNombrDefensa(){return nombreDefensa;}


}

