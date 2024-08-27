package Enemigos;
public abstract class Enemigo {
    
    //Atributos de clase
    protected int rangoAtaque;
    protected int salud;
    protected int armadura;
    protected int recompensa;
    protected int posX; //posición en el mapa
    protected int posY;
    protected char representacion; //segun el enemigo tendrá un caracter que lo represente en el mapa

    //Constructores de la clase 
    public Enemigo(int saludInicial, int rangoAtaque, int armadura, int recompensa, char representacion, int inicialX, int inicialY){
        this.salud = saludInicial;
        this.rangoAtaque = rangoAtaque;
        this.armadura = armadura;
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

    public int getArmadura(){
        return armadura;
    }
    public void setAramadura(int armadura){
        this.armadura = armadura; 
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
    
    //Otros métodos
    public void recibirDanio(int danio){
        //Método para recibir daño
        salud -= danio;
    }

    public boolean esEliminado(){
        //Método para verificar si el enemigo fue eliminado
        return salud <= 0;
    }

    public void moverHacia(char[][] mapa, int objetivoX, int objetivoY, int torreX, int torreY) {
        // Verificar si está cerca de la torre (a 1 o 2 casillas de distancia)
        int distanciaX = Math.abs(posX - torreX);
        int distanciaY = Math.abs(posY - torreY);

        if (distanciaX < 2 && distanciaY < 2) {
            // Ajustar el objetivo hacia la torre si está cerca
            posX = torreX;
            posY = torreY;
        }else{
            // Verificar si el movimiento en la dirección X está bloqueado
            if (posX < objetivoX && mapa[posX + 1][posY] != '*' && mapa[posX + 1][posY] != '?') {
                posX++;
            } else if (posX > objetivoX && mapa[posX - 1][posY] != '*' && mapa[posX - 1][posY] != '?') {
                posX--;
            } else if (posY < objetivoY && mapa[posX][posY + 1] != '*' && mapa[posX][posY + 1] != '?') { // Verificar si el movimiento en la dirección Y está bloqueado
                posY++;
            } else if (posY > objetivoY && mapa[posX][posY - 1] != '*' && mapa[posX][posY - 1] != '?') {
                posY--;
            } else {
                // Si ambos caminos están bloqueados, buscar una ruta alternativa
                if (mapa[posX + 1][posY] != '*' && posX < mapa.length - 1) {
                    posX++;
                } else if (mapa[posX - 1][posY] != '*' && posX > 0) {
                    posX--;
                } else if (mapa[posX][posY + 1] != '*' && posY < mapa[0].length - 1) {
                    posY++;
                } else if (mapa[posX][posY - 1] != '*' && posY > 0) {
                    posY--;
                }
            }
        }
    }
}
