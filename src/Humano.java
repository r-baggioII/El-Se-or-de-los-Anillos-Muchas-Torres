

public class Humano extends Enemigo{
    public Humano(int posX, int posY){
        super( 100, 2, 5, '\u263C',posX,posY,10);
    }

    //Permite al humano aumentar su daño de ataque
    public void aumentarDanioAtaque(){
        this.danioAtaque += 5;
    }

    @Override
    public void superAtaque() {
        System.out.println("\u001B[33m" + "Humano ACTIVÓ HABILIDAD ESPECIAL" + "\u001B[0m");
        aumentarDanioAtaque();  // Habilidad especial de Humano
    }
}
