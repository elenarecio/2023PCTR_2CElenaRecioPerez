import java.util.Random;
import java.util.Hashtable;

/**
 * Hace la creacion y eliminacion de enemigos.
 * Esta clase representa todo el juego.
 * 
 * @author Elena Recio Pérez
 * @date 16/06/2024
 * @version 2 Convocatoria
 * 
 * */

public class Juego implements IJuego {
	private static Juego juego;
	
	private Hashtable<Integer, Integer> contadorEnemigosTotales;
	private Hashtable<Integer, Integer> contadoresEnemigosTipo;
	private Hashtable<Integer, Integer> contadoresEliminadosTipo;
	
	private static final int MAXENEMIGOS=10;
	private static final int MINENEMIGOS=0;
	private static final int MAX_TIPOS_ENEMIGOS=4;	
	
	private Juego() {
        contadorEnemigosTotales = new Hashtable<>();
        contadoresEnemigosTipo = new Hashtable<>();
        contadoresEliminadosTipo = new Hashtable<>();
        for (int i = 0; i < MAX_TIPOS_ENEMIGOS; i++) {
            contadorEnemigosTotales.put(i, 0);
            contadoresEnemigosTipo.put(i, 0);
            contadoresEliminadosTipo.put(i, 0);
        }
    }
	
	public static synchronized Juego getJuego() {
		if(juego == null) {
			juego = new Juego();
		}
		return juego;
	}
	
	
	public synchronized void generarEnemigo(int tipoEnemigo) throws InterruptedException {
	    // Esperamos entre 1 y 5s
	    int tiempoEspera = new Random().nextInt(5) + 1;
	    Thread.sleep(tiempoEspera * 1000);
	    
	    // Comprobamos las precondiciones
	    comprobarAntesDeGenerar(tipoEnemigo);

	    //Si el numero de enemigos pasa el maximo, se espera
	    int totalEnemigos = sumarContadores();
	    while (totalEnemigos >= MAXENEMIGOS) {
	        wait();
	        totalEnemigos = sumarContadores();
	    }

	    //Actualizamos los contadores de los tipos de enemigos y el total
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
	    // Comprobamos las precondiciones
	    comprobarAntesDeEliminar(tipoEnemigo);

	    // Esperar si el número de enemigos del tipo especificado es menor o igual al mínimo permitido
	    int enemigosTipo = contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    while (enemigosTipo <= MINENEMIGOS) {
	        wait();
	        enemigosTipo = contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS);
	    }

	    //Actualizo los contadores del tipo de enemigo y el total
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
	
	public int sumarContadores() {
        return contadorEnemigosTotales.values().stream().mapToInt(Integer::intValue).sum();
    }
	
	protected void checkInvariante() {
        assert sumarContadores() == contadoresEnemigosTipo.values().stream().mapToInt(Integer::intValue).sum() :
                "La suma de los enemigos no coincide con el total";
    }
	
	protected void comprobarAntesDeGenerar(int tipoEnemigo) throws InterruptedException {
	    //Si no se han genrado enemigos del tipo anterior al que se quiere, se espera
	    if (tipoEnemigo > 0) {
	        while (contadorEnemigosTotales.get(tipoEnemigo - 1) == 0) {
	            wait();
	        }
	    }
	    //Si el numero de enemigos es igual o mayor al maximo permitido, se espera.
	    while (sumarContadores() >= MAXENEMIGOS) {
	        wait();
	    }
	}
	
	protected void comprobarAntesDeEliminar(int tipoEnemigo) throws InterruptedException {
	    //Si no hay todavia enemigos de x tipo para eliminar, se espera.
	    while (contadoresEnemigosTipo.getOrDefault(tipoEnemigo, MINENEMIGOS) <= MINENEMIGOS) {
	        wait();
	    }
	}

}

