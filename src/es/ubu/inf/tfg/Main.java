package es.ubu.inf.tfg;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;
import es.ubu.inf.tfg.generador.posiblesitemsets.GeneradorBancoPreguntasPosiblesItemSets;
import es.ubu.inf.tfg.generador.reglasasociacion.GeneradorBancoPreguntasReglasAsociacion;
import es.ubu.inf.tfg.traductor.Traductor;
import es.ubu.inf.tfg.traductor.xml.TraductorXML;

public class Main {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
		
		GeneradorBancoPreguntas generador = null;
		Traductor traductor = null;
		
		int tipoPregunta = 0;
        int numPreguntas = 0;
        
    	String ruta;
		String nombreFichero;
        
		final int minNumPreguntas = 1;
		final int maxNumPreguntas = 100;

		System.out.println("**************CONFIGURACION**************");

		while (true) {
			try {
				System.out.println("Escribe el numero correspondiente a la pregunta que quieres generar:");
				System.out.println("1 - Posibles Item Sets");
				System.out.println("2 - Reglas Asociacion"); 
				
				tipoPregunta = leer.nextInt();
				if (tipoPregunta == 1) {
					generador = new GeneradorBancoPreguntasPosiblesItemSets();
				} else if (tipoPregunta == 2) {
					generador = new GeneradorBancoPreguntasReglasAsociacion();
				} else {
					System.out.println("Ingresa un numero valido (1 o 2)");
				}
				
                break;
                
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
		            System.out.println("Ingresa un numero valido (entre " + minNumPreguntas + " y " + maxNumPreguntas + ")");
		        }
		    } catch (InputMismatchException e) {
		    	System.out.println("Ingresa un numero valido (entre " + minNumPreguntas + " y " + maxNumPreguntas + ")");
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
		
		traductor = new TraductorXML(ruta);
		traductor.traducir(generador.generarBancoPreguntas(numPreguntas));
		
		leer.close();
		
		System.out.println("Finalizado");	
		
	}

}
