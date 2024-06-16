public class SistemaLanzador {
    public static void main(String[] args) {
        IJuego juego = Juego.getJuego();

        // Crear y lanzar los hilos de enemigos y aliados
        for (int i = 0; i < 4; i++) {
            ActividadEnemiga enemiga = new ActividadEnemiga(i, juego);
            new Thread (enemiga).start();
            
            ActividadAliada aliada = new ActividadAliada(i, juego);
            new Thread (aliada).start();
        }
    }
}
