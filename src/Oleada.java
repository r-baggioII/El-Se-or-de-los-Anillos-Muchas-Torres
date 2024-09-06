import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Oleada {

    public List<Enemigo> enemigos;

    // Constructor que recibe una lista de enemigos ya creada
    public Oleada(List<Enemigo> enemigos) {
        this.enemigos = enemigos;  // Usar la lista pasada como parámetro
    }

    // Método para iniciar la oleada con enemigos según el nivel
    public void iniciarOleada(Mapa mapa, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera) {
        // Asignar posiciones aleatorias iniciales a los enemigos
        for (Enemigo enemigo : enemigos) {
            asignarPosicionAleatoria(enemigo, mapa);
            mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.representacion);

        }

        // Ciclo de movimiento de los enemigos
        while (!enemigos.isEmpty()) {
            Mapa.imprimirMapa(mapa.getMapa());  // Mostrar el mapa actualizado

            List<Enemigo> eliminados = new ArrayList<>();
            List<DefensaEstandar> torreEliminados = new ArrayList<>();
            List<DefensaEstandar> barreraEliminados = new ArrayList<>();
            boolean todosEnCerro = true; // Bandera para verificar si todos los enemigos vivos están en el cerro

            for (Enemigo enemigo : enemigos) {
                // Limpiar la posición anterior del enemigo
                mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.');

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {
                    todosEnCerro = false; // Si hay algún enemigo vivo fuera del cerro, cambiar la bandera

                    // Las torres atacan al enemigo y reciben daño del enemigo
                    for (DefensaEstandar torre : miTorres) {
                        if (torre instanceof Torre currentTorre) {

                            if (currentTorre.enemigoEnRango(enemigo)) {
                                currentTorre.lanzarAtaque(enemigo);
                                enemigo.recibirAtaque(currentTorre);
                                enemigo.informarEstado();
                            }

                            if (enemigo.defensaEnRango(currentTorre)) {
                                enemigo.lanzarAtaque(currentTorre);
                                currentTorre.recibirAtaque(enemigo);
                                torre.informarEstado();
                            }

                            if (torre.getResistencia() <= 0) {
                                torreEliminados.add(torre);
                            }
                        }
                    }

                    // Las defensas reciben ataques de los enemigos
                    for (DefensaEstandar barrera : miBarrera) {
                        if (barrera instanceof Barrera currentBarrera) {

                            if (enemigo.defensaEnRango(barrera)) {
                                currentBarrera.recibirAtaque(enemigo);
                            }

                            barrera.informarEstado();
                        }

                        if (barrera.getResistencia() <= 0) {
                            barreraEliminados.add(barrera);
                        }
                    }

                    // Mover al enemigo hacia el Cerro de la Gloria
                    enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY());

                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() == mapa.cerroGloria.getPosX() && enemigo.getPosY() == mapa.cerroGloria.getPosY()) {
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

            //Elimina del mapa a las barreras y torres que han sido destruidas
            //eliminarDeMapa(mapa,barreraEliminados,torreEliminados);
            miBarrera.removeAll(barreraEliminados);
            miTorres.removeAll(torreEliminados);

            // Verificar si todos los enemigos han sido eliminados
            if (enemigos.isEmpty()) {
                System.out.println("Todos los enemigos han sido eliminados. La oleada ha terminado.");
                break;
            }

            // Verificar si todos los enemigos que siguen vivos están en el Cerro de la Gloria
            if (todosEnCerro) {
                System.out.println("Todos los enemigos vivos han llegado al Cerro de la Gloria. La oleada ha terminado.");
                break;
            }

            // Pausa para simular el movimiento
            try {
                Thread.sleep(1000);  // Pausa de 1000 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Mapa.imprimirMapa(mapa.getMapa());  // Mostrar el mapa actualizado después de la Oleada


    }

    private void eliminarDeMapa(Mapa mapa,List<DefensaEstandar> torreEliminados, List<DefensaEstandar> barerraEliminados ) {
        for (DefensaEstandar torre : torreEliminados) {
            mapa.setElemento(torre.getPosX(), torre.getPosY(), '.');
        }
        for (DefensaEstandar barrera : barerraEliminados) {
            mapa.setElemento(barrera.getPosX(), barrera.getPosY(), '.');
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
                posX = rand.nextInt(limiteX);
                posY = rand.nextInt(limiteY);
            } while (mapa.getElemento(posX,posY) != '.' );


        } else if (enemigo instanceof Humano) {
            // Segundo cuadrante
            do {
                posX = rand.nextInt(limiteX);
                posY = mitadMapa + rand.nextInt(limiteY);
            } while (mapa.getElemento(posX,posY) != '.' );


        } else if (enemigo instanceof Hobbit) {
            // Tercer cuadrante
            do {
                posX = mitadMapa + rand.nextInt(limiteX);
                posY = rand.nextInt(limiteY);
            } while (mapa.getElemento(posX,posY) != '.' );

        } else if (enemigo instanceof Elfo) {
            // Cuarto cuadrante
            do {
                posX = mitadMapa + rand.nextInt(limiteX);
                posY = mitadMapa + rand.nextInt(limiteY);
            } while (mapa.getElemento(posX,posY) != '.' );
        }

        // Asignar la nueva posición al enemigo
        enemigo.setPosX(posX);
        enemigo.setPosY(posY);
    }
}
