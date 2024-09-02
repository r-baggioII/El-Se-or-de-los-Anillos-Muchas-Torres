public abstract class Defensa {
    public int resistencia; 
    public int costo; 
    public int posX; 
    public int posY;
    public char nombreDefensa;

    //Constructor 
    public Defensa(int resistencia, int costo, int posX, int posY,char nombreDefensa){
        this.resistencia = resistencia; 
        this.costo = costo; 
        this.posX = posX; 
        this.posY = posY; 
        this.nombreDefensa = nombreDefensa;
    }
    //Getters 
    public int getResistencia(){
        return resistencia; 
    }
    public int getCosto(){
        return costo; 
    }
    public int getPosX(){
        return posX; 
    }
    public int getPosY(){
        return posY; 
    }
    public char getNombrDefensa(){return nombreDefensa;}

    //Metodos
    public void restaurarVida(){}
    //metodo polimorfico que se implementará en las clases barrera y torre

    public void recibirAtaque(Enemigo enemigo) { //método abstracto que se implementará en las clases barrera y torre
        int distanciaX = Math.abs(this.posX - enemigo.getPosX());
        int distanciaY = Math.abs(this.posY - enemigo.getPosY());
        if (distanciaX <= enemigo.getrangoAtaque() && distanciaY <= enemigo.getrangoAtaque()) {
            this.resistencia -= enemigo.getRecompensa(); // Changed from vidaInicial to resistencia
            System.out.println("VIDA " + this.nombreDefensa + ": " + this.resistencia);
        }

        if (this.resistencia <= 0) {
            System.out.println("¡La barrera " + this.nombreDefensa + " ha sido destruida!");
        }
    }
    
    protected void colocarEnMapa(Mapa maps, Magia magia,int x,int y, char nombreTorre ){
        //Verifica que la posición elegida sea correcta s
        if (x == maps.getCerroGloriaX() && y == maps.getCerroGloriaY()) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (x == maps.getTamañoMapa() / 2 || y == maps.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las fronteras de los cuatro reinos.");
        }
        //Gastar magia al colocar la torre
        magia.gastarMagia(this.costo);
        this.nombreDefensa = nombreTorre;
        //Coloca la torre en el mapa
        this.posX = x;
        this.posY = y;
        //colocarEnMapa(maps.getMapa());
        char [][] mapa = maps.getMapa();
        mapa[x][y] = nombreTorre;
    }

}

