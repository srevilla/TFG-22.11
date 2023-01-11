package ReglasAsociacion;
//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
//import weka.core.converters.ArffSaver;

public class Pregunta {
	
	private ArrayList<Attribute> atributos;
	private List<String> etiquetas;
	private Instances datos;
	private String nombreAtributo;
	
	public Pregunta() {
		
	}
	
	private void crearAtributos(int numAtributos) {
		atributos = new ArrayList<>();
		for (int i = 0; i < numAtributos; i++) {
			
	        nombreAtributo = Character.toString((char) ('A' + i));
	
	        etiquetas = new ArrayList<>();
	        etiquetas.add("T");
	        etiquetas.add("F");
	        atributos.add(new Attribute(nombreAtributo, etiquetas));
	      }
	}
	
	public void crearConjuntoDatos(int numInstancias, int numAtributos) {
		crearAtributos(numAtributos);
	    datos = new Instances("datos", atributos, 0);

	    Random rand = new Random();

	    for (int i = 0; i < numInstancias; i++) {

	      Instance instancia = new DenseInstance(numAtributos);
	      instancia.setDataset(datos); 
	      
	      for (int j = 0; j < numAtributos; j++) {
	    	  instancia.setValue(j, (rand.nextInt(2) == 0) ? "T" : "F");
	      }
	      // Añade la instancia al conjunto de datos
	      datos.add(instancia);
	    }
	}

	public ArrayList<Attribute> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<Attribute> atributos) {
		this.atributos = atributos;
	}

	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public Instances getDatos() {
		return datos;
	}

	public void setDatos(Instances datos) {
		this.datos = datos;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
}
 


