import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import Enemigos.Enano;
import Enemigos.Enemigo;
import Enemigos.Hobbit;
import Enemigos.Humano;
import Enemigos.Elfo;




public class Oleada {

    Random rand = new Random();
    private  char[][] mapa;
    private int size ;
    private int torreX;
    private int torreY;
    private List<Enemigo> enemigos;

    public Oleada(Mapa maps){
        this.mapa = maps.getMapa();
        this.size = maps.getTamañoMapa();
        this.torreX = maps.getCerroGloriaX();
        this.torreY = maps.getCerroGloriaY();
        this.enemigos = new ArrayList<>();

    }

    public void Start(Mapa maps, Nivel nivel, Torres torre) {
        int oleada = nivel.getNivelActual()*2;
        int ataques=0;

        // Generar enemigos al inicio de la oleada
        for (int i = 0; i < oleada; i++) {
            int tipoEnemigo = rand.nextInt(oleada/2+1); // Generar un número aleatorio entre 0 y 3
            Enemigo enemigo;
            switch (tipoEnemigo) {
                case 0:
                    enemigo = new Humano(0, rand.nextInt(0,this.size / 2 - 1));
                    break;
                case 1:
                    enemigo = new Enano(0,rand.nextInt(this.size / 2 + 1, this.size));
                    break;
                case 2:
                    enemigo = new Elfo(rand.nextInt(0, this.size / 2 - 1), 0);
                case 3:
                    enemigo = new Hobbit(rand.nextInt(0,this.size / 2 + 1), 0);
                    break;
                default:
                    enemigo = new Humano(0, rand.nextInt(0,this.size / 2 - 1));
            }
            enemigos.add(enemigo);
            this.mapa[enemigo.getPosX()][enemigo.getPosY()] = enemigo.getRepresentacion();
        }

        // Ciclo de movimiento de los enemigos
        while (!enemigos.isEmpty()) {//IsEmpty. Devuelve el valor 1 (true) si hay un campo vacío; de lo contrario, devuelve el valor 0 (false).

            maps.clearScreen();
            maps.imprimirMapa(this.mapa);

            List<Enemigo> eliminados = new ArrayList<>();
            for (Enemigo enemigo : enemigos) {

                // Limpiar la posición anterior del enemigo
                this.mapa[enemigo.getPosX()][enemigo.getPosY()] = '.';

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {
                    // La torre ataca al enemigo
                    torre.lanzarAtaque(enemigo);
                    torre.recibirAtaque(enemigo);

                    // El enemigo se mueve hacia la torre
                    enemigo.moverHacia(this.mapa,this.torreX, this.torreY,this.torreX,this.torreY);

                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() == this.torreX && enemigo.getPosY() == this.torreY) {
                        ataques++;
                        System.out.println(enemigo.getClass().getSimpleName() + " ha atacado a CERRO DE LA GLORIA. Vidas:"+(oleada-ataques));
                        eliminados.add(enemigo);
                        break;
                    } else {
                        this.mapa[enemigo.getPosX()][enemigo.getPosY()] = enemigo.getRepresentacion();
                    }
                } else {
                    System.out.println(enemigo.getClass().getSimpleName() + " eliminado.");
                    eliminados.add(enemigo); // Marcar para eliminar
                }
            }
            if (ataques == oleada) {
                break;
            }

            enemigos.removeAll(eliminados); // Eliminar enemigos que han sido eliminados o llegaron a la torre


                // Pausa para simular el movimiento
            try {
                Thread.sleep(1000); // Pausa de 1000 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
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


}

