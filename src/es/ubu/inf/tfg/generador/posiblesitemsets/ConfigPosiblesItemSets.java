package es.ubu.inf.tfg.generador.posiblesitemsets;

public class ConfigPosiblesItemSets {

	private int numPreguntas;
	private int numItemSets;
	private int tamItemSets;
	
	public ConfigPosiblesItemSets(int numPreguntas, int numItemSets, int tamItemSets) {
		this.numPreguntas = numPreguntas;
		this.numItemSets = numItemSets;
		this.tamItemSets = tamItemSets;
	}
	
	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}

	public int getNumItemSets() {
		return numItemSets;
	}

	public void setNumItemSets(int numItemSets) {
		this.numItemSets = numItemSets;
	}

	public int getTamItemSets() {
		return tamItemSets;
	}

	public void setTamItemSets(int tamItemSets) {
		this.tamItemSets = tamItemSets;
	}
	
	
}
