package es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.datos.conjuntoitemsets.ConjuntoItemSets;
import es.ubu.inf.tfg.generador.datos.conjuntoitemsets.GeneradorConjuntoItemSets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Clase GeneradorPreguntaAmpliacionItemSets que genera una pregunta de tipo Ampliacion Item Sets.
 *
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class GeneradorPreguntaAmpliacionItemSets {

	/**
	 * Atributo que almacena el generador de conjunto de item sets
	 */
	private GeneradorConjuntoItemSets gcd;

	/**
	 * Atributo que almacena el numero de opciones
	 */
	private static final int numOpciones = 4;

	/**
	 * Maxima puntuacion permitida
	 */
	private static final int maxPuntuacion = 100;

	/**
	 * Constructor que inicializa un generador de preguntas a partir de una configuracion
	 * @param config - La configuracion del tipo de pregunta
	 */
	public GeneradorPreguntaAmpliacionItemSets(ConfigAmpliacionItemSets config) {
		gcd = new GeneradorConjuntoItemSets(config);
	}

	/**
	 * Devuelve el enunciado de la pregunta.
	 * @param conjuntoItemSets - El conjunto de item sets
	 * @return el enunciado de la pregunta
	 */
	public String obtenerEnunciado(ConjuntoItemSets conjuntoItemSets) {
		return "Se dispone de los siguientes " + conjuntoItemSets.getConjuntoItemSets().get(0).size() + "-item sets: " + conjuntoItemSets.getConjuntoItemSets().toString() + "\r\n" 
				+ "¿Cuales son los posibles " + (conjuntoItemSets.getConjuntoItemSets().get(0).size()+1) + "-item sets?";
	}

	/**
	 * Devuelve el titulo de la pregunta.
	 * @return
	 */
	public String obtenerTitulo() {
		return "AmpliaciónItemSets";
	}

	/**
	 * Este método genera una pregunta con una serie de opciones.
	 * La pregunta y las opciones se generan de forma aleatoria.
	 * 
	 * @return una nueva pregunta con sus opciones asociadas.
	 */
	public Pregunta generarPregunta() {

		boolean tieneSolucion = false;
		Pregunta pregunta = null;
		int cont;

		while(!tieneSolucion) {
			Random random = new Random();
			int numRespuestasVerdaderas = random.nextInt(numOpciones-3)+1;
			int numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
			ConjuntoItemSets conjunto = gcd.crearConjuntoItemSets();
			List<List<Character>> conjuntoItemSets = conjunto.getConjuntoItemSets();
			List<List<Character>> posiblesSoluciones = generarPosiblesSoluciones(conjuntoItemSets);		
			List<List<Character>> conjuntoPermutacionesItemActual = new ArrayList<List<Character>>();
			List<List<Character>> solucionesVerdaderas = new ArrayList<List<Character>>();
			List<List<Character>> solucionesFalsas = new ArrayList<List<Character>>();

			for (List<Character> itemActualPosibleSolucion : posiblesSoluciones) {
				cont = 0;
				List<Character> itemPosibleSolucion = new ArrayList<Character>(itemActualPosibleSolucion);
				conjuntoPermutacionesItemActual = generarPermutaciones(itemActualPosibleSolucion);
				conjuntoPermutacionesItemActual = simplificarPermutaciones(conjuntoPermutacionesItemActual, conjuntoItemSets);
				conjuntoPermutacionesItemActual = conjuntoPermutacionesItemActual.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());

				for (List<Character> itemActualPermutaciones : conjuntoPermutacionesItemActual) {
					String itemActualPermutacionesString = itemActualPermutaciones.toString();
					itemActualPermutacionesString = itemActualPermutacionesString.replaceAll("[^A-Z,\s]", "");
					if (conjuntoItemSets.toString().contains(itemActualPermutacionesString)) {
						cont++;
					}
				}
				if (cont == conjuntoPermutacionesItemActual.size()) {
					solucionesVerdaderas.add(itemPosibleSolucion);
				} else {
					solucionesFalsas.add(itemPosibleSolucion);
				}
			}

			tieneSolucion = tieneSolucion(numRespuestasVerdaderas, numRespuestasFalsas, solucionesVerdaderas, solucionesFalsas);

			if(tieneSolucion) {
				List<Opcion> opciones = new ArrayList<>();
				List<List<String>> opcionesGeneradas = generarOpciones(numRespuestasVerdaderas, numRespuestasFalsas, solucionesVerdaderas, solucionesFalsas);
				List<String> opcionesVerdaderas = opcionesGeneradas.get(0);
				List<String> opcionesFalsas = opcionesGeneradas.get(1);

				double pesoVerdaderas = (double) establecerPuntuacion(numRespuestasVerdaderas);
				double pesoFalsas = -establecerPuntuacion(numRespuestasFalsas);

				for (int i=0; i<numRespuestasVerdaderas; i++) {
					String texto = opcionesVerdaderas.get(i).toString();
					Opcion opcion = new Opcion(pesoVerdaderas, texto);
					opciones.add(opcion);
				}

				for (int i=0; i<numRespuestasFalsas; i++) {
					String texto = opcionesFalsas.get(i).toString();
					Opcion opcion = new Opcion(pesoFalsas, texto);
					opciones.add(opcion);
				}

				pregunta = new Pregunta(opciones, obtenerEnunciado(conjunto), obtenerTitulo());
			}	        
		}

		return pregunta;
	}

	/**
	 * Metodo que genera una lista de todas las combinaciones posibles de los elementos de los 
	 * diferentes subconjuntos que se encuentran en el conjunto de item sets.
	 * @param conjuntoItemSets - El conjunto de item sets
	 * @return las combinaciones posibles del conjunto de item sets
	 */
	private List<List<Character>> generarPosiblesSoluciones(List<List<Character>> conjuntoItemSets) {
		List<List<Character>> posiblesSoluciones = new ArrayList<>();
		int k = 0;
		List<Character> itemActual = new ArrayList<Character>();

		for (int i=0; i<conjuntoItemSets.size(); i++) {
			while(k < conjuntoItemSets.size()-1) {
				for (int j=0; j<conjuntoItemSets.get(i).size(); j++) {
					for (Character letra : conjuntoItemSets.get(i)) {
						itemActual.add(letra);
					}
					if(!itemActual.contains(conjuntoItemSets.get(k).get(j))) {
						itemActual.add(conjuntoItemSets.get(k).get(j));
						Collections.sort(itemActual);
						if(!posiblesSoluciones.contains(itemActual)) {
							posiblesSoluciones.add(itemActual);
						}
					}
					itemActual = new ArrayList<Character>();
				}
				k++;
			}
			k=0;
		}

		return posiblesSoluciones;
	}

	/**
	 * Este método genera todas las posibles permutaciones de un item set.
	 * @param itemActual - El item set 
	 * @return una lista de listas de caracteres, donde cada sublista representa una permutación diferente.
	 */
	private List<List<Character>> generarPermutaciones(List<Character> itemActual) {
		if (itemActual.isEmpty()) {
			List<List<Character>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}

		Character primerElemento = itemActual.remove(0);
		List<List<Character>> resultado = new ArrayList<>();
		List<List<Character>> permutaciones = generarPermutaciones(itemActual);
		for (List<Character> per : permutaciones) {
			for (int index = 0; index <= per.size(); index++) {
				List<Character> temp = new ArrayList<>(per);
				temp.add(index, primerElemento);
				resultado.add(temp);
			}
		}
		return resultado;
	}

	/**
	 * Metodo que simplifica las permutaciones generadas anteriormente
	 * @param itemActualPermutaciones - La lista de permutaciones de un item set a simplificar
	 * @param conjuntoItemSets - El conjunto de item sets
	 * @return la lista de permutaciones simplificada de un item set
	 */
	private List<List<Character>> simplificarPermutaciones (List<List<Character>> itemActualPermutaciones, List<List<Character>> conjuntoItemSets) {
		int cont = 1;
		List<List<Character>> permutacionesSimplificadas = new ArrayList<List<Character>>();
		for (List<Character> itemActual: itemActualPermutaciones) {
			for (Character letra : itemActual) {
				if (cont==conjuntoItemSets.get(0).size()) {
					itemActual.remove(letra);
					Collections.sort(itemActual);
					permutacionesSimplificadas.add(itemActual);
				}
				cont++;
			}
			cont=1;
		}
		return permutacionesSimplificadas;
	}

	/**
	 * Metodo que establece una puntuacion a una opcion.
	 * @param numRespuestas - El numero de respuestas 
	 * @return la puntuacion
	 */
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}

	/**
	 * Método que genera un índice aleatorio dentro de un rango especificado.
	 * @param maxIndex - El limite superior del rango de indices a generar
	 * @return el indice generado aleatoriamente dentro del rango especificado
	 */
	private int generarIndex(int maxIndex) {
		int index = (int)(Math.random() * maxIndex);
		return index;
	}

	/**
	 * Método que genera una lista de opciones que incluyen respuestas verdaderas y falsas.
	 * @param numRespuestasVerdaderas - Número de respuestas verdaderas que se deben incluir en la lista de opciones
	 * @param numRespuestasFalsas - Número de respuestas falsas que se deben incluir en la lista de opciones
	 * @param solucionesVerdaderas - Lista de soluciones verdaderas
	 * @param solucionesFalsas - Lista de soluciones falsas
	 * @return una lista de listas de strings que incluyen opciones verdaderas y falsas
	 */
	public List<List<String>> generarOpciones(int numRespuestasVerdaderas, int numRespuestasFalsas, List<List<Character>> solucionesVerdaderas, List<List<Character>> solucionesFalsas) {

		List<List<String>> resultado = new ArrayList<>();
		List<String> opcionesVerdaderas = new ArrayList<String>();
		List<String> opcionesFalsas = new ArrayList<String>();

		for (int i=0; i<numRespuestasVerdaderas; i++) {
			String solucionAñadir = solucionesVerdaderas.get(generarIndex(solucionesVerdaderas.size())).toString();
			if (!opcionesVerdaderas.contains(solucionAñadir)) {
				opcionesVerdaderas.add(solucionAñadir);
			} else {
				i--;
			}
		}

		for (int i=0; i<numRespuestasFalsas; i++) {
			String solucionAñadir = solucionesFalsas.get(generarIndex(solucionesFalsas.size())).toString();
			if (!opcionesFalsas.contains(solucionAñadir)) {
				opcionesFalsas.add(solucionAñadir);
			} else {
				i--;
			}
		}

		resultado.add(opcionesVerdaderas);
		resultado.add(opcionesFalsas);

		return resultado;
	}

	/**
	 * Método que determina si hay suficientes soluciones verdaderas y falsas para
	 * generar un número determinado de opciones verdaderas y falsas.
	 * @param numRespuestasVerdaderas - Número de opciones verdaderas requeridas
	 * @param numRespuestasFalsas - Número de opciones falsas requeridas
	 * @param solucionesVerdaderas - Lista de soluciones verdaderas
	 * @param solucionesFalsas - Lista de soluciones falsas
	 * @return True si hay suficientes soluciones verdaderas y falsas, False en caso contrario
	 */
	private boolean tieneSolucion(int numRespuestasVerdaderas, int numRespuestasFalsas, List<List<Character>> solucionesVerdaderas, List<List<Character>> solucionesFalsas) {

		Set<String> solucionesVerdaderasSet = new HashSet<>();
		Set<String> solucionesFalsasSet = new HashSet<>();

		for(List<Character> itemSet : solucionesVerdaderas) {
			solucionesVerdaderasSet.add(itemSet.toString());
		}

		for(List<Character> itemSet : solucionesFalsas) {
			solucionesFalsasSet.add(itemSet.toString());
		}

		if (solucionesVerdaderas.size() >= numRespuestasVerdaderas
				&& solucionesFalsas.size() >= numRespuestasFalsas) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Devuelve el conjunto de item sets
	 * @return el conjunto de item sets
	 */
	public GeneradorConjuntoItemSets getGcd() {
		return gcd;
	}

	/**
	 * Establece el conjunto de item sets
	 * @param gcd - El conjunto de item sets
	 */
	public void setGcd(GeneradorConjuntoItemSets gcd) {
		this.gcd = gcd;
	}
}

