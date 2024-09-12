public class Enano extends Enemigo {
    public Enano(int posX, int posY){
        super( 90, 2, 7,'\u2692',posX,posY,5,false);

    }

    @Override
    public void activarHabilidadEspecial(){
        this.habilidadEnUso = !this.habilidadEnUso; //bandera para saber si la habilidad del enemigo está activda o no
        aumertarRangoAtaque();
    }

    //La habilidad especial de los Enanos es que pueden aumentar su rango de ataque
    public void aumertarRangoAtaque(){
        System.out.println("\033[0;33m" + "ENANO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.rangoAtaque+=3;
    }

}