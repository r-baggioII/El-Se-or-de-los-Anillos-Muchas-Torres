public class Humano extends Enemigo{

    public Humano(int posX, int posY){
        super( 100, 2, 5, '\u263C',posX,posY,10,false);
    }

    @Override
    public void activarHabilidadEspecial(){
        this.habilidadEnUso = !this.habilidadEnUso; //bandera para saber si la habilidad del enemigo está activda o no
        aumentarDanioAtaque();
    }

    //La habilidad especial de los humanos es que pueden aumentar el daño al atacar
    public void aumentarDanioAtaque(){
        System.out.println("\033[0;33m" + "HUMANO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.danioAtaque += 5;
    }


}