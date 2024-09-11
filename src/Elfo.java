public class Elfo extends Enemigo {
    int turnos;
    boolean superAtaque;
    public Elfo (int posX,int posY){
        super( 120, 3, 10,'\u2694', posX,posY,15);
        this.turnos = 5;
    }

    //Esta habilidad permite al elfo atacar a distancia por tiempo limitado
    public void superTiro(){
        this.rangoAtaque+=3;
    }

    public void restarTiros(){
        turnos--;
        if (turnos <= 0){
            this.superAtaque = false;
            this.rangoAtaque -= 3;
        }
    }

    public int getTurnos(){
        return turnos;
    }
    public boolean getSuperAtaque(){
        return superAtaque;
    }

    @Override
    public void superAtaque() {
        System.out.println("\u001B[33m" + "Elfo ACTIVÓ HABILIDAD ESPECIAL" + "\u001B[0m");
        superTiro();  // Habilidad especial de Elfo
    }

}
