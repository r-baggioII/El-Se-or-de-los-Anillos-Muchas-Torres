import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Oleada {

    Random rand = new Random();
    private List<Enemigo> enemigos;

    // Constructor que recibe una lista de enemigos ya creada
    public Oleada(List<Enemigo> enemigos) {
        this.enemigos = enemigos;  // Usar la lista pasada como parámetro
    }

    // Método para iniciar la oleada con enemigos según el nivel
    public void iniciarOleada(Mapa mapa, int nivelActual, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera) {
        // Asignar posiciones aleatorias iniciales a los enemigos
        for (Enemigo enemigo : enemigos) {
            asignarPosicionAleatoria(enemigo, mapa);
            mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.representacion);
        }


        // Ciclo de movimiento de los enemigos
        while (!enemigos.isEmpty()) {
            mapa.clearScreen();  // Limpiar el mapa antes de imprimir
            Mapa.imprimirMapa(mapa.getMapa());  // Mostrar el mapa actualizado

            List<Enemigo> eliminados = new ArrayList<>();
            List<DefensaEstandar> torreEliminados = new ArrayList<>();
            List<DefensaEstandar> barreraEliminados = new ArrayList<>();
            int ataquesActuales = 0;

            for (Enemigo enemigo : enemigos) {
                // Limpiar la posición anterior del enemigo
                mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.');

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {
                    // Las torres atacan al enemigo y reciben daño del enemigo
                    for (DefensaEstandar torre : miTorres) {
                        if (torre instanceof Torre currentTorre) {
                            currentTorre.lanzarAtaque(enemigo);
                            currentTorre.recibirAtaque(enemigo);
                            enemigo.informarEstado();
                            torre.informarEstado();

                            if (torre.getResistencia() <= 0) {
                                torreEliminados.add(torre);
                            }
                            // El enemigo se mueve hacia la torre
                            enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY());
                        }
                    }

                    // Las defensas reciben ataques de los enemigos
                    for (DefensaEstandar barrera : miBarrera) {
                        if (barrera instanceof Barrera currentBarrera) {
                            currentBarrera.recibirAtaque(enemigo);
                            barrera.informarEstado();
                        }
                        if (barrera.getResistencia() <= 0) {
                            barreraEliminados.add(barrera);
                        }
                        enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY());
                    }




                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() == mapa.cerroGloria.getPosX() && enemigo.getPosY() == mapa.cerroGloria.getPosY()) {
                        ataquesActuales++;
                        mapa.cerroGloria.vidas -= 1;  // Se le resta una vida al cerro de la gloria por cada ataque
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println(enemigo.getClass().getSimpleName() + " ha atacado a CERRO DE LA GLORIA. Vidas restantes: " + mapa.cerroGloria.vidas);
                        eliminados.add(enemigo);  // El enemigo que llegó al Cerro de la Gloria es eliminado
                        enemigo.setSalud(0);
                    } else {
                        mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.getRepresentacion());  // Colocar al enemigo en su nueva posición
                    }
                } else {
                    eliminados.add(enemigo);  // Agregar a la lista de eliminados
                    mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.'); //Si el enemigo fue eliminado se remueve del mapa
                }
            }

            // Eliminar enemigos que han sido eliminados o llegaron al Cerro de la Gloria
            enemigos.removeAll(eliminados);
            miBarrera.removeAll(barreraEliminados);
            //miTorres.removeAll(torreEliminados);


            if (ataquesActuales > 0 && ataquesActuales == enemigos.size()) {
                System.out.println("¡Los Enemigos han atacado al cerro de la gloria!");
                break;
            }

            // Pausa para simular el movimiento
            try {
                Thread.sleep(1000);  // Pausa de 1000 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Mapa.imprimirMapa(mapa.getMapa());  // Mostrar el mapa actualizado después de terminar la oleada

        if (enemigos.isEmpty()) {
            System.out.println("¡Todos los enemigos han sido eliminados!");
        }

    }


    // Método para asignar posiciones aleatorias a los enemigos
    private void asignarPosicionAleatoria(Enemigo enemigo, Mapa mapa) {
        int tamañoMapa = mapa.getTamañoMapa();
        int mitadMapa = tamañoMapa / 2;
        Random rand = new Random();

        int limiteX = mitadMapa;
        int limiteY = mitadMapa;

        int posX = 0, posY = 0;

        // Asignar posición aleatoria según el tipo de enemigo
        if (enemigo instanceof Enano) {
            // Primer cuadrante
            do {
                posX = rand.nextInt(limiteX);  // 0 a n/2 - 1
                posY = rand.nextInt(limiteY);  // 0 a n/2 - 1
            } while (mapa.getElemento(posX,posY) != '.' || mapa.getElemento(posX,posY) == '*');
        } else if (enemigo instanceof Humano) {
            // Segundo cuadrante
            do {
                posX = rand.nextInt(limiteX);  // 0 a n/2 - 1
                posY = mitadMapa + rand.nextInt(limiteY);  // n/2 a n - 1
            } while (mapa.getElemento(posX,posY) != '.' || mapa.getElemento(posX,posY) == '*');
        } else if (enemigo instanceof Hobbit) {
            // Tercer cuadrante
            do {
                posX = mitadMapa + rand.nextInt(limiteX);  // n/2 a n - 1
                posY = rand.nextInt(limiteY);  // 0 a n/2 - 1
            } while (mapa.getElemento(posX,posY) != '.' || mapa.getElemento(posX,posY) == '*');
        } else if (enemigo instanceof Elfo) {
            // Cuarto cuadrante
            do {
                posX = mitadMapa + rand.nextInt(limiteX);  // n/2 a n - 1
                posY = mitadMapa + rand.nextInt(limiteY);  // n/2 a n - 1
            } while (mapa.getElemento(posX,posY) != '.' || mapa.getElemento(posX,posY) == '*');
        }

        // Asignar la nueva posición al enemigo
        enemigo.setPosX(posX);
        enemigo.setPosY(posY);
    }
}
