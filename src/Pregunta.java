import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Pregunta {

	private int numItemSets;
	private int tamItemSets;
	private List<Character> conjunto;
	private List<List<Character>> totalConjuntos;

	public Pregunta(int numItemSets, int tamItemSets) {
		this.numItemSets = numItemSets;
		this.tamItemSets = tamItemSets;
	}
	
	public char letraAleatoria() {
		Random random = new Random();
        char letraAleatoria = (char) (random.nextInt(7) + 'A');
        return letraAleatoria;
	}
	
	public void generarItemSets() {
		conjunto = new ArrayList<Character>();
		char letraNueva;
		while(conjunto.size() < getTamItemSets()) {
			letraNueva = letraAleatoria();
			if(!conjunto.contains(letraNueva)) {
				conjunto.add(letraNueva);
			}
		}

		Collections.sort(conjunto);
	}
	
	public void generarTotalConjuntos() {
		
		totalConjuntos = new ArrayList<List<Character>>();
		List<Character> conjuntoNuevo = new ArrayList<Character>();
		
		while(totalConjuntos.size() < getNumItemSets()) {
			generarItemSets();
			conjuntoNuevo = getConjunto();
			if(!totalConjuntos.contains(conjuntoNuevo)) {
				totalConjuntos.add(conjuntoNuevo);
			}
		}		
		ordenarTotalConjuntos();
	}
	
	public void ordenarTotalConjuntos() {
        totalConjuntos.sort((x, y) -> {
            for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
                if (x.get(i) != y.get(i)) {
                    return x.get(i) - y.get(i);
                }
            }
            return x.size() - y.size();
        });
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
	
	public List<Character> getConjunto(){
		return conjunto;
	}
	
	public List<List<Character>> getTotalConjuntos() {
		return totalConjuntos;
	}

	public void setTotalConjuntos(List<List<Character>> totalConjuntos) {
		this.totalConjuntos = totalConjuntos;
	}

}
