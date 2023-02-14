package es.ubu.inf.tfg.generador.datos.conjuntoitemsets;

import java.util.List;

/**
 * Clase ConjuntoItemSets que representa un conjunto de item sets.
 * Contiene una lista de listas de objetos de tipo Character.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class ConjuntoItemSets {

	/**
	 * Atributo que almacena el conjunto de item sets.
	 */
	private List<List<Character>> conjuntoItemSets;

	/**
	 * Constructor que inicializa el conjunto de item sets.
	 * @param conjuntoItemSets - El conjunto de item sets
	 */
	public ConjuntoItemSets(List<List<Character>> conjuntoItemSets) {
		this.conjuntoItemSets = conjuntoItemSets;
	}

	/**
	 * Devuelve el conjunto de item sets.
	 * @return el conjunto de item sets
	 */
	public List<List<Character>> getConjuntoItemSets() {
		return conjuntoItemSets;
	}

	/**
	 * Establece el conjunto de item sets.
	 * @param conjuntoItemSets - El conjunto de item sets
	 */
	public void setConjuntoItemSets(List<List<Character>> conjuntoItemSets) {
		this.conjuntoItemSets = conjuntoItemSets;
	}	
}
