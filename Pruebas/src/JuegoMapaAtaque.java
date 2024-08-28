public class JuegoMapaAtaque {
    public static void main(String[] args) {
        int size; // Tamaño del mapa
        char[][] mapa;

        SeleccionMapa Smapa = new SeleccionMapa();
        Smapa.seleccionDeMapa();
        mapa = Smapa.getMapaSeleccionado();
        size = mapa.length;
        // Posición de la torre en el centro del mapa
        int torreX = size / 2;
        int torreY = size / 2;
        Torre torre = new Torre(torreX, torreY, 3); // Torre con rango de ataque 3
        mapa[torreX][torreY] = 'T';

        // Posición inicial del bot
        int botX = 0;
        int botY = 0;
        Bot bot = new Bot(botX, botY, 30); // Bot con 30 puntos de salud

        // Ciclo de movimiento del bot
        while (!bot.estaEliminado() && (bot.getPosX() != torreX || bot.getPosY() != torreY)) {
            limpiarPantalla(); // Limpiar la pantalla

            // Imprimir el mapa
            actualizarMapa(mapa, bot, torre);
            imprimirMapa(mapa);

            // Mover el bot hacia la torre
            bot.moverHacia(torreX, torreY);

            // La torre ataca al bot si está en su rango
            torre.atacarBot(bot);

            // Pausa para ver el movimiento
            try {
                Thread.sleep(1000); // Pausa de 500 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (bot.estaEliminado()) {
            System.out.println("¡El bot ha sido eliminado por la torre!");
        } else {
            System.out.println("¡El bot ha alcanzado la torre!");
        }
    }


    public static void actualizarMapa(char[][] mapa, Bot bot, Torre torre) {
        //Limpia el mapa y mantiene las divisiones de los cuadrantes
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                // Mantener divisiones en el mapa
                if (mapa[i][j] != '|' && mapa[i][j] != '-') {
                    mapa[i][j] = '.';
                }
            }
        }
        // Colocar la torre en el mapa
        mapa[torre.getPosX()][torre.getPosY()] = 'T';
        // Colocar el bot en el mapa
        mapa[bot.getPosX()][bot.getPosY()] = 'B';
    }
    

    // Método para limpiar la pantalla
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Método para imprimir el mapa
    public static void imprimirMapa(char[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}