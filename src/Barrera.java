public class Barrera extends Defensa {
    //Constrcutor de la clase Barrera




    public Barrera(int posX, int posY) {
        super(200, 25, posX, posY,'t'); // Llama al constructor de Defensa
    }
    //Construcotr por defecto sin arguemntos
    public Barrera() {
        super(100, 50, 0, 0,'d'); //Por defecto la torre se coloca en la posición 0,0
    }
    //Metodo que recibe un ataque de un enemigo (implemenación del método abstracto de la clase Defensa)
}
