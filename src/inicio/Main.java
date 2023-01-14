package inicio;
import java.util.Scanner;

import preguntas.posiblesItemSets.*;
import preguntas.reglasAsociacion.*;
import traductor.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
		
		final int MaxTamItemSets = 4;
		final int MinTamItemSets = 3;
		
		int tipoPregunta;
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
		List<SolucionPosiblesItemSets> totalProblemas = new ArrayList<SolucionPosiblesItemSets>();
		List<SolucionReglasAsociacion> totalProblemasReglasAsociacion = new ArrayList<SolucionReglasAsociacion>();

		System.out.println("**************CONFIGURACION**************");
		System.out.println("Escribe el numero correspondiente a la pregunta que quieres generar: /n");
		System.out.println("1 - Posibles Item Sets /n");
		System.out.println("2 - Reglas Asociacion /n");
		tipoPregunta = leer.nextInt();
		
		System.out.println("Elige cantidad de preguntas a generar");
		numPreguntas = leer.nextInt();
		System.out.println("Elige cantidad de opciones de respuesta verdaderas (Min:1, Max: 4)");
		numRespuestasVerdaderas = leer.nextInt();
		numRespuestasFalsas = 4 - numRespuestasVerdaderas;
		
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
		
		if(tipoPregunta == 1) {
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

			PreguntaPosiblesItemSets p = new PreguntaPosiblesItemSets(numItemSets, tamItemSets);
			
			while(!tieneSolucion || contador<numPreguntas) {
				p.generarTotalConjuntos();
				
				List<List<Character>> totalConjuntos = p.getTotalConjuntos();
				SolucionPosiblesItemSets s = new SolucionPosiblesItemSets(totalConjuntos);
				s.generarSoluciones();
				tieneSolucion = s.tieneSolucion(numRespuestasVerdaderas);
				if(tieneSolucion) {
					s.generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
					
					contador++;
					totalProblemas.add(s);
				}
			}
			
			TraductorPosiblesItemSets t = new TraductorPosiblesItemSets();
			t.generarPlantillas(totalProblemas);

			try {
				t.exportarXML(ruta);
					
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		    System.out.print("Ingrese el número de atributos: ");
		    int numAtributos = leer.nextInt();
		    
		    System.out.print("Ingrese el número de instancias aleatorias: ");
		    int numInstancias = leer.nextInt();
		    
		    System.out.print("Ingrese el valor del soporte minimo: ");
		    double soporte = leer.nextDouble();
		    
		    System.out.print("Ingrese el valor de la confianza minima: ");
		    double confianza = leer.nextDouble();
		    
		    PreguntaReglasAsociacion p = new PreguntaReglasAsociacion();
//		    int cont = 1;
		    
		    while(!tieneSolucion || contador<numPreguntas) {
		    	p.crearConjuntoDatos(numInstancias, numAtributos);
		    	
		    	SolucionReglasAsociacion s = new SolucionReglasAsociacion(p.getDatos());
		    	s.generarSoluciones(soporte, confianza);

		    	tieneSolucion = s.tieneSolucion(numRespuestasVerdaderas, numRespuestasFalsas);
		    	
		    	if(tieneSolucion) {
		    		s.generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
					totalProblemasReglasAsociacion.add(s);
					contador++;
		    	}		    	
		    }
		    
			TraductorReglasAsociacion t = new TraductorReglasAsociacion();
			t.generarPlantillas(totalProblemasReglasAsociacion);

			try {
				t.exportarXML(ruta);
					
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println("Finalizado");
	}
}
