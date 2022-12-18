package PosiblesItemSets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solucion {
	
	private List<List<Character>> totalConjuntos;
	private List<List<Character>> posiblesSoluciones;
	private List<List<Character>> solucionesVerdaderas;
	private List<List<Character>> solucionesFalsas;
	private List<List<Character>> opciones;

	public Solucion(List<List<Character>> totalConjuntos) {
		this.totalConjuntos = totalConjuntos;
	}
	
	private void generarPosiblesSoluciones() {
		posiblesSoluciones = new ArrayList<List<Character>>();
		int k = 0;
		List<Character> itemActual = new ArrayList<Character>();
		
		for (int i=0; i<totalConjuntos.size(); i++) {
			while(k < totalConjuntos.size()-1) {
				for (int j=0; j<totalConjuntos.get(i).size(); j++) {
					for (Character letra : totalConjuntos.get(i)) {
						itemActual.add(letra);
					}
					if(!itemActual.contains(totalConjuntos.get(k).get(j))) {
						itemActual.add(totalConjuntos.get(k).get(j));
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
}

	private <E> List<List<E>> generarPermutaciones(List<E> original) {
		  if (original.isEmpty()) {
		    List<List<E>> result = new ArrayList<>();
		    result.add(new ArrayList<>());
		    return result;
		  }
		  E firstElement = original.remove(0);
		  List<List<E>> returnValue = new ArrayList<>();
		  List<List<E>> permutations = generarPermutaciones(original);
		  for (List<E> smallerPermutated : permutations) {
		    for (int index = 0; index <= smallerPermutated.size(); index++) {
		      List<E> temp = new ArrayList<>(smallerPermutated);
		      temp.add(index, firstElement);
		      returnValue.add(temp);
		    }
		  }
		  return returnValue;
		}
	
	private List<List<Character>> simplificarPermutaciones (List<List<Character>> itemActualPermutaciones) {
		int cont = 1;
		List<List<Character>> permutacionesSimplificadas = new ArrayList<List<Character>>();
		for (List<Character> itemActual: itemActualPermutaciones) {
			for (Character letra : itemActual) {
				if (cont==totalConjuntos.get(0).size()) {
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

	
	public void generarSoluciones() {
		generarPosiblesSoluciones();
		int cont;
		solucionesVerdaderas = new ArrayList<List<Character>>();
		solucionesFalsas = new ArrayList<List<Character>>();
		List<List<Character>> conjuntoPermutacionesItemActual = new ArrayList<List<Character>>();
		for (List<Character> itemActualPosibleSolucion : posiblesSoluciones) {
			cont = 0;
			List<Character> itemPosibleSolucion = new ArrayList<Character>(itemActualPosibleSolucion);
			conjuntoPermutacionesItemActual = generarPermutaciones(itemActualPosibleSolucion);
			conjuntoPermutacionesItemActual = simplificarPermutaciones(conjuntoPermutacionesItemActual);
			conjuntoPermutacionesItemActual = conjuntoPermutacionesItemActual.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
			
			for (List<Character> itemActualPermutaciones : conjuntoPermutacionesItemActual) {
				String itemActualPermutacionesString = itemActualPermutaciones.toString();
				itemActualPermutacionesString = itemActualPermutacionesString.replaceAll("[^A-Z,\s]", "");
				if (totalConjuntos.toString().contains(itemActualPermutacionesString)) {
					cont++;
				}
			}
			if (cont == conjuntoPermutacionesItemActual.size()) {
				solucionesVerdaderas.add(itemPosibleSolucion);
			} else {
				solucionesFalsas.add(itemPosibleSolucion);
			}
		}
	}
	
	private int generarIndex(int maxIndex) {
        int index = (int)(Math.random() * maxIndex);
        return index;
	}
	
	public void generarOpciones(int numRespuestasFalsas, int numRespuestasVerdaderas) {
		opciones = new ArrayList<List<Character>>();
		
		for (int i=0; i<numRespuestasFalsas; i++) {
			List<Character> solucionAñadir = getSolucionesFalsas().get(generarIndex(getSolucionesFalsas().size()));
			if (!opciones.contains(solucionAñadir)) {
				opciones.add(solucionAñadir);
			} else {
				i--;
			}
		}
		
		for (int i=0; i<numRespuestasVerdaderas; i++) {
			List<Character> solucionAñadir = getSolucionesVerdaderas().get(generarIndex(getSolucionesVerdaderas().size()));
			if (!opciones.contains(solucionAñadir)) {
				opciones.add(solucionAñadir);
			} else {
				i--;
			}
		}
	}
//	public void imprimirOpciones(int numRespuestasFalsas, int numRespuestasVerdaderas) {
//		generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
//		for (int i=0; i<4; i++) {
//			switch(i) {
//			case 0: 
//				System.out.println("a) " + getOpciones().remove(generarIndex(4-i)));
//				break;
//			case 1: 
//				System.out.println("b) " + getOpciones().remove(generarIndex(4-i)));
//				break;
//			case 2: 
//				System.out.println("c) " + getOpciones().remove(generarIndex(4-i)));
//				break;
//			case 3: 
//				System.out.println("d) " + getOpciones().remove(generarIndex(4-i)));
//				break;
//			}
//		}
//	}
	
	public boolean tieneSolucion(int numRespuestasVerdaderas) {
		if (getSolucionesVerdaderas().size()>=numRespuestasVerdaderas) {
			return true;
		} else {
			return false;
		}
	}

	public List<List<Character>> getTotalConjuntos() {
		return totalConjuntos;
	}

	public void setTotalConjuntos(List<List<Character>> totalConjuntos) {
		this.totalConjuntos = totalConjuntos;
	}

	public List<List<Character>> getPosiblesSoluciones() {
		return posiblesSoluciones;
	}

	public void setPosiblesSoluciones(List<List<Character>> posiblesSoluciones) {
		this.posiblesSoluciones = posiblesSoluciones;
	}

	public List<List<Character>> getSolucionesVerdaderas() {
		return solucionesVerdaderas;
	}

	public void setSolucionesVerdaderas(List<List<Character>> solucionesVerdaderas) {
		this.solucionesVerdaderas = solucionesVerdaderas;
	}

	public List<List<Character>> getSolucionesFalsas() {
		return solucionesFalsas;
	}

	public void setSolucionesFalsas(List<List<Character>> solucionesFalsas) {
		this.solucionesFalsas = solucionesFalsas;
	}
	
	public List<List<Character>> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<List<Character>> opciones) {
		this.opciones = opciones;
	}
}
