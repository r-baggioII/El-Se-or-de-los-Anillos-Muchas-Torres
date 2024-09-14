/**
 * La clase Humano representa a un enemigo de tipo Humano en el juego.
 * Hereda de la clase Enemigo y tiene una habilidad especial para aumentar su daño de ataque.
 */
public class Humano extends Enemigo{
    /**
     * Constructor que inicializa al Humano con su posición en el mapa y los valores predeterminados de salud, daño, rango de ataque y representación.
     *
     * @param posX la posición en el eje X del mapa.
     * @param posY la posición en el eje Y del mapa.
     */
    public Humano(int posX, int posY){
        super( 100, 2, 5, '\u263C',posX,posY,10,false);
    }
    /**
     * Activa o desactiva la habilidad especial del Humano.
     * Esta habilidad le permite aumentar temporalmente su daño de ataque.
     */
    @Override
    public void activarHabilidadEspecial(){
        this.habilidadEnUso = !this.habilidadEnUso; //bandera para saber si la habilidad del enemigo está activda o no
        aumentarDanioAtaque();
    }

    /**
     * Aumenta el daño de ataque del Humano en 5 unidades.
     * Esta es la habilidad especial del Humano, activada mediante {@link #activarHabilidadEspecial()}.
     */
    public void aumentarDanioAtaque(){
        System.out.println("\033[0;33m" + "HUMANO HA ACTIVADO SU HABILIDAD ESPECIAL" + "\033[0m");
        this.danioAtaque += 5;
    }
}