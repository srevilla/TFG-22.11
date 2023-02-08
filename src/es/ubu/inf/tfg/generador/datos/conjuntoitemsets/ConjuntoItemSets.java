package es.ubu.inf.tfg.generador.datos.conjuntoitemsets;

import java.util.List;

public class ConjuntoItemSets {

	private List<List<Character>> conjuntoItemSets;
	
	public ConjuntoItemSets(List<List<Character>> conjuntoItemSets) {
		this.conjuntoItemSets = conjuntoItemSets;
	}

	public List<List<Character>> getConjuntoItemSets() {
		return conjuntoItemSets;
	}

	public void setConjuntoItemSets(List<List<Character>> conjuntoItemSets) {
		this.conjuntoItemSets = conjuntoItemSets;
	}
	
}
