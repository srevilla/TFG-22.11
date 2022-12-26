package PosiblesItemSets;
import java.util.Scanner;

import Traductor.Traductor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
		
		final int MaxTamItemSets = 4;
		final int MinTamItemSets = 3;
		
		int contador = 0;
		int tamItemSets = 0;
		int numItemSets;
		int numPreguntas = 1;
		int numRespuestasFalsas = 2;
		int numRespuestasVerdaderas = 2;
		boolean repetir = true;
		boolean tieneSolucion = false;
		String ruta;
		String nombreFichero;
		List<Solucion> totalProblemas = new ArrayList<Solucion>();

		System.out.println("**************CONFIGURACION**************");
		System.out.println("Elige cantidad de preguntas a generar");
		numPreguntas = leer.nextInt();
		System.out.println("Elige cantidad de opciones de respuesta verdaderas (Min:1, Max: 4)");
		numRespuestasVerdaderas = leer.nextInt();
		numRespuestasFalsas = 4 - numRespuestasVerdaderas;
		System.out.println("Elige cantidad de item sets:");
		numItemSets = leer.nextInt();
		while(repetir) {
			System.out.println("Elige tamaño de los item sets (Min: 3, Max: 4):");
			tamItemSets = leer.nextInt();
			if(tamItemSets < MinTamItemSets || tamItemSets > MaxTamItemSets) {
				System.out.println("El tamaño del item set tiene que ser 3 o 4");
			} else {
				repetir = false;
			}
		}		

		Pregunta p = new Pregunta(numItemSets, tamItemSets);
		
		System.out.println("Especifica la ruta donde van a ser creados los archivos:");
		ruta = leer.next();
		System.out.println("Especifica el nombre del fichero");
		nombreFichero = leer.next();
		ruta = ruta + "\\" + nombreFichero + ".xml";
		
		File file = new File(ruta);
        if (!file.exists()) {
            try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

		while(!tieneSolucion || contador<numPreguntas) {
			p.generarTotalConjuntos();
			
			List<List<Character>> totalConjuntos = p.getTotalConjuntos();
			Solucion s = new Solucion(totalConjuntos);
			s.generarSoluciones();
			tieneSolucion = s.tieneSolucion(numRespuestasVerdaderas);
			if(tieneSolucion) {
				s.generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
				
				contador++;
				totalProblemas.add(s);
			}
		}
		
		Traductor t = new Traductor();
		t.generarPlantillas(totalProblemas);

		try {
			t.exportarXML(ruta);
				
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		}
		System.out.println("Finalizado");
		
		
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
