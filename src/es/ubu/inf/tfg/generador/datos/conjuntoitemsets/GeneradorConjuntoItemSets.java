package es.ubu.inf.tfg.generador.datos.conjuntoitemsets;

import java.util.Random;

import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.ConfigAmpliacionItemSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorConjuntoItemSets {

	private int numItemSets;
	private int tamItemSets;
	
	private static final int minNumItemSets = 10;
	private static final int maxNumItemSets = 16;
	private static final int minTamItemSets = 3;
	private static final int maxTamItemSets = 4;
	
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
	
	private char letraAleatoria() {
		try {
			Random random = new Random();
	        char letraAleatoria = (char) (random.nextInt(7) + 'A');
	        return letraAleatoria;
		} catch (Exception e) {
			System.out.println("Error al generar letra aleatoria: " + e.getMessage());
			return ' ';
		}	
	}
	
	private void ordenarTotalConjuntos(List<List<Character>> conjuntoItemSets) {
		try {
			conjuntoItemSets.sort((x, y) -> {
	            for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
	                if (x.get(i) != y.get(i)) {
	                    return x.get(i) - y.get(i);
	                }
	            }
	            return x.size() - y.size();
	        });
		} catch (Exception e) {
			System.out.println("Error al ordenar conjunto total: " + e.getMessage());
		}
	}
}

	


