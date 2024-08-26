public class Torre {
    private int posX;
    private int posY;
    private int rango;

    public Torre(int x, int y, int rango) {
        this.posX = x;
        this.posY = y;
        this.rango = rango;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void atacarBot(Bot bot) {
        int distancia = Math.abs(bot.getPosX() - posX) + Math.abs(bot.getPosY() - posY);
        if (distancia <= rango) {
            System.out.println("¡Bot atacado por la torre en (" + posX + ", " + posY + ")!");
            bot.recibirDaño();
        }
    }
}