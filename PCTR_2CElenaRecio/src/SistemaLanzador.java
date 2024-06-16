/**
 * Contiene la clase principal del juego
 * 
 * @author Elena Recio Pérez
 * @date 16/06/2024
 * @version 2 Convocatoria
 * 
 * */

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
