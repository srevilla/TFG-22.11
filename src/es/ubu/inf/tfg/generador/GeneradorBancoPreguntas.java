package es.ubu.inf.tfg.generador;

import es.ubu.inf.tfg.dominio.BancoPreguntas;

/**
 * Interfaz que define la estructura para generar un banco de preguntas.
 * @param <T> tipo de la configuración que se requiere para generar el banco de preguntas
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public interface GeneradorBancoPreguntas <T> {

	/**
	 * Método que genera un banco de preguntas a partir de una configuración dada.
	 * @param config - La configuración necesaria para generar el banco de preguntas
	 * @return el banco de preguntas generado
	 */
	BancoPreguntas generarBancoPreguntas(T config);
}
