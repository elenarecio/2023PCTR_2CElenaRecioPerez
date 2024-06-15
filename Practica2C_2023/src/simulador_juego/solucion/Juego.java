package simulador_juego.solucion;

import java.util.Hashtable;

public class Juego {
	private static Juego juego;
	
	private Hashtable<Integer, Integer> contadorEnemigosTotales;
	private Hashtable<Integer, Integer> contadoresEnemigosTipo;
	private Hashtable<Integer, Integer> contadoresEliminadosTipo;
	
	private static final int MAXENEMIGOS=50;
	private static final int MINENEMIGOS=0;
	
	private Juego() {
		contadorEnemigosTotales=new Hashtable();
		contadoresEnemigosTipo=new Hashtable();
		contadoresEliminadosTipo=new Hashtable();
	}
	
	public static synchronized Juego getJuego() {
		if(juego == null) {
			juego = new Juego();
		}
		return juego;
	}
	public void generarEnemigo(int tipoEnemigo) throws InterruptedException {
		
	}
	
	public void eliminarEnemigo(int tipoEenmigo) {
		
	}
	
	private void  imprimirInfo(int tipoEnemigo) {
		
	}
	
	public int sumarContadores(int tipoEnemigo) {
		
	}
	
	protected void checkInvariante() {
		
	}
	
	protected synchronized void comprobarAntesDeGenerar() throws InterruptedException{
		
	}
	
	protected synchronized void comprobarAntesDeEliminar() throws InterruptedException{
		
	}
}
