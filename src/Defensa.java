public abstract class Defensa {
    public int resistencia; 
    public int costo; 
    public int posX; 
    public int posY;
    public char nombreDefensa;

    //Constructor 
    public Defensa(int resistencia, int costo, int posX, int posY,char nombreDefensa){
        this.resistencia = resistencia; 
        this.costo = costo; 
        this.posX = posX; 
        this.posY = posY; 
        this.nombreDefensa = nombreDefensa;
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
    public char getNombrDefensa(){return nombreDefensa;}

    //Metodos
    public void restaurarVida(){}
    //metodo polimorfico que se implementará en las clases barrera y torre
    abstract protected void recibirAtaque(Enemigo enemigo); //método abstracto que se implementará en las clases barrera y torre 
                                                            
    
    protected void colocarEnMapa(char[][] mapa){
        mapa[posX][posY] = this.nombreDefensa; // 't' para torre y '$' para barrera
    }
}

