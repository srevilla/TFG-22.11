package es.ubu.inf.tfg.generador.preguntas.reglasasociacion;

import java.util.ArrayList;
import java.util.List;
import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;

public class GeneradorBancoPreguntasReglasAsociacion implements GeneradorBancoPreguntas <ConfigReglasAsociacion> {

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
