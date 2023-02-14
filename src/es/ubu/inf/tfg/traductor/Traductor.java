package es.ubu.inf.tfg.traductor;

import java.io.IOException;

import es.ubu.inf.tfg.dominio.BancoPreguntas;

/**
 * Interfaz que define la estructura para traducir un banco de preguntas.
 * @param bancoPreguntas - El banco de preguntas que se va a traducir
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public interface Traductor {
	
	/**
	 * Metodo que traduce un banco de preguntas.
	 * @param bancoPreguntas - El banco de preguntas que se va a traducir
	 * @throws IOException
	 */
	void traducir(BancoPreguntas bancoPreguntas) throws IOException;
}
