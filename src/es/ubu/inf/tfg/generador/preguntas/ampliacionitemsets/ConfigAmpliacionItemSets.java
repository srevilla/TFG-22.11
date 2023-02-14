package es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets;

/**
 * Clase que define la configuración de la pregunta Ampliación de Item Sets
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class ConfigAmpliacionItemSets {

	/**
	 * Atributo que almacena el numero de preguntas que se van a generar.
	 */
	private int numPreguntas;

	/**
	 * Atributo que almacena el numero de item sets del conjunto.
	 */
	private int numItemSets;

	/**
	 * Atributo que almacena el tamaño de cada item set del conjunto.
	 */
	private int tamItemSets;

	/**
	 * Constructor que inicializa los datos necesarios para la configuracion.
	 * @param numPreguntas - El numero de preguntas a generar
	 * @param numItemSets - El numero de item sets del conjunto 
	 * @param tamItemSets - El tamaño de cada item set del conjunto
	 */
	public ConfigAmpliacionItemSets(int numPreguntas, int numItemSets, int tamItemSets) {
		this.numPreguntas = numPreguntas;
		this.numItemSets = numItemSets;
		this.tamItemSets = tamItemSets;
	}

	/**
	 * Devuelve el numero de preguntas a generar
	 * @return el numero de preguntas 
	 */
	public int getNumPreguntas() {
		return numPreguntas;
	}

	/**
	 * Establece el numero de preguntas a generar
	 * @param numPreguntas - El numero de preguntas a generar
	 */
	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}

	/**
	 * Devuelve el numero de item sets del conjunto
	 * @return el numero de item sets del conjunto
	 */
	public int getNumItemSets() {
		return numItemSets;
	}

	/**
	 * Establece el numero de item sets del conjunto
	 * @param numItemSets - El numero de item sets del conjunto
	 */
	public void setNumItemSets(int numItemSets) {
		this.numItemSets = numItemSets;
	}

	/**
	 * Devuelve el tamaño de cada item set del conjunto
	 * @return el tamaño de cada item set del conjunto
	 */
	public int getTamItemSets() {
		return tamItemSets;
	}

	/**
	 * Establece el tamaño de cada item set del conjunto
	 * @param tamItemSets - El tamaño de cada item set
	 */
	public void setTamItemSets(int tamItemSets) {
		this.tamItemSets = tamItemSets;
	}	
}
