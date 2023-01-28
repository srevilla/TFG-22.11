package es.ubu.inf.tfg.generador.posiblesitemsets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;

public class GeneradorBancoPreguntasPosiblesItemSets implements GeneradorBancoPreguntas <ConfigPosiblesItemSets> {
	
	private static final int numOpciones = 4;
	private static final int maxPuntuacion = 100;
	private static final int minNumItemSets = 10;
	private static final int maxNumItemSets = 16;
	private static final int minTamItemSets = 3;
	private static final int maxTamItemSets = 4;

	@Override
	public BancoPreguntas generarBancoPreguntas(ConfigPosiblesItemSets config) {
		
		BancoPreguntas bancoPreguntas;

		boolean tieneSolucion = false;
		int contador = 0;
		List<Pregunta> listaPreguntas = new ArrayList<>();
        List<Opcion> opciones;
        int numRespuestasVerdaderas;
        int numRespuestasFalsas;
        Random random = new Random();
				
		while(!tieneSolucion || contador<config.getNumPreguntas()) {
			GeneradorPreguntaPosiblesItemSets generadorPregunta = establecerConfiguracion(config);
			opciones = new ArrayList<>();
			generadorPregunta.generarTotalConjuntos();
			numRespuestasVerdaderas = random.nextInt(numOpciones)+1;
		    numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
		    
		    try {
		        generadorPregunta.generarSoluciones();
		        tieneSolucion = generadorPregunta.tieneSolucion(numRespuestasVerdaderas);
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
	
	private GeneradorPreguntaPosiblesItemSets establecerConfiguracion(ConfigPosiblesItemSets config) {
		Random r = new Random();
		int numItemSets;
		int tamItemSets;
		
		if (config.getNumItemSets() == 0) {
			numItemSets = r.nextInt((maxNumItemSets - minNumItemSets) + 1) + minNumItemSets;
		} else {
			numItemSets = config.getNumItemSets();
		}
		
		if (config.getTamItemSets() == 0) {
			tamItemSets = r.nextInt((maxTamItemSets - minTamItemSets) + 1) + minTamItemSets;
		} else {
			tamItemSets = config.getTamItemSets();
		}
		
		return new GeneradorPreguntaPosiblesItemSets(numItemSets, tamItemSets);
	}
	
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}

}
