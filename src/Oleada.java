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
    public void iniciarOleada(Mapa mapa, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera,int puntosMagiaActuales) {
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

                    ataquesSimultaneos(miTorres, miBarrera,torreEliminados,barreraEliminados, enemigo);

                    // Mover al enemigo hacia el Cerro de la Gloria si no se topa con una barrera
                    if ( false == distanciaEnemenigo(miBarrera,enemigo) ){
                        enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY());
                    } else if (rand.nextDouble() < 0.10) {  // 0.10 representa una probabilidad del 10%
                        superAtaque(enemigo,mapa);  // Realiza el super ataque
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
                    puntosMagiaActuales += enemigo.getRecompensa();
                    eliminados.add(enemigo);  // Agregar a la lista de eliminados
                    mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.'); //Si el enemigo fue eliminado se remueve del mapa
                }
            }

            // Eliminar enemigos que han sido eliminados o llegaron al Cerro de la Gloria
            enemigos.removeAll(eliminados);

            //Elimina del mapa a las barreras y torres que han sido destruidas
            mapa.quitarDefensas(barreraEliminados,torreEliminados);
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

    // Metodo que permite al enemigo atacar a las defensas y viseversa
    private void ataquesSimultaneos(List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera,List<DefensaEstandar> torreEliminados,List<DefensaEstandar> barreraEliminados, Enemigo enemigo) {
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
                }

                if (torre.getResistencia() <= 0) {
                    torreEliminados.add(torre);
                }else{
                    torre.informarEstado();
                }
            }
        }

        // Las defensas reciben ataques de los enemigos
        for (DefensaEstandar barrera : miBarrera) {
            if (barrera instanceof Barrera currentBarrera) {

                if (enemigo.defensaEnRango(barrera)) {
                    currentBarrera.recibirAtaque(enemigo);
                }
            }

            if (barrera.getResistencia() <= 0) {
                barreraEliminados.add(barrera);
            } else {
                barrera.informarEstado();}
        }
    }
    // Método para asignar posiciones aleatorias a los enemigos
    private void asignarPosicionAleatoria(Enemigo enemigo, Mapa mapa) {
        int tamañoMapa = mapa.getTamañoMapa();
        int mitadMapa = tamañoMapa / 2;

        int limiteX = mitadMapa;
        int limiteY = mitadMapa;

        int posX = 0, posY = 0;

        // Asignar posición aleatoria según el tipo de enemigo
        if (enemigo instanceof Enano) {
            // Primer cuadrante: una de las coordenadas siempre será 0
            do{
                posX = rand.nextInt(limiteX);  // Genera un valor entre 0 y n/2 - 1
                posY = rand.nextInt(limiteY);  // Genera un valor entre 0 y n/2 - 1
                if (rand.nextBoolean()) {
                    posX = 0;  // Posibilidad de que posX sea 0
                } else {
                    posY = 0;  // Posibilidad de que posY sea 0
                }
            }while (mapa.verificarLugar(posX,posY));

        } else if (enemigo instanceof Humano) {
            // Segundo cuadrante: una de las coordenadas siempre será 0
            do{
                posX = rand.nextInt(limiteX);  // Genera un valor entre 0 y n/2 - 1
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
                posY = rand.nextInt(limiteY);  // Genera un valor entre 0 y n/2 - 1
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

    private boolean distanciaEnemenigo( List<DefensaEstandar> miBarerras, Enemigo enemigo ) {
        int distanciaX, distanciaY;
        int posX = enemigo.getPosX(), posY = enemigo.getPosY();
        for (DefensaEstandar barrera : miBarerras) {
            distanciaX = Math.abs(posX - barrera.getPosX());
            distanciaY = Math.abs(posY - barrera.getPosY());
            if (distanciaY==1 && distanciaX ==1){
                return true;
            }
        }
        return false;
    }

    private void superAtaque(Enemigo enemigo,Mapa mapa){  // Realiza el super ataque
        if (enemigo instanceof Enano) {
            System.out.println("Enano ACTIVO HABILIDAD ESPECIAL");
            ((Enano) enemigo).bloquearCamino(mapa);
        } else if (enemigo instanceof Hobbit) {
            System.out.println("Hobbit ACTIVO HABILIDAD ESPECIAL");
            ((Hobbit) enemigo).sigiloHobit();
        } else if (enemigo instanceof Elfo) {
            System.out.println("Elfo ACTIVO HABILIDAD ESPECIAL");
            ((Elfo) enemigo).ataqueDistancia();
        } else if (enemigo instanceof Humano) {
            System.out.println("Hobbit ACTIVO HABILIDAD ESPECIAL");
            ((Humano) enemigo).escudoProtecion();
        }

    }

}
