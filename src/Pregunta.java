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
		
//		ordenarTotalConjuntos();
	}
	
	public void ordenarTotalConjuntos() {
//		int i, j, k;
//		List<Character> aux;
//        for (i=0; i<totalConjuntos.size()-1; i++) {
//            for (j = 0; j < totalConjuntos.size() - i - 1; j++) { 
//            	for (k=0; k < totalConjuntos.get(j).size(); k++) {
//                    if (totalConjuntos.get(j + 1).get(k) < totalConjuntos.get(j).get(k)) {
//                        aux = totalConjuntos.get(j + 1);
//                        totalConjuntos.set(j + 1, totalConjuntos.get(j));
//                        totalConjuntos.set(j, aux);
//                    }
//            	}
//            }
//        }
//		Collections.sort(totalConjuntos, new Comparator<List<Character>>() {
//            public int compare(List<Character> conjunto1, List<Character> conjunto2) {
//            	for (int i=0; i<getNumItemSets(); i++) {
//                    return conjunto1.get(i).compareTo(conjunto2.get(i));
//            	}
//            	return 0;
//            }
//
//        });
//		for (List<Character> conjuntosOrdenados : totalConjuntos) {
//            System.out.println(ArrayList.toString(conjuntosOrdenados));
//        }
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
