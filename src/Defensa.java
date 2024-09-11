// Defensa.java
public abstract class Defensa {
    public int posX; 
    public int posY;
    public char nombreDefensa;

    public Defensa(int posX, int posY,char nombreDefensa) {
        this.posX = posX; 
        this.posY = posY; 
        this.nombreDefensa = nombreDefensa;
    }

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }


}

