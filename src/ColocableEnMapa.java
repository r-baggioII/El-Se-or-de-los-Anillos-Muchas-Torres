/**
 * Interfaz que define el comportamiento de los objetos que pueden ser colocados en un mapa.
 * Cualquier clase que implemente esta interfaz debe proporcionar una implementación del método 
 * {@code colocarEnMapa(Mapa mapa)} para posicionarse en el mapa.
 */
public interface ColocableEnMapa {

    /**
     * Coloca el objeto en el mapa en una posición determinada.
     *
     * @param mapa el mapa en el cual se colocará el objeto.
     * @throws IllegalArgumentException si la posición elegida es inválida o no permitida en el mapa.
     */
    void colocarEnMapa(Mapa mapa);
}
