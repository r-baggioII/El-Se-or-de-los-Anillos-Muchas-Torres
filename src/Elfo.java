public class Elfo extends Enemigo {

    public Elfo (int posX,int posY){
        super( 120, 3, 10,'\u2694', posX,posY,15,false);
    }

    @Override
    public void activarHabilidadEspecial(){
        this.habilidadEnUso = !this.habilidadEnUso; //bandera para saber si la habilidad del enemigo está activda o no
        activarCuración();
    }

    //La habilidad especial de los elfos es que pueden curarse a sí mismos
    public void activarCuración(){
        System.out.println("\033[0;33m" + "ELFO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.salud += 10;
    }

}