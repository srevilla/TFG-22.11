package es.ubu.inf.tfg.generador.preguntas.reglasasociacion;

/**
 * Clase que define la configuración de las preguntas Generacion Item Sets y Generacion Reglas Asociacion. 
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class ConfigReglasAsociacion {

	/**
	 * Atributo que almacena el numero de preguntas que se van a generar.
	 */
	private int numPreguntas;

	/**
	 * Atributo que almacena el numero de atributos del conjunto de datos.
	 */
	private int numAtributos;

	/**
	 * Atributo que almacena el numero de instancias del conjunto de datos.
	 */
	private int numInstancias;

	/**
	 * Atributo que almacena el valor del soporte.
	 */
	private double soporte;

	/**
	 * Atributo que almacena el valor de la confianza.
	 */
	private double confianza;

	/**
	 * Atributo que indica si hay atributos numericos.
	 */
	private char atrNumericos;

	/**
	 * Atributo que almacena el numero de intervalos.
	 */
	private int numIntervalos;

	/**
	 * Constructor que inicializa los datos necesarios para la configuracion. 
	 * @param numPreguntas - Numero de preguntas
	 * @param numAtributos - Numero de atributos 
	 * @param numInstancias - Numero de instancias
	 * @param soporte - Valor del soporte
	 * @param confianza - Valor de la confianza
	 * @param atrNumericos - Indica si hay o no atributos numericos
	 * @param numIntervalos - Numero de intervalos
	 */
	public ConfigReglasAsociacion(int numPreguntas, int numAtributos, int numInstancias, double soporte, double confianza, char atrNumericos, int numIntervalos) {
		this.numPreguntas = numPreguntas;
		this.numAtributos = numAtributos;
		this.numInstancias = numInstancias;
		this.soporte = soporte;
		this.confianza = confianza;
		this.atrNumericos = atrNumericos;
		this.numIntervalos = numIntervalos;
	}

	/**
	 * Devuelve el número de preguntas.
	 * @return el número de preguntas.
	 */
	public int getNumPreguntas() {
		return numPreguntas;
	}

	/**
	 * Establece el número de preguntas.
	 * @param numPreguntas el nuevo número de preguntas.
	 */
	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}

	/**
	 * Obtiene el soporte.
	 * @return el soporte.
	 */
	public double getSoporte() {
		return soporte;
	}

	/**
	 * Establece el soporte.
	 * @param soporte el nuevo soporte.
	 */
	public void setSoporte(double soporte) {
		this.soporte = soporte;
	}

	/**
	 * Devuelve la confianza.
	 * @return la confianza.
	 */
	public double getConfianza() {
		return confianza;
	}

	/**
	 * Establece la confianza.
	 * @param confianza la nueva confianza.
	 */
	public void setConfianza(double confianza) {
		this.confianza = confianza;
	}

	/**
	 * Devuelve el número de atributos.
	 * @return el número de atributos.
	 */
	public int getNumAtributos() {
		return numAtributos;
	}

	/**
	 * Establece el número de atributos.
	 * @param numAtributos el nuevo número de atributos.
	 */
	public void setNumAtributos(int numAtributos) {
		this.numAtributos = numAtributos;
	}

	/**
	 * Devuelve el número de instancias.
	 * @return el número de instancias.
	 */
	public int getNumInstancias() {
		return numInstancias;
	}

	/**
	 * Establece el número de instancias.
	 * @param numInstancias el nuevo número de instancias.
	 */
	public void setNumInstancias(int numInstancias) {
		this.numInstancias = numInstancias;
	}

	/**
	 * Verifica si los atributos son numéricos.
	 * @return 'T' si los atributos son numéricos, 'F' en caso contrario.
	 */
	public char isAtrNumericos() {
		return atrNumericos;
	}

	/**
	 * Establece si hay atributos numéricos.
	 * @param atrDiscretos - Indica si hay atributo es numerico.
	 */
	public void setAtrNumericos(char atrDiscretos) {
		this.atrNumericos = atrDiscretos;
	}

	/**
	 * Devuelve el numero de intervalos.
	 * @return el numero de intervalos
	 */
	public int getNumIntervalos() {
		return numIntervalos;
	}

	/**
	 * Establece el numero de intervalos.
	 * @param numIntervalos - El numero de intervalos
	 */
	public void setNumIntervalos(int numIntervalos) {
		this.numIntervalos = numIntervalos;
	}

}
