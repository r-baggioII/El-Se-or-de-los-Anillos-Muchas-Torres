public abstract class Enemigo {
    
    //Atributos de clase 
    protected int salud; 
    protected int velocidad;
    protected int armadura;
    protected int recompensa;
    protected int posicionX;
    protected int posicionY;
    protected char representacion; //segun el enemigo tendrá un caracter que lo represente en el mapa

    //Constructores de la clase 
    public Enemigo(int salud, int velocidad, int armadura, int recompensa, int posicionX, int posicionY, char representacion){
        this.salud = salud;
        this.velocidad = velocidad;
        this.armadura = armadura;
        this.recompensa = recompensa;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.representacion = representacion;
    }
    //Constructor por defecto 
    public Enemigo(){
        this.salud = 0;
        this.velocidad = 0;
        this.armadura = 0;
        this.recompensa = 0;
    }
    //Setters y Getters 
    public int salud(){
        return salud; 
    }
    public void setSalud(int salud){
        this.salud = salud; 
    }
    public int velocidad(){
        return velocidad; 
    }
    public void velocidad(int velocidad){
        this.velocidad = velocidad; 
    }
    public int armadura(){
        return armadura;
    }
    public void setAramaadura(int armadura){
        this.armadura = armadura; 
    }
    public int recompensa(){
        return recompensa;
    }
    public void setRecompensa(int recompensa){
        this.recompensa = recompensa;
    }
    public int posicionX(){
        return posicionX;
    }
    public void setPosicionX(int posicionX){
        this.posicionX = posicionX;
    }
    public int posicionY(){
        return posicionY;
    }
    public void setPosicionY(int posicionY){
        this.posicionY = posicionY;
    }
    public char representacion(){
        return representacion;
    }
    public void setRepresentacion(char representacion){
        this.representacion = representacion;
    }
    //Otros métodos 
    public void mover(){
        //Método para mover al enemigo 
    }
    public void recibirDaño(){
        //Método para recibir daño 
    }   
    public boolean esEliminado(){
        //Método para verificar si el enemigo fue eliminado 
        return false;
    }
}
