public class enemigo {
    //Atributos de clase 
    int salud; 
    int velocidad;
    int armadura;
    int recompensa;
    //Constructor de la clase 
    public enemigo(int salud, int velocidad, int armadura, int recompensa){
        this.salud = salud;
        this.velocidad = velocidad;
        this.armadura = armadura;
        this.recompensa = recompensa;
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
