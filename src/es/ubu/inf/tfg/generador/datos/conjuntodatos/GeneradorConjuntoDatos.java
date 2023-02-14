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

/**
 * Clase GeneradorConjuntoDatos que genera un conjunto de datos.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class GeneradorConjuntoDatos {

	/**
	 * Atributo que almacena el numero de atributos del conjunto de datos.
	 */
	private final int numAtributos;

	/**
	 * Atributo que almacena el numero de instancias del conjunto de datos.
	 */
	private final int numInstancias;

	/**
	 * Atributo que indica si el conjunto va a tener un atributo numerico o no.
	 */
	private final boolean atrNumericos;

	/**
	 * Atributo que almacena el numero de intervalos en el que se va a discretizar al atributo numerico.
	 */
	private final int numIntervalos;

	/**
	 * Minimo numero de atributos permitidos.
	 */
	private static final int minNumAtr = 4;

	/**
	 * Maximo numero de atributos permitidos.
	 */
	private static final int maxNumAtr = 8;

	/**
	 * Minimo numero de instancias permitidas.
	 */
	private static final int minNumInstancias = 10;

	/**
	 * Maximo numero de instancias permitidas.
	 */
	private static final int maxNumInstancias = 16;

	/**
	 * Minimo numero de intervalos permitido.
	 */
	private static final int minNumIntervalos = 2;

	/**
	 * Maximo numero de intervalos permitido.
	 */
	private static final int maxNumIntervalos = 4;

	/**
	 * Constructor que inicializa los atributos con la configuración de reglas de asociación proporcionada. 
	 * Si cualquiera de los valores en la configuración no se especifica, se genera un valor aleatorio
	 * dentro del rango esperado para ese valor.
	 * @param config - La configuración de reglas de asociación.
	 */
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

	/**
	 * Este método crea una lista de atributos basados en el número de atributos especificado.
	 * Cada atributo se le asigna un nombre único y dos etiquetas posibles: "T" y "F".
	 * @param numAtributos - El número de atributos a ser creados
	 * @return la lista de atributos
	 */
	private ArrayList<Attribute> crearAtributos(int numAtributos) {
		ArrayList<Attribute> atributos = new ArrayList<>();
		String nombreAtributo;
		List<String> etiquetas;
		char letraActual = 'A';

		for (int i = 0; i < numAtributos; i++) {	
			nombreAtributo = Character.toString(letraActual);;
			etiquetas = new ArrayList<>();
			etiquetas.add("T");
			etiquetas.add("F");
			atributos.add(new Attribute(nombreAtributo, etiquetas));
			letraActual++;
		}
		return atributos;
	}

	/**
	 * Este método crea un nuevo conjunto de datos a partir de las configuraciones especificadas.
	 * Se crean los atributos según la cantidad especificada en la configuración.
	 * Se generan instancias aleatorias para cada atributo con valores T o F.
	 * Si la configuración especifica atributos numéricos, se agregarán a los datos.
	 * Se retorna un objeto con los datos generados y las configuraciones asociadas.
	 * @return el conjunto de datos generado
	 */
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

	/**
	 * Este método añade un atributo numérico llamado "X" a los datos y lo discretiza 
	 * en un número determinado de intervalos.
	 * La discretización se realiza utilizando la clase Discretize de WEKA.
	 * @param datos - Instancias a las que se añadirá el atributo numérico
	 * @return el conjunto de datos original más los datos discretizados con los puntos de corte
	 */
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

	/**
	 * Método para establecer los intervalos del atributo numérico discretizado.
	 * @param puntosCorte - Un array de doubles que representa los puntos de corte calculados para discretizar el atributo numérico.
	 * @return una cadena de caracteres que representa los intervalos del atributo numérico discretizado
	 */
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
