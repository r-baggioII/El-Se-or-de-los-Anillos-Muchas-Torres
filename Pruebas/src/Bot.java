public class Bot {
    private int posX;
    private int posY;
    private int salud;

    public Bot(int x, int y, int saludInicial) {
        this.posX = x;
        this.posY = y;
        this.salud = saludInicial;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSalud() {
        return salud;
    }

    public void moverHacia(int objetivoX, int objetivoY) {
        if (posX < objetivoX) {
            posX++;
        } else if (posX > objetivoX) {
            posX--;
        }

        if (posY < objetivoY) {
            posY++;
        } else if (posY > objetivoY) {
            posY--;
        }
    }

    public void recibirDaño() {
        salud -= 10;
        System.out.println("¡Bot ha recibido daño! Salud restante: " + salud);
    }

    public boolean estaEliminado() {
        return salud <= 0;
    }
}