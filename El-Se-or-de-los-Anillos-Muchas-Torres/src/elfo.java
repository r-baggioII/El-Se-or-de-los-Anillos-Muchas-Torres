public class Elfo extends Enemigo {
    public Elfo (int posX,int posY){
        super( 120, 6, 10, 6, posX,posY,'E');
    }
    
    @Override
    public void mover(){
        //Implementación de método mover para elfos
    }
    
    @Override
    public void recibirDaño(){
        //Implementación de método recibirDaño para elfos
    }
    
    @Override
    public boolean esEliminado(){
        //Implementación de método esEliminado para elfos 
        return false;
    }
}
