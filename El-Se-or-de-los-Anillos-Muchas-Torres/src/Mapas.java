public class Mapas {

    public int torreX;
    public int torreY;
    int size = 15;
    char[][] mapa = new char[size][size];

    Torres torre = new Torres();

    public Mapas() {}

    public void inicarMapa(){
        // Inicializar el mapa vacío
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapa[i][j] = '*';
            }
        }
        // Posición de la torre en el centro del mapa
        torreX = size / 2;
        torreY = size / 2;
        mapa[torreX][torreY] = 'T'; // 'T' representa la torre


    }

    public int getSize(){
        return size;
    }
    public int getTorreX(){
        return torreX;
    }
    public int getTorreY(){
        return torreY;
    }
    public char[][] getMapa(){
        return mapa;
    }


}
