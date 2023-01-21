package es.ubu.inf.tfg.generador.posiblesitemsets;

public class ConfigPosiblesItemSets {

	private int numItemSets;
	private int tamItemSets;
	
	
	public ConfigPosiblesItemSets(int numItemSets, int tamItemSets) {
		this.numItemSets = numItemSets;
		this.tamItemSets = tamItemSets;
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
