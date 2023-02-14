package es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets;

import java.util.ArrayList;
import java.util.List;
import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;

/**
 * Clase GeneradorBancoPreguntasAmpliacionItemSets que genera un banco de preguntas 
 * de tipo Ampliacion Item Sets.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class GeneradorBancoPreguntasAmpliacionItemSets implements GeneradorBancoPreguntas <ConfigAmpliacionItemSets> {

	/**
	 * Metodo que genera un banco de preguntas a partir de una configuracion.
	 * @param config - La configuración de Ampliacion Item Sets
	 */
	@Override
	public BancoPreguntas generarBancoPreguntas(ConfigAmpliacionItemSets config) {

		int contador = 0;
		List<Pregunta> listaPreguntas = new ArrayList<>();

		while(contador<config.getNumPreguntas()) {
			GeneradorPreguntaAmpliacionItemSets generadorPregunta = new GeneradorPreguntaAmpliacionItemSets(config);
			Pregunta nuevaPregunta = generadorPregunta.generarPregunta();
			if(!listaPreguntas.contains(nuevaPregunta)) {
				listaPreguntas.add(nuevaPregunta);
				contador++;
			} 
		}		
		return new BancoPreguntas(listaPreguntas);	
	}
}
