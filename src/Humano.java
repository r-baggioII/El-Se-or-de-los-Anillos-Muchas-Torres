

public class Humano extends Enemigo{
    public Humano(int posX, int posY){
        super( 100, 2, 5, '\u263C',posX,posY,10);
    }

    //Permite al humano resistir ataques de las Torres
    public void escudoProtecion(){
        this.danioAtaque += 5;
    }
}
