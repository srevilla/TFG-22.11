package es.ubu.inf.tfg.generador.preguntas.reglasasociacion;

import java.util.ArrayList;
import java.util.List;
import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;

/**
 * Clase GeneradorBancoPreguntasReglasAsociacion que genera un banco de preguntas 
 * de tipo Generacion Reglas Asociacion.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class GeneradorBancoPreguntasReglasAsociacion implements GeneradorBancoPreguntas <ConfigReglasAsociacion> {

	/**
	 * Metodo que genera un banco de preguntas a partir de una configuracion.
	 * @param config - La configuración de Generacion Reglas Asociacion
	 */
	@Override
	public BancoPreguntas generarBancoPreguntas(ConfigReglasAsociacion config) {

		int contador = 0;
		List<Pregunta> listaPreguntas = new ArrayList<>();

		while(contador<config.getNumPreguntas()) {
			GeneradorPreguntaReglasAsociacion generadorPregunta = new GeneradorPreguntaReglasAsociacion(config);
			Pregunta nuevaPregunta = generadorPregunta.generarPregunta();
			if(!listaPreguntas.contains(nuevaPregunta)) {
				listaPreguntas.add(nuevaPregunta);
				contador++;
			}
		}

		return new BancoPreguntas(listaPreguntas);

	}
}
