import java.awt.*;

public class Hobbit extends Enemigo {
    int sigilo;
    public Hobbit(int posX, int posY){
        super( 70, 1, 3,'\u26C7',posX,posY,20);
    }

    // Al ser un hobbit le permite ser sigiloso y avanzar sin ser descubierto
    public void sigiloHobit(){
        this.sigilo=4;
    }
    public void pasoSigiloso(){
        this.sigilo--;

    }
    public int getSigilo(){
        return this.sigilo;
    }

    @Override
    public void superAtaque() {
        System.out.println("\u001B[33m" + "Hobbit ACTIVÓ HABILIDAD ESPECIAL" + "\u001B[0m");
        pasoSigiloso();  // Habilidad especial de Elfo
    }

}
