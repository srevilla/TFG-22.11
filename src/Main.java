import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
		
		final int MaxTamItemSets = 4;
		final int MinTamItemSets = 3;
		
		int tamItemSets = 0;
		boolean repetir = true;

		System.out.println("**************CONFIGURACION**************");
		System.out.println("Elige cantidad de item sets:");
		final int numItemSets = leer.nextInt();
		while(repetir) {
			System.out.println("Elige tamaño de los item sets:");
			tamItemSets = leer.nextInt();
			if(tamItemSets < MinTamItemSets || tamItemSets > MaxTamItemSets) {
				System.out.println("El tamaño del item set tiene que ser 3 o 4");
			} else {
				repetir = false;
			}
		}		
		System.out.println("Hay " + numItemSets + " item sets de tamaño " + tamItemSets);
		Pregunta p = new Pregunta(numItemSets, tamItemSets);
		System.out.println("Se dispone de los siguientes " + tamItemSets + "-item sets:");

		boolean tieneSolucion = false;
		while(!tieneSolucion) {
			p.generarTotalConjuntos();
			
			List<List<Character>> totalConjuntos = p.getTotalConjuntos();
			Solucion s = new Solucion(totalConjuntos);
			s.generarSoluciones();
			tieneSolucion = s.tieneSolucion();
			if(tieneSolucion) {
				for (int i=0; i<p.getTotalConjuntos().size(); i++) {
					System.out.print(p.getTotalConjuntos().get(i));
				}
				System.out.println("Cuales de las siguientes opciones son correctas?");
				s.imprimirOpciones();
			}
		}
		
//		List<List<Character>> totalConjuntos = new ArrayList<List<Character>>();
//		
//		List<Character> fila1 = new ArrayList<Character>();
//		List<Character> fila2 = new ArrayList<Character>();
//		List<Character> fila3 = new ArrayList<Character>();
//		List<Character> fila4 = new ArrayList<Character>();
//		List<Character> fila5 = new ArrayList<Character>();
//		List<Character> fila6 = new ArrayList<Character>();
//		List<Character> fila7 = new ArrayList<Character>();
//		List<Character> fila8 = new ArrayList<Character>();
//		List<Character> fila9 = new ArrayList<Character>();
//		
//		fila1.add('A'); 
//		fila1.add('B'); 
//		fila1.add('D');
//		
//		fila2.add('A'); 
//		fila2.add('B'); 
//		fila2.add('F');
//		
//		fila3.add('A'); 
//		fila3.add('D'); 
//		fila3.add('F');
//		
//		fila4.add('B'); 
//		fila4.add('C'); 
//		fila4.add('E');
//		
//		fila5.add('B'); 
//		fila5.add('C'); 
//		fila5.add('G');
//		
//		fila6.add('B'); 
//		fila6.add('D'); 
//		fila6.add('F');
//		
//		fila7.add('B'); 
//		fila7.add('E'); 
//		fila7.add('G');
//		
//		fila8.add('C'); 
//		fila8.add('E'); 
//		fila8.add('G');
//		
//		fila9.add('D'); 
//		fila9.add('F'); 
//		fila9.add('G');
//		
//		totalConjuntos.add(fila1);
//		totalConjuntos.add(fila2);
//		totalConjuntos.add(fila3);
//		totalConjuntos.add(fila4);
//		totalConjuntos.add(fila5);
//		totalConjuntos.add(fila6);
//		totalConjuntos.add(fila7);
//		totalConjuntos.add(fila8);
//		totalConjuntos.add(fila9);
		
//		for (int i=0; i<p.getNumItemSets(); i++) {
//			System.out.print(totalConjuntos.get(i));
//		}

//		System.out.println("¿Cuales son los posibles 4-item sets?");
//		int solucion = leer.nextInt();
	}

}
