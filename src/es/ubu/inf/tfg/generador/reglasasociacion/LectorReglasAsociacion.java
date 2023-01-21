package es.ubu.inf.tfg.generador.reglasasociacion;

import java.util.Scanner;

public class LectorReglasAsociacion {

	Scanner leer = new Scanner(System.in);

	public ConfigReglasAsociacion leerConfig() {
//		int numIntervalos = 0;
		
//		System.out.print("¿Quieres que haya atributos discretos? Escribe (s) o (n)");
//		String atrDiscretos = leer.next();
		
	    System.out.print("Ingrese el número de atributos:");
	    int numAtributos = leer.nextInt();
	    
	    System.out.print("Ingrese el número de instancias aleatorias:");
	    int numInstancias = leer.nextInt();
	    
//	    System.out.print("Ingrese el valor del soporte minimo:");
//	    double soporte = leer.nextDouble();
	    
//	    System.out.print("Ingrese el valor de la confianza minima:");
//	    double confianza = leer.nextDouble();
	    
//	    if (atrDiscretos.equals("s")) {
//	    	System.out.println("Establece el numero de intervalos en los que sera discretizado el atributo");
//	    	numIntervalos = leer.nextInt();
//		}
	    
//	    return new ConfigReglasAsociacion(soporte, confianza, numIntervalos, numAtributos, numInstancias, atrDiscretos);
	    return new ConfigReglasAsociacion(numAtributos, numInstancias);
	}
}
