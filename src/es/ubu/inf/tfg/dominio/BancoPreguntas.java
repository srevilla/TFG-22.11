package es.ubu.inf.tfg.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase BancoPreguntas que representa un banco de preguntas.
 * Contiene una lista de objetos de la clase Pregunta y proporciona métodos 
 * para acceder y modificar esta lista.
 *
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class BancoPreguntas {


	/**
	 * Atributo que almacena la lista de preguntas asociada a un banco de preguntas.
	 */
	private List<Pregunta> preguntas = new ArrayList<>();

	/**
	 * Constructor de la clase BancoPreguntas.
	 * Inicializa la lista de preguntas con la lista proporcionada como argumento.
	 * 
	 * @param preguntas - La lista de preguntas a ser inicializada.
	 */
	public BancoPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Devuelve la lista de preguntas almacenadas en el banco de preguntas.
	 * 
	 * @return la lista de preguntas.
	 */
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	/**
	 * Establece la lista de preguntas almacenadas en el banco de preguntas.
	 * 
	 * @param preguntas - La lista de preguntas a ser almacenada.
	 */
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
