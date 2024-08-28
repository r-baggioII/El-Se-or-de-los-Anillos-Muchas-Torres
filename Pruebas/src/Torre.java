public class Torre {
    private int posX;
    private int posY;
    private int rango;

    public Torre(int x, int y, int rango) {
        this.posX = x;
        this.posY = y;
        this.rango = rango;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void atacarBot(Bot bot) {
        int distancia = Math.abs(bot.getPosX() - posX) + Math.abs(bot.getPosY() - posY);
        if (distancia <= rango) {
            System.out.println("¡Bot atacado por la torre en (" + posX + ", " + posY + ")!");
            bot.recibirDaño();
        }
    }
        /*
        // Enemigo cololado en el mapa

        Humano humano = new Humano(0,this.size/2-1);
        Enano eneano = new Enano(this.size/2-1,this.size);


        this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion(); // '' representa al bot enemigo

        // Ciclo de movimiento del bot
        while (humano.getPosX() != this.torreX || humano.getPosY() != this.torreY) {

            maps.clearScreen();
            maps.imprimirMapa(this.mapa);

            // Limpiar la posición anterior del bot
            this.mapa[humano.getPosX()][humano.getPosY()] = '.';

            // Mover el bot hacia la torre
            if (humano.esEliminado != false ){
                torre.recibirAtaque(humano,torre);
                torre.lanzarAtaque(humano,torre);
                humano.moverHacia(this.torreX,this.torreY);

                // Actualizar la nueva posición del bot
                if (humano.getPosX() == this.torreX && humano.getPosY() == this.torreY && cont!=oleada) {
                    maps.imprimirMapa(this.mapa);
                    System.out.println("Bot a atacado a Torre");

                    new Humano(0, this.size);

                    cont++;
                    this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion(); // 'B' representa al bot enemigo
                }else{
                    this.mapa[humano.getPosX()][humano.getPosY()] = humano.getRepresentacion();
                }

                // Pausa para ver el movimiento
                try {
                    Thread.sleep(1000); // Pausa de 500 ms para simular el movimiento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Bot Eliminado");
                break;
            }
        }
        if (humano.getPosX() == this.torreX && humano.getPosY() == this.torreY) {
            System.out.println("¡El bot ha alcanzado la torre " + cont + " veces!");

        }

    */
}