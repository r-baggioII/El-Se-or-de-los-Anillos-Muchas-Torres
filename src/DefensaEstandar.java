public abstract class DefensaEstandar extends Defensa {
    public int resistencia;
    public int costoMagia;

    public DefensaEstandar(int posX, int posY, char nombreDefensa, int resistencia, int costoMagia){
        super(posX,posY,nombreDefensa);
        this.resistencia = resistencia;
        this.costoMagia = costoMagia;
    }

    public int getResistencia(){
        return resistencia; 
    }
    public int getCosto(){
        return costoMagia; 
    }
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
    
    protected void colocarEnMapa(Mapa maps,int x,int y, char nombreTorre ){
        //Verifica que la posición elegida sea correcta s
        if (x == maps.cerroGloria.getPosX() && y == maps.cerroGloria.getPosY()) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que la torre principal.");
        } else if (x == maps.getTamañoMapa() / 2 || y == maps.getTamañoMapa() / 2) {
            throw new IllegalArgumentException("No se puede colocar una torre en la misma posición que las fronteras de los cuatro reinos.");
        }
        this.nombreDefensa = nombreTorre;
        //Coloca la torre en el mapa
        this.posX = x;
        this.posY = y;
        //colocarEnMapa(maps.getMapa());
        char [][] mapa = maps.getMapa();
        mapa[x][y] = nombreTorre;
    }

}



