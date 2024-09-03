import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Oleada {

    Random rand = new Random();
    private Mapa mapa;
    private List<Enemigo> enemigos;

    // Constructor de la clase Oleada
    public Oleada(){} //constructor por defecto

    // Constructor de la clase Oleada
    public Oleada(Mapa mapa) {
        this.mapa = mapa;
        this.enemigos = new ArrayList<>();
    }

    public void iniciarOleada(int nivelActual, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera) {
        int oleada = nivelActual * 2;
        int ataques = 0;

        // Generar enemigos al inicio de la oleada
        for (int i = 0; i < oleada; i++) {
            int tipoEnemigo = rand.nextInt(oleada / 2 + 1); // Generar un número aleatorio entre 0 y 3
            Enemigo enemigo;
            switch (tipoEnemigo) {
                case 0:
                    enemigo = new Humano(0, rand.nextInt(0, this.mapa.getTamañoMapa() / 2 - 1));
                    break;
                case 1:
                    enemigo = new Enano(0, rand.nextInt(this.mapa.getTamañoMapa()  / 2 + 1, this.mapa.getTamañoMapa() ));
                    break;
                case 2:
                    enemigo = new Elfo(rand.nextInt(0,this.mapa.getTamañoMapa()  / 2 - 1), 0);
                    break;
                case 3:
                    enemigo = new Hobbit(rand.nextInt(0,this.mapa.getTamañoMapa()  / 2 + 1), 0);
                    break;
                default:
                    enemigo = new Humano(0, rand.nextInt(0, this.mapa.getTamañoMapa() / 2 - 1));
            }
            enemigos.add(enemigo);
            this.mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.getRepresentacion());
        }

        // Ciclo de movimiento de los enemigos
        while (!enemigos.isEmpty()) {
            mapa.clearScreen();
            Mapa.imprimirMapa(mapa.getMapa());

            List<Enemigo> eliminados = new ArrayList<>();
            List<DefensaEstandar> torreEliminados = new ArrayList<>();
            List<DefensaEstandar> barreraEliminados = new ArrayList<>();
            
            for (Enemigo enemigo : enemigos) {
                // Limpiar la posición anterior del enemigo
                mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), '.');

                // Verificar si el enemigo ha sido eliminado
                if (!enemigo.esEliminado()) {

                    // Las torres atacan al enemigo y reciben daño del enemigo
                    for (DefensaEstandar torre : miTorres) {
                        if (torre instanceof Torre) {
                            Torre currentTorre = (Torre) torre;
                            currentTorre.lanzarAtaque(enemigo);
                            currentTorre.recibirAtaque(enemigo);

                            if (torre.getResistencia() <= 0) {
                                torreEliminados.add(torre);
                            }
                            // El enemigo se mueve hacia la torre
                            enemigo.moverHacia(mapa.getMapa(), mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY(), currentTorre);
                        }
                    }

                    // Las defensas reciben ataques de los enemigos
                    for (DefensaEstandar barrera : miBarrera) {
                        if (barrera instanceof Barrera) {
                            ((Barrera) barrera).recibirAtaque(enemigo);
                        }
                        if (barrera.getResistencia() <= 0) {
                            barreraEliminados.add(barrera);
                        }
                        enemigo.moverHacia(mapa.getMapa(),  mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY(), barrera);
                    }

                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() ==mapa.cerroGloria.getPosX() && enemigo.getPosY() == mapa.cerroGloria.getPosY()) {
                        ataques++;
                        System.out.println(enemigo.getClass().getSimpleName() + " ha atacado a CERRO DE LA GLORIA. Vidas: " + (oleada - ataques));
                        eliminados.add(enemigo);
                        break;
                    } else {
                        mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.getRepresentacion());
                    }
                } else {
                    System.out.println(enemigo.getClass().getSimpleName() + " eliminado.");
                    eliminados.add(enemigo);
                }
            }

            if (ataques == oleada) {
                break;
            }

            enemigos.removeAll(eliminados); // Eliminar enemigos que han sido eliminados o llegaron a la torre
            miBarrera.removeAll(barreraEliminados);
            // miTorres.removeAll(torreEliminados); // Opcional: Descomentar si quieres eliminar las torres destruidas

            // Pausa para simular el movimiento
            try {
                Thread.sleep(1000); // Pausa de 1000 ms para simular el movimiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
