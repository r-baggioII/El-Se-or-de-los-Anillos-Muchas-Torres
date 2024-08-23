public class Enano extends Enemigo {
    public Enano(int posX, int posY){
        super( 90, 8, 7, 5,posX,posY,'e');
    }
    
    @Override
    public void mover(){
        //Implementación de método mover para enanos
    }
    
    @Override
    public void recibirDaño(){
        //Implementación de método recibirDaño para enanos
    }
    
    @Override
    public boolean esEliminado(){
        //Implementación de método esEliminado para enanos 
        return false;
    }
    
}
