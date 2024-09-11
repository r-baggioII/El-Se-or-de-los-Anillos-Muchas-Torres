
public abstract class Enemigo {
    
    //Atributos de clase
    protected int rangoAtaque;
    protected int salud;
    protected int recompensa;
    protected int posX; //posición en el mapa
    protected int posY;
    protected int danioAtaque;
    protected char representacion; //segun el enemigo tendrá un caracter que lo represente en el mapa

    //Constructores de la clase 
    public Enemigo(int saludInicial, int rangoAtaque, int recompensa, char representacion, int inicialX, int inicialY, int danioAtaque){
        this.salud = saludInicial;
        this.rangoAtaque = rangoAtaque;
        this.recompensa = recompensa;
        this.posX = inicialX;
        this.posY = inicialY;
        this.representacion = representacion;
        this.danioAtaque = danioAtaque;
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
        this.posX = posX;
    }

    public int getPosY(){
        return this.posY;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }

    public char getRepresentacion(){
        return representacion;
    }
    public void setRepresentacion(char representacion){
        this.representacion = representacion;
    }

    public int getDanioAtaque(){return danioAtaque;}
    public void setDanioAtaque(int danioAtaque){ this.danioAtaque = danioAtaque; }

    //Otros métodos
    public void recibirAtaque(Torre torre){
        this.salud -= torre.getPoderAtaque();
    }

    public void lanzarAtaque(Defensa defensa) {
        int newPosX = this.posX + 1; //Se le suma uno a la posicón para mostrar al usuario
        int newPosY = this.posY + 1;
        System.out.println("El enemigo " + this.representacion + " en la posicón"+ "(" + newPosX + " , " +  newPosY + ")" + " ha atacado a la defensa " + defensa.getClass().getSimpleName() + " infligiendo " + this.danioAtaque + " de daño.");
    }

    public void informarEstado() {
        int newPosX = this.posX + 1;
        int newPosY = this.posY + 1;
        if (!esEliminado()) {
            System.out.println("Enemigo " + this.representacion + " en (" + newPosX  + ", " + newPosY  + ") - VIDA: " + this.salud);
        } else {
            System.out.println("Enemigo " + this.representacion + " (" + newPosX+  ", " + newPosY + ") eliminado.");
        }
    }

    public boolean defensaEnRango(Defensa defensa) {
        int distanciaX = Math.abs(this.posX - defensa.getPosX());
        int distanciaY = Math.abs(this.posY - defensa.getPosY());
        return distanciaX <= this.rangoAtaque && distanciaY <= this.rangoAtaque;
    }


    public boolean esEliminado(){
        //Método para verificar si el enemigo fue eliminado
        return salud <= 0;
    }


    public void moverHacia(Mapa mapa, int torreX, int torreY) {
        // Verificar si está cerca de la torre (a 1 o 2 casillas de distancia)
        int distanciaX = Math.abs(posX - torreX);
        int distanciaY = Math.abs(posY - torreY);

        if (distanciaX < 2 && distanciaY < 2) {
            // Ajustar el objetivo hacia la torre si está cerca
            posX = torreX;
            posY = torreY;
        }else{
            // Verificar si el movimiento en la dirección X está bloqueado
            if (posX < torreX && mapa.getElemento(posX + 1, posY) == '.'){
                posX++;
            } else if (posX > torreX && mapa.getElemento(posX - 1, posY) == '.') {
                posX--;
            } else if (posY < torreY && mapa.getElemento(posX, posY +1) == '.' )  { // Verificar si el movimiento en la dirección Y está bloqueado
                posY++;
            } else if (posY > torreY &&  mapa.getElemento(posX, posY -1) == '.') {
                posY--;
            } else {
                // Si ambos caminos están bloqueados, buscar una ruta alternativa
                if (mapa.getElemento(posX + 1, posY) == '.' && posX < mapa.getTamañoMapa() - 1) {
                    posX++;
                } else if (mapa.getElemento(posX - 1, posY) == '.' && posX > 0) {
                    posX--;
                } else if (mapa.getElemento(posX, posY +1) == '.' && posY < mapa.getTamañoMapa() - 1) {
                    posY++;
                } else if (mapa.getElemento(posX, posY -1) == '.' && posY > 0) {
                    posY--;
                }
            }
        }
    }
}
