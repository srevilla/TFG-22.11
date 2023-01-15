package inicio;
import java.util.Scanner;

import preguntas.posiblesItemSets.*;
import preguntas.reglasAsociacion.*;
import traductor.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
		
		final int MaxTamItemSets = 4;
		final int MinTamItemSets = 3;
		final int minNumPreguntas = 1;
		final int maxNumPreguntas = 100;
		final int minNumRespuestasVerdaderas = 1;
		final int maxNumRespuestasVerdaderas = 4;
		
		int tipoPregunta = 1;
		int contador = 0;
		int tamItemSets = 0;
		int numItemSets;
		int numPreguntas = 1;
		int numRespuestasFalsas = 2;
		int numRespuestasVerdaderas = 2;
		int numIntervalos = 0;
		boolean tieneSolucion = false;
		String ruta;
		String nombreFichero;
		List<SolucionPosiblesItemSets> totalProblemas = new ArrayList<SolucionPosiblesItemSets>();
		List<SolucionReglasAsociacion> totalProblemasReglasAsociacion = new ArrayList<SolucionReglasAsociacion>();

		System.out.println("**************CONFIGURACION**************");
		
		while (true) {
		    try {
		        System.out.println("Escribe el numero correspondiente a la pregunta que quieres generar:");
		        System.out.println("1 - Posibles Item Sets");
		        System.out.println("2 - Reglas Asociacion"); 
		        tipoPregunta = leer.nextInt();
		        if (tipoPregunta == 1 || tipoPregunta == 2) {
		            break;
		        } else {
		            System.out.println("Ingresa un numero valido (1 o 2)");
		        }
		    } catch (InputMismatchException e) {
		        System.out.println("Ingresa un numero valido (1 o 2)");
		        leer.next();
		    }
		}
		
		while (true) {
		    try {
		        System.out.println("Elige cantidad de preguntas a generar (Min: " + minNumPreguntas + ", Max: " + maxNumPreguntas + ")");
		        numPreguntas = leer.nextInt();
		        if (numPreguntas >= minNumPreguntas && numPreguntas <= maxNumPreguntas) {
		            break;
		        } else {
		            System.out.println("Ingresa un numero valido (entre " + minNumPreguntas + " y " + maxNumPreguntas);
		        }
		    } catch (InputMismatchException e) {
		    	System.out.println("Ingresa un numero valido (entre " + minNumPreguntas + " y " + maxNumPreguntas);
		        leer.next();
		    }
		}
		
		while (true) {
			try {
				System.out.println("Elige cantidad de opciones de respuesta verdaderas (Min:1, Max: 4)");
				numRespuestasVerdaderas = leer.nextInt();
				numRespuestasFalsas = 4 - numRespuestasVerdaderas;
				if (numRespuestasVerdaderas >= 1 && numRespuestasVerdaderas <= 4) {
					break;
				} else {
		            System.out.println("Ingresa un numero valido (entre " + minNumRespuestasVerdaderas + " y " + maxNumRespuestasVerdaderas);
				}
			} catch (InputMismatchException e) {
		    	System.out.println("Ingresa un numero valido (entre " + minNumRespuestasVerdaderas + " y " + maxNumRespuestasVerdaderas);
		    	leer.next();
			}
		}
		
		while(true) {
		    System.out.println("Especifica la ruta donde van a ser creados los archivos:");
		    ruta = leer.next();
		    System.out.println("Especifica el nombre del fichero");
		    nombreFichero = leer.next();
		    ruta = ruta + "\\" + nombreFichero + ".xml";

		    File file = new File(ruta);
		    if (!file.exists()) {
		        try {
		            file.createNewFile();
		            break;
		        } catch (IOException e) {
		            System.out.println("Error al crear el archivo, comprueba si la ruta es correcta y si tienes permisos de escritura");
		        }
		    } else {
		        System.out.println("El archivo ya existe en la ruta especificada");
		        break;
		    }
		}
		
		if(tipoPregunta == 1) {
			
			while (true) {
			    try {
			        System.out.println("Elige cantidad de item sets:");
			        numItemSets = leer.nextInt();
			        if (numItemSets > 0) {
			            break;
			        } else {
			            System.out.println("El valor introducido no es válido, ingresa un número mayor a 0");
			        }
			    } catch (InputMismatchException e) {
			        System.out.println("El valor introducido no es válido, ingresa un número entero");
			        leer.next();
			    }
			}

			while (true) {
			    try {
			        System.out.println("Elige tamaño de los item sets (Min: 3, Max: 4):");
			        tamItemSets = leer.nextInt();
			        if (tamItemSets >= 3 && tamItemSets <= 4) {
			            break;
			        } else {
			            System.out.println("El valor introducido no es válido, ingresa un número entero entre 3 y 4");
			        }
			    } catch (InputMismatchException e) {
			        System.out.println("El valor introducido no es válido, ingresa un número entero entre 3 y 4");
			        leer.next();
			    }
			}

			while(!tieneSolucion || contador<numPreguntas) {
			    PreguntaPosiblesItemSets p = new PreguntaPosiblesItemSets(numItemSets, tamItemSets);
			    p.generarTotalConjuntos();
			    List<List<Character>> totalConjuntos = p.getTotalConjuntos();
			    SolucionPosiblesItemSets s = new SolucionPosiblesItemSets(totalConjuntos);
			    try {
			        s.generarSoluciones();
			        tieneSolucion = s.tieneSolucion(numRespuestasVerdaderas);
			        if(tieneSolucion) {
			            s.generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
			            contador++;
			            totalProblemas.add(s);
			        }
			    } catch (Exception e) {
			        System.out.println("Error al generar solución: " + e.getMessage());
			    }
			}
			
			try {
			    TraductorPosiblesItemSets t = new TraductorPosiblesItemSets();
			    t.generarPlantillas(totalProblemas);
				try {
					t.exportarXML(ruta);					
				} catch (Exception e) {
				// TODO Auto-generated catch block
				    System.out.println("Error al exportar XML: " + e.getMessage());
				}
			} catch (Exception e) {
			    System.out.println("Error al generar plantillas: " + e.getMessage());
			}

		} else if (tipoPregunta == 2) {
			
			System.out.print("¿Quieres que haya atributos discretos? Escribe (s) o (n)");
			String atrDiscretos = leer.next();
			
		    System.out.print("Ingrese el número de atributos:");
		    int numAtributos = leer.nextInt();
		    
		    System.out.print("Ingrese el número de instancias aleatorias:");
		    int numInstancias = leer.nextInt();
		    
		    System.out.print("Ingrese el valor del soporte minimo:");
		    double soporte = leer.nextDouble();
		    
		    System.out.print("Ingrese el valor de la confianza minima:");
		    double confianza = leer.nextDouble();
		    
		    if (atrDiscretos.equals("s")) {
		    	System.out.println("Establece el numero de intervalos en los que sera discretizado el atributo");
		    	numIntervalos = leer.nextInt();
			}
		    
		    PreguntaReglasAsociacion p = new PreguntaReglasAsociacion();
		    
		    while(!tieneSolucion || contador<numPreguntas) {
		    	p.crearConjuntoDatos(numInstancias, numAtributos);
		    	
		    	if (atrDiscretos.equals("s")) {
		    		p.añadirDiscretizacion(numIntervalos);
		    	}
		    	
		    	SolucionReglasAsociacion s = new SolucionReglasAsociacion(p.getDatos(), p.getDatosUso());
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
