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
            Mapa.imprimirMapa(mapa.getMapa());

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

             // Eliminar enemigos que han sido eliminados o llegaron a la torre ·············
            enemigos.removeAll(eliminados);
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

    private Enemigo generarEnemigo(Mapa mapa, int tipoEnemigo) {
        int tamañoMapa = mapa.getTamañoMapa();
        int mitadMapa = tamañoMapa / 2;
        Random rand = new Random(); // Asegúrate de que el generador de números aleatorios esté inicializado correctamente

        int limiteX = mitadMapa; // La coordenada límite en el eje X
        int limiteY = mitadMapa; // La coordenada límite en el eje Y

        switch (tipoEnemigo) {
            case 0: // Enanos
                // Generar coordenadas dentro del primer cuadrante evitando la fila y columna 9
                int enanoX;
                int enanoY;
                do {
                    enanoX = rand.nextInt(limiteX); // 0 a n/2 - 1
                    enanoY = rand.nextInt(limiteY); // 0 a n/2 - 1
                } while (enanoX == 8 || enanoY == 8); // Ajusta para evitar el límite específico
                return new Enano(enanoX, enanoY);

            case 1: // Humanos
                // Generar coordenadas dentro del segundo cuadrante evitando la fila y columna 9
                int humanoX;
                int humanoY;
                do {
                    humanoX = rand.nextInt(limiteX); // 0 a n/2 - 1
                    humanoY = mitadMapa + rand.nextInt(limiteY); // n/2 a n - 1
                } while (humanoX == 8 || humanoY == 8 + mitadMapa); // Ajusta para evitar el límite específico
                return new Humano(humanoX, humanoY);

            case 2: // Hobbits
                // Generar coordenadas dentro del tercer cuadrante evitando la fila y columna 9
                int hobbitX;
                int hobbitY;
                do {
                    hobbitX = mitadMapa + rand.nextInt(limiteX); // n/2 a n - 1
                    hobbitY = rand.nextInt(limiteY); // 0 a n/2 - 1
                } while (hobbitX == 8 + mitadMapa || hobbitY == 8); // Ajusta para evitar el límite específico
                return new Hobbit(hobbitX, hobbitY);

            case 3: // Elfos
                // Generar coordenadas dentro del cuarto cuadrante evitando la fila y columna 9
                int elfoX;
                int elfoY;
                do {
                    elfoX = mitadMapa + rand.nextInt(limiteX); // n/2 a n - 1
                    elfoY = mitadMapa + rand.nextInt(limiteY); // n/2 a n - 1
                } while (elfoX == 8 + mitadMapa || elfoY == 8 + mitadMapa); // Ajusta para evitar el límite específico
                return new Elfo(elfoX, elfoY);

            default:
                return null; // En caso de que el tipo de enemigo no sea válido
        }
    }


}
