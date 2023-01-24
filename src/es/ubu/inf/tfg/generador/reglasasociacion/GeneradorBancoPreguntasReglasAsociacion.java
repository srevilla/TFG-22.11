package es.ubu.inf.tfg.generador.reglasasociacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;

public class GeneradorBancoPreguntasReglasAsociacion implements GeneradorBancoPreguntas <ConfigReglasAsociacion> {

	private static final int numOpciones = 4;
	private static final int maxPuntuacion = 100;

	@Override
	public BancoPreguntas generarBancoPreguntas(ConfigReglasAsociacion config) {
		BancoPreguntas bancoPreguntas;
		
		boolean tieneSolucion = false;
		int contador = 0;
		List<Pregunta> listaPreguntas = new ArrayList<>();
        List<Opcion> opciones;
        int numRespuestasVerdaderas;
        int numRespuestasFalsas;
        Random random = new Random();
		
//		GeneradorPreguntaReglasAsociacion generadorPregunta = new GeneradorPreguntaReglasAsociacion(config.getSoporte(), config.getConfianza(), config.getNumIntervalos(), config.getNumAtributos(), config.getNumInstancias(), config.getAtrDiscretos());
		
		while(!tieneSolucion || contador<config.getNumPreguntas()) {
			GeneradorPreguntaReglasAsociacion generadorPregunta = new GeneradorPreguntaReglasAsociacion(config.getSoporte(), config.getConfianza(), config.getNumIntervalos(), config.getNumAtributos(), config.getNumInstancias(), config.isAtrDiscretos());

			opciones = new ArrayList<>();
	    	generadorPregunta.crearConjuntoDatos();
			numRespuestasVerdaderas = random.nextInt(numOpciones)+1;
		    numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
		    
		    if (config.isAtrDiscretos()) {
	    		generadorPregunta.añadirDiscretizacion();
	    	}
		    
		    try {
		        generadorPregunta.generarSoluciones();
		        tieneSolucion = generadorPregunta.tieneSolucion(numRespuestasVerdaderas, numRespuestasFalsas);
		        if(tieneSolucion) {
		            generadorPregunta.generarOpciones(numRespuestasFalsas, numRespuestasVerdaderas);
		           		           	            
	            	double pesoVerdaderas = (double) establecerPuntuacion(numRespuestasVerdaderas);
	            	double pesoFalsas = -establecerPuntuacion(numRespuestasFalsas);
	            	
	            	for (int i=0; i<numRespuestasVerdaderas; i++) {
	            		String texto = generadorPregunta.getOpcionesVerdaderas().get(i).toString();
		            	Opcion opcion = new Opcion(pesoVerdaderas, texto);
		            	opciones.add(opcion);
		            }
	            	
	            	for (int i=0; i<numRespuestasFalsas; i++) {
	            		String texto = generadorPregunta.getOpcionesFalsas().get(i).toString();
		            	Opcion opcion = new Opcion(pesoFalsas, texto);
		            	opciones.add(opcion);
		            }
	            	
	            	Pregunta pregunta = new Pregunta(opciones, generadorPregunta.obtenerEnunciado(), generadorPregunta.obtenerTitulo());
	            	listaPreguntas.add(pregunta);
	            	contador++;
		        }
		    } catch (Exception e) {
		        System.out.println("Error al generar la pregunta: " + e.getMessage());
		    }
		}
		
		bancoPreguntas = new BancoPreguntas(listaPreguntas);
		
		return bancoPreguntas;
	}
	
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}

}
