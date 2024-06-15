package simulador_juego.solucion;

public class ActividadAliada extends Thread{
	private int tipoEnemigo;
	private IJuego juego;
	
	public ActividadAliada(int tipoEnemigo, IJuego juego) {
		this.tipoEnemigo=tipoEnemigo;
		this.juego=juego;
	}
	
	public void run() {
		
	}

}
