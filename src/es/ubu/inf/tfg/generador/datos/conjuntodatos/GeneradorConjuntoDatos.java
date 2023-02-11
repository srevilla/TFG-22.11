package es.ubu.inf.tfg.generador.datos.conjuntodatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ubu.inf.tfg.dominio.UnexpectedException;
import es.ubu.inf.tfg.generador.preguntas.reglasasociacion.ConfigReglasAsociacion;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class GeneradorConjuntoDatos {

	private final int numAtributos;
	private final int numInstancias;
	private final boolean atrNumericos;
	private final int numIntervalos;
	
    private static final int minNumAtr = 4;
    private static final int maxNumAtr = 8;
    private static final int minNumInstancias = 10;
    private static final int maxNumInstancias = 16;
    private static final int minNumIntervalos = 2;
    private static final int maxNumIntervalos = 4;
	
	public GeneradorConjuntoDatos(ConfigReglasAsociacion config) {
		Random r = new Random();
		
		if (config.getNumAtributos() == 0) {
			numAtributos = r.nextInt((maxNumAtr - minNumAtr) + 1) + minNumAtr;
		} else {
			numAtributos = config.getNumAtributos();
		}
		
		if (config.getNumInstancias() == 0) {
			numInstancias = r.nextInt((maxNumInstancias - minNumInstancias) + 1) + minNumInstancias;
		}
		else {
			numInstancias = config.getNumInstancias();
		}
		
		if (config.isAtrNumericos() == ' ') {
			atrNumericos = r.nextBoolean();
		} else if (config.isAtrNumericos() == 's') {
			atrNumericos = true;
		} else {
			atrNumericos = false;
		}
		
		if (config.getNumIntervalos() == 0) {
			numIntervalos = r.nextInt((maxNumIntervalos - minNumIntervalos) + 1) + minNumIntervalos;
		} else {
			numIntervalos = config.getNumIntervalos();
		}
	}

	private ArrayList<Attribute> crearAtributos(int numAtributos) {
		ArrayList<Attribute> atributos = new ArrayList<>();
		String nombreAtributo;
		List<String> etiquetas;
		
		for (int i = 0; i < numAtributos; i++) {	
			nombreAtributo = "a" + (i + 1);
	        etiquetas = new ArrayList<>();
	        etiquetas.add("T");
	        etiquetas.add("F");
	        atributos.add(new Attribute(nombreAtributo, etiquetas));
	    }
		return atributos;
	}
	
	public ConjuntoDatos crearConjuntoDatos() {
		Random rand;
		
		crearAtributos(numAtributos);
	    Instances datosEnunciado = new Instances("datos", crearAtributos(numAtributos), 0);

	    rand = new Random();

	    for (int i = 0; i < numInstancias; i++) {

	      Instance instancia = new DenseInstance(numAtributos);
	      instancia.setDataset(datosEnunciado); 
	      
	      for (int j = 0; j < numAtributos; j++) {
	    	  instancia.setValue(j, (rand.nextInt(2) == 0) ? "T" : "F");
	      }
	      datosEnunciado.add(instancia);
	    }
	    
	    if(atrNumericos) {
	    	return añadirAtributosNumericos(datosEnunciado);
	    }
	    
	    return new ConjuntoDatos(datosEnunciado, datosEnunciado, null);
	    	       
	}
	
	private ConjuntoDatos añadirAtributosNumericos(Instances datos) {
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
		discretizar.setUseEqualFrequency(false);
		
		try {
			discretizar.setInputFormat(datos);
			Instances datosCalculos = Filter.useFilter(datos, discretizar);
			double[] puntosCorte = discretizar.getCutPoints(datosCalculos.numAttributes()-1);
			ConjuntoDatos conjuntoDatos = new ConjuntoDatos(datos, datosCalculos, establecerIntervalos(puntosCorte));
			return conjuntoDatos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedException(e);
		}		
			
	}
	
	private String establecerIntervalos(double[] puntosCorte) {
		String intervalos = "(";
		
	    intervalos += "- ; " + Math.round(puntosCorte[0] * 10.0) / 10.0;
	    for (int i = 0; i < puntosCorte.length - 1; i++) {
	        intervalos += "], [" + Math.round((puntosCorte[i] + 0.1) * 10.0) / 10.0 + " ; " + Math.round((puntosCorte[i + 1]) * 10.0) / 10.0;
	    }
	    intervalos += "], [" + Math.round((puntosCorte[puntosCorte.length - 1] + 0.1) * 10.0) / 10.0 + " ; -)";
	    
	    return intervalos;
	}
}
