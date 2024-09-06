
public abstract class Enemigo {
    
    //Atributos de clase
    protected int rangoAtaque;
    protected int salud;
    protected int recompensa;
    protected int posX; //posición en el mapa
    protected int posY;
    protected char representacion; //segun el enemigo tendrá un caracter que lo represente en el mapa

    //Constructores de la clase 
    public Enemigo(int saludInicial, int rangoAtaque, int recompensa, char representacion, int inicialX, int inicialY){
        this.salud = saludInicial;
        this.rangoAtaque = rangoAtaque;
        this.recompensa = recompensa;
        this.posX = inicialX;
        this.posY = inicialY;
        this.representacion = representacion;
    }
    //Setters y Getters 
    public int getSalud(){
        return salud; 
    }
    public void setSalud(int salud){
        this.salud = salud; 
    }

    public int getrangoAtaque(){
        return rangoAtaque;
    }
    public void setrangoAtaque(int rangoAtaque){
        this.rangoAtaque = rangoAtaque;
    }


    public int getRecompensa(){
        return recompensa;
    }
    public void setRecompensa(int recompensa){
        this.recompensa = recompensa;
    }

    public int getPosX(){
        return this.posX;
    }
    public void setPosX(int posX){
        this.posX = posX + 1;
    }

    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int posY){
        this.posY = posY + 1;
    }

    public char getRepresentacion(){
        return representacion;
    }
    public void setRepresentacion(char representacion){
        this.representacion = representacion;
    }
    
    //Otros métodos
    public void recibirDanio(int danio){
        //Método para recibir daño
        salud -= danio;
    }

    public void informarEstado() {
        if (!esEliminado()) {
            System.out.println("Enemigo " + this.representacion + " en (" + posX  + ", " + posY  + ") - VIDA: " + this.salud);
        } else {
            System.out.println("Enemigo " + this.representacion + "(" + posX +  ", " + posY + ") eliminado.");
        }
    }


    public boolean esEliminado(){
        //Método para verificar si el enemigo fue eliminado
        return salud <= 0;
    }

    public void moverHacia(Mapa mapa, int torreX, int torreY,Defensa defensa) {
        // Verificar si está cerca de la torre (a 1 o 2 casillas de distancia)
        int distanciaX = Math.abs(posX - torreX);
        int distanciaY = Math.abs(posY - torreY);

        if (distanciaX < 2 && distanciaY < 2) {
            // Ajustar el objetivo hacia la torre si está cerca
            posX = torreX;
            posY = torreY;
        }else{
            // Verificar si el movimiento en la dirección X está bloqueado
            if (posX < torreX && mapa.getElemento(posX + 1, posY) != '*' && mapa.getElemento(posX + 1, posY) != defensa.getNombreDefensa()) {
                posX++;
            } else if (posX > torreX && mapa.getElemento(posX - 1, posY) != '*' && mapa.getElemento(posX - 1, posY) != defensa.getNombreDefensa()) {
                posX--;
            } else if (posY < torreY && mapa.getElemento(posX, posY +1) != '*' && mapa.getElemento(posX, posY +1) != defensa.getNombreDefensa()) { // Verificar si el movimiento en la dirección Y está bloqueado
                posY++;
            } else if (posY > torreY &&  mapa.getElemento(posX, posY -1) != '*' && mapa.getElemento(posX, posY -1) != defensa.getNombreDefensa()) {
                posY--;
            } else {
                // Si ambos caminos están bloqueados, buscar una ruta alternativa
                if (mapa.getElemento(posX + 1, posY) != '*' && posX < mapa.getTamañoMapa() - 1) {
                    posX++;
                } else if (mapa.getElemento(posX - 1, posY) != '*' && posX > 0) {
                    posX--;
                } else if (mapa.getElemento(posX, posY +1) != '*' && posY < mapa.getTamañoMapa() - 1) { //antes era mapa[0].length (el mapa es cuadrado así q es lo mismo)
                    posY++;
                } else if (mapa.getElemento(posX, posY -1) != '*' && posY > 0) {
                    posY--;
                }
            }
        }
    }
}
