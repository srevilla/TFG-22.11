package ReglasAsociacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import weka.associations.AssociationRule;
import weka.core.Attribute;

public class Main_ReglasAsociacion {

	 public static void main(String[] args) throws IOException {
		   
			Scanner sc = new Scanner(System.in);
			boolean tieneSolucion = false;
			List<AssociationRule> reglasVerdaderas = new ArrayList<AssociationRule>();
			List<AssociationRule> reglasFalsas = new ArrayList<AssociationRule>();
			
			System.out.print("Ingrese el número de respuestas verdaderas (min:1, max:4) : ");
		    int respuestasVerdaderas = sc.nextInt();
		    int respuestasFalsas = 4 - respuestasVerdaderas;
			
		    System.out.print("Ingrese el número de atributos: ");
		    int numAtributos = sc.nextInt();
		    
		    System.out.print("Ingrese el número de instancias aleatorias: ");
		    int numInstancias = sc.nextInt();
		    
		    System.out.print("Ingrese el valor del soporte minimo: ");
		    double soporte = sc.nextDouble();
		    
		    System.out.print("Ingrese el valor de la confianza minima: ");
		    double confianza = sc.nextDouble();
		    
		    Pregunta p = new Pregunta();
		    int cont = 1;
		    
		    while(!tieneSolucion) {
		    	p.crearConjuntoDatos(numInstancias, numAtributos);
		    	
		    	Solucion s = new Solucion(p.getDatos());
		    	s.generarSoluciones(soporte, confianza);

		    	tieneSolucion = s.tieneSolucion(respuestasVerdaderas, respuestasFalsas);
		    	
		    	if(tieneSolucion) {
			    	reglasVerdaderas = s.getReglasVerdaderas();
			    	reglasFalsas = s.getReglasFalsas();
		    	}
		    	System.out.println(cont);
		    	cont++;
		    }
		  
	    	System.out.println("Fin");
	 }
}
