public class Humano extends Enemigo{
    public Humano(){
        super( 100, 10, 5, 4);
    }
    
    @Override
    public void mover(){
        //Implementación de método mover para humanos
    }
    
    @Override
    public void recibirDaño(){
        //Implementación de método recibirDaño para humanos 
    }
    
    @Override
    public boolean esEliminado(){
        //Implementación de método esEliminado para humanos 
        return false;
    }
}
