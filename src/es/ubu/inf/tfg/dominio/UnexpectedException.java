package es.ubu.inf.tfg.dominio;

/**
 * Clase UnexpectedException que extiende de la clase RuntimeException
 * Esta clase es utilizada para representar una excepción inesperada en tiempo de ejecución
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class UnexpectedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe una causa y la pasa a la superclase RuntimeException
	 * @param cause - La causa de la excepción
	 */
	public UnexpectedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}	
}
