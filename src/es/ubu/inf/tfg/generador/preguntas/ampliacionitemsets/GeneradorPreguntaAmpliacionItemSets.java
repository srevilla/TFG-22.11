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

public class GeneradorPreguntaAmpliacionItemSets {

	private GeneradorConjuntoItemSets gcd;
	
	private static final int numOpciones = 4;
	private static final int maxPuntuacion = 100;
	
	public GeneradorPreguntaAmpliacionItemSets(ConfigAmpliacionItemSets config) {
		gcd = new GeneradorConjuntoItemSets(config);
	}
	
	public String obtenerEnunciado(ConjuntoItemSets conjuntoItemSets) {
		return "Se dispone de los siguientes " + conjuntoItemSets.getConjuntoItemSets().get(0).size() + "-item sets: " + conjuntoItemSets.getConjuntoItemSets().toString() + "\r\n" 
				+ "¿Cuales son los posibles " + (conjuntoItemSets.getConjuntoItemSets().get(0).size()+1) + "-item sets?";
	}
	
	public String obtenerTitulo() {
		return "Ampliación Item Sets";
	}
	
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
	
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}
	
	private int generarIndex(int maxIndex) {
        int index = (int)(Math.random() * maxIndex);
        return index;
	}
	
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
}