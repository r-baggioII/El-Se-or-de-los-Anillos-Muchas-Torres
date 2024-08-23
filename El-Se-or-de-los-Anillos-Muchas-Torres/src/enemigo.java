public abstract class Enemigo {
    
    //Atributos de clase 
    protected int salud; 
    protected int velocidad;
    protected int armadura;
    protected int recompensa;
    protected int posX; //posición en el mapa
    protected int posY;
    protected char representacion; //segun el enemigo tendrá un caracter que lo represente en el mapa

    //Constructores de la clase 
    public Enemigo(int saludInicial, int velocidad, int armadura, int recompensa, int inicialX, int inicialY, char representacion){
        this.salud = saludInicial;
        this.velocidad = velocidad;
        this.armadura = armadura;
        this.recompensa = recompensa;
        this.posX = inicialX;
        this.posY = inicialY;
        this.representacion = representacion;
    }
    //Setters y Getters 
    public int getSalud(){
        return salud; 
    }
    public void setSalud(int salud){
        this.salud = salud; 
    }
    public int getVelocidad(){
        return velocidad; 
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad; 
    }
    public int getArmadura(){
        return armadura;
    }
    public void setAramaadura(int armadura){
        this.armadura = armadura; 
    }
    public int getRecompensa(){
        return recompensa;
    }
    public void setRecompensa(int recompensa){
        this.recompensa = recompensa;
    }
    public int getPosicionX(){
        return posX;
    }
    public void setPosicionX(int posX){
        this.posX = posX;
    }
    public int getPosicionY(){
        return posY;
    }
    public void setPosicionY(int posY){
        this.posY = posY;
    }
    public char getRepresentacion(){
        return representacion;
    }
    public void setRepresentacion(char representacion){
        this.representacion = representacion;
    }
    
    //Otros métodos 
    public void moverHacia(int objetivoX, int objetivoY){
        //Método para mover al enemigo sobre el mapa
        if (posX < objetivoX) {
            posX++;
        } else if (posX > objetivoX) {
            posX--;
        }

        if (posY < objetivoY) {
            posY++;
        } else if (posY > objetivoY) {
            posY--;
        }

    }
    public void recibirDanio(int danio){
        //Método para recibir daño 
        salud -= danio;
    }   
    public boolean esEliminado(){
        //Método para verificar si el enemigo fue eliminado 
        return salud <= 0; 
    }
}
