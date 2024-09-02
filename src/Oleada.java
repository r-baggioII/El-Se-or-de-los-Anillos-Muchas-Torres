import java.util.Random;
import java.util.ArrayList;
import java.util.List;


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

    public void Start(Mapa maps, Nivel nivel,List<Defensa> miTorres,List<Defensa> miBarrera) {
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
            List<Defensa> torreEliminados = new ArrayList<>();
            List<Defensa> barreraEliminados = new ArrayList<>();
            for (Enemigo enemigo : enemigos) {
                // Limpiar la posición anterior del enemigo
                this.mapa[enemigo.getPosX()][enemigo.getPosY()] = '.';

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {
                        // Las torres atacan al enemigo y reciben daño del enemigo
                        for (Defensa torre : miTorres) {
                            if (torre instanceof Torre) {
                                Torre currentTorre = (Torre) torre;
                                currentTorre.lanzarAtaque(enemigo);
                                currentTorre.recibirAtaque(enemigo);
                                
                                if ( torre.getResistencia()<=0){
                                    torreEliminados.add(torre);
                                }
                                // El enemigo se mueve hacia la torre
                                enemigo.moverHacia(this.mapa,this.torreX, this.torreY,currentTorre);

                            }
                        }
                    /*
                    for (Defensa barrera : miBarrera) {
                        if (barrera instanceof Torre) {
                            ((Barrera) barrera).lanzarAtaque(enemigo);
                            ((Barrera) barrera).recibirAtaque(enemigo);
                        }
                        if ( barrera.getResistencia()<=0){
                            barreraEliminados.add(barrera);
                        }
                    }
                    */

                   
                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() == this.torreX && enemigo.getPosY() == this.torreY) {
                        ataques++;
                        System.out.println(enemigo.getClass().getSimpleName() + " ha atacado a CERRO DE LA GLORIA. Vidas:"+(oleada-ataques));
                        eliminados.add(enemigo);
                        break;
                    } else {
                        this.mapa[enemigo.getPosX()][enemigo.getPosY()] = enemigo.getRepresentacion();
                    }
                }else {
                    System.out.println(enemigo.getClass().getSimpleName() + " eliminado.");
                    eliminados.add(enemigo); // Marcar para eliminar
                }
            }
            if (ataques == oleada) {
                break;
            }

            enemigos.removeAll(eliminados); // Eliminar enemigos que han sido eliminados o llegaron a la torre
            miBarrera.removeAll(barreraEliminados);
            //miTorres.removeAll(torreEliminados);

                // Pausa para simular el movimiento
            try {
                Thread.sleep(1000); // Pausa de 1000 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

