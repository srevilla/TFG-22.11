package es.ubu.inf.tfg.generador.datos.conjuntoitemsets;

import java.util.Random;

import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.ConfigAmpliacionItemSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase GeneradorConjuntoItemSets que genera un conjunto de item sets.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class GeneradorConjuntoItemSets {

	/**
	 * Atributo que almacena el numero de item sets del conjunto.
	 */
	private final int numItemSets;

	/**
	 * Atributo que almacena el tamaño de cada item set del conjunto.
	 */
	private final int tamItemSets;

	/**
	 * Minimo numero de item sets permitido.
	 */
	private static final int minNumItemSets = 10;

	/**
	 * Maximo numero de item sets permitido.
	 */
	private static final int maxNumItemSets = 16;

	/**
	 * Minimo tamaño de item sets permitido.
	 */
	private static final int minTamItemSets = 3;

	/**
	 * Maximo tamaño de item sets permitido.
	 */
	private static final int maxTamItemSets = 4;

	/**
	 * Constructor que inicializa los atributos con la configuración de ampliacion item sets proporcionada. 
	 * Si cualquiera de los valores en la configuración no se especifica, se genera un valor aleatorio
	 * dentro del rango esperado para ese valor.
	 * @param config - La configuración de ampliacion item sets.
	 */
	public GeneradorConjuntoItemSets(ConfigAmpliacionItemSets config) {
		Random r = new Random();

		if (config.getNumItemSets() == 0) {
			numItemSets = r.nextInt((maxNumItemSets - minNumItemSets) + 1) + minNumItemSets;
		} else {
			numItemSets = config.getNumItemSets();
		}

		if (config.getTamItemSets() == 0) {
			tamItemSets = r.nextInt((maxTamItemSets - minTamItemSets) + 1) + minTamItemSets;
		} else {
			tamItemSets = config.getTamItemSets();
		}
	}

	/**
	 * Este método crea un conjunto de item sets.
	 * Cada conjunto de items es generado de forma aleatoria y se asegura que no se repita en la lista.
	 * La cantidad de conjuntos de items en la lista es igual a la variable numItemSets.
	 * La lista de conjuntos de items es ordenada según su tamaño.
	 * @return la lista de conjuntos de items ordenados por su tamaño.
	 */
	public ConjuntoItemSets crearConjuntoItemSets() {
		List<List<Character>> conjuntoItemSets  = new ArrayList<>();
		List<Character> conjuntoNuevo = new ArrayList<>();

		while(conjuntoItemSets.size() < numItemSets) {
			generarItemSets();
			conjuntoNuevo = generarItemSets();
			if(!conjuntoItemSets.contains(conjuntoNuevo)) {
				conjuntoItemSets.add(conjuntoNuevo);
			}
		}		
		ordenarTotalConjuntos(conjuntoItemSets);

		return new ConjuntoItemSets(conjuntoItemSets);
	}

	/**
	 * Método que genera un conjunto de tamaño tamItemSets con letras aleatorias que no se encuentran repetidas.
	 * @return una lista de caracteres ordenados de tamaño tamItemSets.
	 */
	private List<Character> generarItemSets() {
		List<Character> conjunto = new ArrayList<>();

		char letraNueva;
		while(conjunto.size() < tamItemSets) {
			letraNueva = letraAleatoria();
			if(!conjunto.contains(letraNueva)) {
				conjunto.add(letraNueva);
			}
		}
		Collections.sort(conjunto);

		return conjunto;
	}

	/**
	 * Método que genera una letra aleatoria.
	 * @return la letra generada.
	 */
	private char letraAleatoria() {
		Random random = new Random();
		char letraAleatoria = (char) (random.nextInt(7) + 'A');
		return letraAleatoria;
	}

	/**
	 * Este método ordena los conjuntos de itemsets alfabéticamente.
	 * @param conjuntoItemSets - Lista de listas de caracteres que representan los conjuntos de itemsets.
	 */
	private void ordenarTotalConjuntos(List<List<Character>> conjuntoItemSets) {
		conjuntoItemSets.sort((x, y) -> {
			for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
				if (x.get(i) != y.get(i)) {
					return x.get(i) - y.get(i);
				}
			}
			return x.size() - y.size();
		});
	}
}




