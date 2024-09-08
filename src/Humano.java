

public class Humano extends Enemigo{
    int bloquearAtaque;
    public Humano(int posX, int posY){
        super( 100, 2, 5, '\u263C',posX,posY,10);
        bloquearAtaque = 3;
    }

    //Permite al humano resistir ataques de las Torres
    public void escudoProtecion(){

    }

    public void ataqueBloqueado(){
        bloquearAtaque--;
    }



}
