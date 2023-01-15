package preguntas.reglasAsociacion;
//import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.Filter;

public class PreguntaReglasAsociacion {
	
	private ArrayList<Attribute> atributos;
	private List<String> etiquetas;
	private Instances datos;
	private Instances datosUso;
	private String nombreAtributo;
	
	public PreguntaReglasAsociacion() {
		
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
	      datos.add(instancia);
	    }
	}
	
	public void añadirDiscretizacion(int numIntervalos) {
		Attribute nuevoAtr = new Attribute("X");
		datos.insertAttributeAt(nuevoAtr, datos.numAttributes());
		
		Random random = new Random();
		int atrIndex = datos.attribute("X").index();

		for (int i = 0; i < datos.numInstances(); i++) {
			double randomValue = Math.round((0 + (100 - 0) * random.nextDouble())*10)/10.0;
		    datos.instance(i).setValue(atrIndex, randomValue);
		}
		
		Discretize discretizar = new Discretize();

		discretizar.setAttributeIndices("last");
		discretizar.setBins(numIntervalos);
		discretizar.setUseEqualFrequency(true);
		try {
			discretizar.setInputFormat(datos);
			datosUso = Filter.useFilter(datos, discretizar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Instances getDatosUso() {
		return datosUso;
	}

	public void setDatosDiscretizacion(Instances datosUso) {
		this.datosUso = datosUso;
	}
	
	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
}
 


