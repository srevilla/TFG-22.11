package es.ubu.inf.tfg.generador.itemsets;

import java.util.ArrayList;
import java.util.List;

import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;
import es.ubu.inf.tfg.generador.reglasasociacion.ConfigReglasAsociacion;

public class GeneradorBancoPreguntasItemSets implements GeneradorBancoPreguntas <ConfigReglasAsociacion> {

	@Override
	public BancoPreguntas generarBancoPreguntas(ConfigReglasAsociacion config) {
		
		int contador = 0;
		List<Pregunta> listaPreguntas = new ArrayList<>();
				
		while(contador<config.getNumPreguntas()) {
			GeneradorPreguntaItemSets generadorPregunta = new GeneradorPreguntaItemSets(config);
			Pregunta nuevaPregunta = generadorPregunta.generarPregunta();
			if(!listaPreguntas.contains(nuevaPregunta)) {
				listaPreguntas.add(nuevaPregunta);
	        	contador++;
				System.out.println("ok-"+contador);
			}
		}
		
		return new BancoPreguntas(listaPreguntas);
		
	}
}
