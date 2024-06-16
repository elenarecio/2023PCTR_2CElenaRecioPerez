import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Clase que creara un hilo para simular la representacion de la actividad de un aliado
 * 
 * @author Elena Recio Pérez
 * @date 16/06/2024
 * @version 2 Convocatoria
 * 
 * */

public class ActividadAliada extends Thread{
	private int tipoEnemigo;
	private IJuego juego;
	
	public ActividadAliada(int tipoEnemigo, IJuego juego) {
		this.tipoEnemigo=tipoEnemigo;
		this.juego=juego;
	}
	
	public void run() {
		//Creamos el hilo para generar enemigos
		while(!interrupted()) {
			try {
				//Tiempo de espera entre 1 y 5s y se genera el enemigo de un tipo
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
				juego.generarEnemigo(tipoEnemigo);
			} catch (InterruptedException e) {
				//finalizaria la ejecucion del hilo
				break;
			}
		}
	}
}

