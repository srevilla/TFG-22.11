package es.ubu.inf.tfg.dominio;

import java.util.List;
import java.util.Objects;

/**
 * Clase Pregunta que representa una pregunta del banco de preguntas.
 * Contiene una lista de objetos de la clase Opcion, un enunciado y un titulo.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class Pregunta {

	/**
	 * Una lista de opciones asociadas a la pregunta
	 */
	private List<Opcion> opciones;

	/**
	 * El enunciado de la pregunta
	 */
	private String enunciado;

	/**
	 * El título de la pregunta
	 */
	private String titulo;

	/**
	 * Constructor para inicializar una pregunta
	 * @param opciones - Una lista de opciones asociadas a la pregunta
	 * @param enunciado - El enunciado de la pregunta
	 * @param titulo - El título de la pregunta
	 */
	public Pregunta (List<Opcion> opciones, String enunciado, String titulo) {
		this.opciones = opciones;
		this.enunciado = enunciado;
		this.titulo = titulo;
	}	

	/**
	 * Calcula el código hash para la pregunta
	 * @return un código hash para la pregunta
	 */
	@Override
	public int hashCode() {
		return Objects.hash(enunciado, opciones, titulo);
	}

	/**
	 * Determina si dos preguntas son iguales
	 * @param obj - Un objeto a comparar con la pregunta actual
	 * @return verdadero si obj es igual a la pregunta actual, falso de lo contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pregunta other = (Pregunta) obj;
		return Objects.equals(enunciado, other.enunciado) && Objects.equals(opciones, other.opciones)
				&& Objects.equals(titulo, other.titulo);
	}

	/**
	 * Obtiene la lista de opciones asociadas a la pregunta
	 * @return la lista de opciones asociadas a la pregunta
	 */
	public List<Opcion> getOpciones() {
		return opciones;
	}

	/**
	 * Establece la lista de opciones asociadas a la pregunta
	 * @param opciones - La nueva lista de opciones asociadas a la pregunta
	 */
	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	/**
	 * Obtiene el enunciado de la pregunta
	 * @return el enunciado de la pregunta
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * Establece el enunciado de la pregunta
	 * @param enunciado - El enunciado de la pregunta
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * Obtiene el titulo de la pregunta
	 * @return el titulo de la pregunta
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el titulo de la pregunta
	 * @param titulo - El titulo de la pregunta
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
