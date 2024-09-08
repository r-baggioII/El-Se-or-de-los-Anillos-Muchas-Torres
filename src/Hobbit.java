import java.awt.*;

public class Hobbit extends Enemigo {
    public Hobbit(int posX, int posY){
        super( 70, 1, 3,'\u26C7',posX,posY,20);
    }

    // Al ser un hobit le permite ser sigiloso y avansar sin ser descubierlo
    public void sigiloHobit(){

    }
}
