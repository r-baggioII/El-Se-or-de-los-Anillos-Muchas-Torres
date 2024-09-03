import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Oleada {

    Random rand = new Random();
    private List<Enemigo> enemigos;

    // Constructor por defecto
    public Oleada() {
        this.enemigos = new ArrayList<>();
    }

    // Método para iniciar la oleada con enemigos según el nivel
    public void iniciarOleada(Mapa mapa, int nivelActual, List<DefensaEstandar> miTorres, List<DefensaEstandar> miBarrera) {
        int cantEnemigos;

        // Determinar la cantidad de enemigos según el nivel
        switch (nivelActual) {
            case 1: cantEnemigos = 2; break;  // Nivel 1: 2 enemigos
            case 2: cantEnemigos = 3; break;  // Nivel 2: 3 enemigos
            case 3: cantEnemigos = 4; break;  // Nivel 3: 4 enemigos
            default: cantEnemigos = 0; break; // Valor por defecto si el nivel es inválido
        }

        int ataques = 0;

        // Generar enemigos al inicio de la oleada
        for (int i = 0; i < cantEnemigos; i++) {
            Enemigo enemigo;

            // Generar el tipo de enemigo basado en el nivel
            if (nivelActual == 1) {
                // Nivel 1: Humanos o Enanos
                enemigo = generarEnemigo(mapa, rand.nextInt(2));
            } else if (nivelActual == 2) {
                // Nivel 2: Humanos, Enanos o Elfos
                enemigo = generarEnemigo(mapa, rand.nextInt(3));
            } else {
                // Nivel 3: Humanos, Enanos, Elfos o Hobbits
                enemigo = generarEnemigo(mapa, rand.nextInt(4));
            }

            enemigos.add(enemigo);
            mapa.setElemento(enemigo.getPosX(), enemigo.getPosY(), enemigo.getRepresentacion());
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
                        if (torre instanceof Torre currentTorre) {
                            currentTorre.lanzarAtaque(enemigo);
                            currentTorre.recibirAtaque(enemigo);

                            if (torre.getResistencia() <= 0) {
                                torreEliminados.add(torre);
                            }
                            // El enemigo se mueve hacia la torre
                            enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY(), currentTorre);
                        }
                    }

                    // Las defensas reciben ataques de los enemigos
                    for (DefensaEstandar barrera : miBarrera) {
                        if (barrera instanceof Barrera currentBarrera) {
                            currentBarrera.recibirAtaque(enemigo);
                        }
                        if (barrera.getResistencia() <= 0) {
                            barreraEliminados.add(barrera);
                        }
                        enemigo.moverHacia(mapa, mapa.cerroGloria.getPosX(), mapa.cerroGloria.getPosY(), barrera);
                    }

                    // Actualizar la nueva posición del enemigo
                    if (enemigo.getPosX() == mapa.cerroGloria.getPosX() && enemigo.getPosY() == mapa.cerroGloria.getPosY() && !(eliminados.contains(enemigo))) {
                        ataques++;
                        mapa.cerroGloria.vidas -= ataques; //Se le resta una vida al cerro de la gloria por cada ataque
                        System.out.println(enemigo.getClass().getSimpleName() + " ha atacado a CERRO DE LA GLORIA. Vidas: " + mapa.cerroGloria.vidas);
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

            if (ataques == cantEnemigos) {
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

    // Método para generar enemigos basado en el tipo
    private Enemigo generarEnemigo(Mapa mapa, int tipoEnemigo) {
        switch (tipoEnemigo) {
            case 0:
                return new Humano(0, rand.nextInt(0, mapa.getTamañoMapa() / 2 - 1));
            case 1:
                return new Enano(0, rand.nextInt(mapa.getTamañoMapa() / 2 + 1, mapa.getTamañoMapa()));
            case 2:
                return new Elfo(rand.nextInt(0, mapa.getTamañoMapa() / 2 - 1), 0);
            case 3:
                return new Hobbit(rand.nextInt(0, mapa.getTamañoMapa() / 2 + 1), 0);
            default:
                return new Humano(0, rand.nextInt(0, mapa.getTamañoMapa() / 2 - 1));
        }
    }
}
