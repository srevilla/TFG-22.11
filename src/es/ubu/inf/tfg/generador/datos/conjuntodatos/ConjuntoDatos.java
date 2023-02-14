package es.ubu.inf.tfg.generador.datos.conjuntodatos;

import weka.core.Instances;

/**
 * Clase ConjuntoDatos que representa un conjunto de datos.
 * Contiene informacion de los datos del enunciado, los datos de los calculos y los intervalos.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class ConjuntoDatos {

	/**
	 * Atributo que almacena los datos que se usan en el enunciado. 
	 */
	private Instances datosEnunciado;

	/**
	 * Atributo que almacena los datos que se usan para los calculos.
	 */
	private Instances datosCalculos;

	/**
	 * Atributo que almacena los intervalos en los que se discretiza el atributo numerico.
	 */
	private String intervalos;

	/**
	 * Constructor que inicializa los atributos con los valores especificados.
	 * @param datosEnunciado - Los datos del enunciado
	 * @param datosCalculos - Los datos de los cálculos
	 * @param intervalos - Los intervalos en los que se discretiza el atributo numerico
	 */
	public ConjuntoDatos(Instances datosEnunciado, Instances datosCalculos, String intervalos) {
		this.datosEnunciado = datosEnunciado;
		this.datosCalculos = datosCalculos;
		this.intervalos = intervalos;
	}

	/**
	 * Devuelve los datos que se usan en el enunciado
	 * @return los datos que se usan en el enunciado
	 */
	public Instances getDatosEnunciado() {
		return datosEnunciado;
	}

	/**
	 * Establece los datos que se usan en el enunciado
	 * @param datosEnunciado - Los datos que se usan en el enunciado
	 */
	public void setDatosEnunciado(Instances datosEnunciado) {
		this.datosEnunciado = datosEnunciado;
	}

	/**
	 * Devuelve los datos que se usan en los calculos
	 * @return los datos que se usan en los calculos
	 */
	public Instances getDatosCalculos() {
		return datosCalculos;
	}

	/**
	 * Establece los datos que se usan en los calculos
	 * @param datosCalculos - Los datos que se usan en los calculos
	 */
	public void setDatosCalculos(Instances datosCalculos) {
		this.datosCalculos = datosCalculos;
	}

	/**
	 * Devuelve los intervalos en los que se discretiza el atributo numerico.
	 * @return los intervalos en los que se discretiza el atributo numerico
	 */
	public String getIntervalos() {
		return intervalos;
	}

	/**
	 * Establece los intervalos en los que se discretiza el atributo numerico.
	 * @param intervalos - Los intervalos en los que se discretiza el atributo numerico
	 */
	public void setIntervalos(String intervalos) {
		this.intervalos = intervalos;
	}
}
