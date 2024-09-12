
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Oleada {

    public List<Enemigo> enemigos;
    public Random rand = new Random();

    // Constructor que recibe una lista de enemigos ya creada
    public Oleada(List<Enemigo> enemigos) {
        this.enemigos = enemigos;  // Usar la lista pasada como parámetro
    }

    // Método para iniciar la oleada con enemigos según el nivel
    public void iniciarOleada(Mapa mapa, List<DefensaEstandar> miDefensas,PuntajeMagico puntosMagia ) {
        // Asignar posiciones aleatorias iniciales a los enemigos
        for (Enemigo enemigo : enemigos) {
            manejoPosicion(enemigo, mapa);
            mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.representacion);
        }

        // Ciclo de movimiento de los enemigos
        while (!enemigos.isEmpty()) {
            Mapa.imprimirMapa(mapa.getMapa());  // Mostrar el mapa actualizado

            List<Enemigo> eliminados = new ArrayList<>();
            List<DefensaEstandar> miDefensasEliminados = new ArrayList<>();
            boolean todosEnCerro = true; // Bandera para verificar si todos los enemigos vivos están en el cerro

            for (Enemigo enemigo : enemigos) {
                // Limpiar la posición anterior del enemigo
                mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.');

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {
                    todosEnCerro = false; // Si hay algún enemigo vivo fuera del cerro, cambiar la bandera

                    if (rand.nextDouble() < 0.05) {  // 0.05 representa una probabilidad del 5%
                        enemigo.activarHabilidadEspecial();
                    }

                    manejarAtaques(miDefensas,miDefensasEliminados, enemigo);

                    // Mover al enemigo hacia el Cerro de la Gloria si no se topa con una barrera
                    if ( !sePuedeMoverEnemigo(miDefensas,enemigo) ){
                        enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY());
                    }

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
                    System.out.println("Recompensa: "+enemigo.getClass().getSimpleName()+"+"+enemigo.getRecompensa());
                    puntosMagia.aumentarMagia(enemigo.getRecompensa());

                    eliminados.add(enemigo);  // Agregar a la lista de eliminados
                    mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.'); //Si el enemigo fue eliminado se remueve del mapa
                }

                if(enemigo.habilidadEnUso){
                    enemigo.habilidadEnUso = false; //Desactivar la habilidad especial luego de cada iteración
                }
            }

            // Eliminar enemigos que han sido eliminados o llegaron al Cerro de la Gloria
            enemigos.removeAll(eliminados);

            //Elimina del mapa a las barreras y torres que han sido destruidas
            mapa.quitarDefensas(miDefensasEliminados);
            miDefensas.removeAll(miDefensasEliminados);

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

    // Método para asignar posiciones aleatorias a los enemigos
    private void manejoPosicion(Enemigo enemigo, Mapa mapa) {
        int tamañoMapa = mapa.getTamañoMapa();
        int mitadMapa = tamañoMapa / 2;
        int posX = 0, posY = 0;

        // Asignar posición aleatoria según el tipo de enemigo
        if (enemigo instanceof Enano) {
            // Primer cuadrante: una de las coordenadas siempre será 0
            do{
                posX = rand.nextInt(mitadMapa);  // Genera un valor entre 0 y n/2 - 1
                posY = rand.nextInt(mitadMapa);  // Genera un valor entre 0 y n/2 - 1
                if (rand.nextBoolean()) {
                    posX = 0;  // Posibilidad de que posX sea 0
                } else {
                    posY = 0;  // Posibilidad de que posY sea 0
                }
            }while (mapa.verificarLugar(posX,posY));

        } else if (enemigo instanceof Humano) {
            // Segundo cuadrante: una de las coordenadas siempre será 0
            do{
                posX = rand.nextInt(mitadMapa);  // Genera un valor entre 0 y n/2 - 1
                posY = rand.nextInt(mitadMapa+1 ,tamañoMapa);  // Genera un valor entre n/2 y n - 1
                if (rand.nextBoolean()) {
                    posX = 0;  // Posibilidad de que posX sea 0
                } else {
                    posY = tamañoMapa-1;  // Posibilidad de que posY esté en el inicio del cuadrante
                }
            }while (mapa.verificarLugar(posX,posY));
        } else if (enemigo instanceof Hobbit) {
            // Tercer cuadrante: una de las coordenadas siempre será 0
            do{
                posX =rand.nextInt(mitadMapa+1,tamañoMapa);  // Genera un valor entre n/2 y n - 1
                posY = rand.nextInt(mitadMapa);  // Genera un valor entre 0 y n/2 - 1
                if (rand.nextBoolean()) {
                    posX = tamañoMapa-1;  // Posibilidad de que posX esté en el inicio del cuadrante
                } else {
                    posY = 0;  // Posibilidad de que posY sea 0
                }
            }while (mapa.verificarLugar(posX,posY));
        } else if (enemigo instanceof Elfo) {
            // Cuarto cuadrante: una de las coordenadas siempre será 0
            do{
                posX = rand.nextInt(mitadMapa+1,tamañoMapa);  // Genera un valor entre n/2 y n - 1
                posY = rand.nextInt(mitadMapa+1,tamañoMapa);  // Genera un valor entre n/2 y n - 1
                if (rand.nextBoolean()) {
                    posX = tamañoMapa-1;  // Posibilidad de que posX esté en el inicio del cuadrante
                } else {
                    posY = tamañoMapa-1;  // Posibilidad de que posY esté en el inicio del cuadrante
                }
            }while (mapa.verificarLugar(posX,posY));
        }

        // Asignar la nueva posición al enemigo
        enemigo.setPosX(posX);
        enemigo.setPosY(posY);
        // Actualizar el mapa con la nueva posición del enemigo
        mapa.setElemento(posX, posY, enemigo.getRepresentacion());
    }

    // Metodo que permite al enemigo atacar a las defensas y viseversa
    private void manejarAtaques(List<DefensaEstandar> miDefensas, List<DefensaEstandar> miDefensasEliminados, Enemigo enemigo) {
        // Las torres atacan al enemigo y reciben daño del enemigo
        for (DefensaEstandar defensa : miDefensas) {
            // Si la defensa es una torre, verifica y realiza los ataques correspondientes
            if (defensa instanceof Torre torre) {
                if (torre.enemigoEnRango(enemigo)) {
                    torre.lanzarAtaque(enemigo);

                    // Si la habilidad especial está activada, el enemigo la usa
                    if (enemigo.habilidadEnUso) {
                       enemigo.activarHabilidadEspecial();
                    }

                    enemigo.recibirAtaque(torre);
                    enemigo.informarEstado();
                }
            }

            // Verificar si el enemigo está en rango de cualquier defensa (torres y barreras)
            if (enemigo.defensaEnRango(defensa)) {
                enemigo.lanzarAtaque(defensa);

                if (enemigo.habilidadEnUso) {
                   enemigo.activarHabilidadEspecial();
                }

                defensa.recibirAtaque(enemigo);
                defensa.informarEstado();
            }

            // Manejo de eliminación
            if (defensa.getResistencia() <= 0) {
                miDefensasEliminados.add(defensa);
            }
        }
    }

    private boolean sePuedeMoverEnemigo(List<DefensaEstandar> miDefensas, Enemigo enemigo) {
        for (DefensaEstandar defensa : miDefensas) {
            if (defensa instanceof Barrera) {
                if (defensa.enemigoEnRango(enemigo)) {
                    return true;
                }
            }
        }
        return false;
    }


}
