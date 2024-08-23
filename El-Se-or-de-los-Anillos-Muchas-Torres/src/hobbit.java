public class Hobbit extends Enemigo {
    public Hobbit(){
        super( 70, 9, 3, 2);
    }
    
    @Override
    public void mover(){
        //Implementación de método mover para hobbits 
    }
    
    @Override
    public void recibirDaño(){
        //Implementación de método recibirDaño para hobbits 
    }
    
    @Override
    public boolean esEliminado(){
        //Implementación de método esEliminado para hobbits 
        return false;
    }
}
