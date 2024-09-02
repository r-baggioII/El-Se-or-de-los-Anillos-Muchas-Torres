public class Barrera extends DefensaEstandar {
    //Constrcutor de la clase Barrera
    //int posX, int posY, char nombreDefensa, int resistencia, int costoMagia 
    public Barrera(int posX, int posY, int resistencia, int costoMagia) {
        super(posX, posY,'b', resistencia, costoMagia); // Llama al constructor de Defensa
    }
    //Construcotr por defecto sin arguemntos
    public Barrera() {
        super(0, 0,'b', 0, 0); // Llama al constructor de Defensa

    }
   
}
