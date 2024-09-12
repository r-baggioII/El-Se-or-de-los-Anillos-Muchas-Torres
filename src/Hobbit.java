import java.awt.*;

public class Hobbit extends Enemigo {

    public Hobbit(int posX, int posY){
        super( 70, 1, 3,'\u26C7',posX,posY,20, false);
    }


    @Override
    public void activarHabilidadEspecial(){
        this.habilidadEnUso = !this.habilidadEnUso; //bandera para saber si la habilidad del enemigo está activda o no
        absorberAtaque(20); //Las torress siempren hacen 25 de daño
    }

    //La habilidad especial del hobbit es que cuando recibe el ataque de la torre vuelve a recuperar la salud perdida
    public void absorberAtaque(int danioRecibido){
        System.out.println("\033[0;33m" + "HOBBIT HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.salud += danioRecibido;
    }


}