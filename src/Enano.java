public class Enano extends Enemigo {
    int contarPasos;
    public Enano(int posX, int posY){
        super( 90, 2, 7,'\u2692',posX,posY,5);
        contarPasos = 0;
    }

    // El enano bloquea una posicion cuando da x pasos
    public void bloquearCamino(Mapa mapa){
        mapa.setElemento(this.posX, this.posY, '*');
    }

    public void contarCamino(){
        this.contarPasos++;
    }

    @Override
    public void superAtaque(Mapa mapa) {
        System.out.println("\u001B[33m" + "Enano ACTIVÓ HABILIDAD ESPECIAL" + "\u001B[0m");
        bloquearCamino(mapa);  // Habilidad especial de Enano
    }
    
}
