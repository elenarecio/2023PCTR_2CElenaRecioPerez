import java.util.Random;
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
	public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {

	    int tiempoEspera = new Random().nextInt(5) + 1;
	    Thread.sleep(tiempoEspera * 1000);

	    comprobarAntesDeGenerar(tipoEnemigo);

	    int totalEnemigos = sumarContadores();
	    while (totalEnemigos >= MAXENEMIGOS) {
	        wait();
	        totalEnemigos = sumarContadores();
	    }

	    int enemigosTipo = contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    enemigosTipo++;
	    contadoresEnemigosTipo.put(tipoEnemigo, enemigosTipo);

	    int enemigosTotal = contadorEnemigosTotales.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    enemigosTotal++;
	    contadorEnemigosTotales.put(tipoEnemigo, enemigosTotal);

	    System.out.println("   ");
	    System.out.println("Generado enemigo tipo " + tipoEnemigo);
	    imprimirInfo();

	    checkInvariante();

	    notifyAll();
	}

	public synchronized void eliminarEnemigo(int tipoEnemigo) throws InterruptedException {

	    comprobarAntesDeEliminar(tipoEnemigo);

	    int enemigosTipo = contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    while (enemigosTipo <= MINENEMIGOS) {
	        wait();
	        enemigosTipo = contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    }

	    enemigosTipo--;
	    contadoresEnemigosTipo.put(tipoEnemigo, enemigosTipo);

	    int enemigosTotal = contadorEnemigosTotales.get(tipoEnemigo);
	    enemigosTotal--;
	    contadorEnemigosTotales.put(tipoEnemigo, enemigosTotal);

	    int enemigosEliminadosTipo = contadoresEliminadosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    enemigosEliminadosTipo++;
	    contadoresEliminadosTipo.put(tipoEnemigo, enemigosEliminadosTipo);

	    System.out.println("   ");
	    System.out.println("Eliminado enemigo tipo " + tipoEnemigo);
	    imprimirInfo();

	    checkInvariante();

	    notifyAll();
	}

	
	private void imprimirInfo() {
        int totalEnemigos = sumarContadores();
        System.out.println("--> Enemigos totales: " + totalEnemigos);
        for (int i = 0; i < MAX_TIPOS_ENEMIGOS; i++) {
            System.out.println("----> Enemigos tipo " + i + ": " + contadoresEnemigosTipo.get(i) + " ------ [Eliminados:" + contadoresEliminadosTipo.get(i) + "]");
            }
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
