/**
 * Interfaz relacionada con todos los demas archivos
 * Define los metodos para generar y eliminar enemigos
 * 
 * @author Elena Recio Pérez
 * @date 16/06/2024
 * @version 2 Convocatoria
 * 
 * */
public interface IJuego {
	//Con este metodo se genera el enemigo de un tipo
	void generarEnemigo(int tipoEnemigo) throws InterruptedException;
	
	//Con este método se elimina un enemigo de un tipo
	void eliminarEnemigo(int tipoEnemigo) throws InterruptedException;

}
