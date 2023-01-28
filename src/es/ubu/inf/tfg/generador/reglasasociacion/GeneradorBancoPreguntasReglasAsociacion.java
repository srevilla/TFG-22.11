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
    private static final int minNumAtr = 4;
    private static final int maxNumAtr = 10;
    private static final int minNumInstancias = 10;
    private static final int maxNumInstancias = 20;
    private static final double minSoporte = 0.3;
    private static final double maxSoporte = 0.6;
    private static final double minConfianza = 0.6;
    private static final double maxConfianza = 1.0;
    private static final int minNumIntervalos = 2;
    private static final int maxNumIntervalos = 4;

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
				
		while(!tieneSolucion || contador<config.getNumPreguntas()) {
			GeneradorPreguntaReglasAsociacion generadorPregunta = establecerConfiguracion(config);

			opciones = new ArrayList<>();
	    	generadorPregunta.crearConjuntoDatos();
			numRespuestasVerdaderas = random.nextInt(numOpciones)+1;
		    numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
		    
		    if (generadorPregunta.getAtrDiscretos()) {
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
	
	private GeneradorPreguntaReglasAsociacion establecerConfiguracion(ConfigReglasAsociacion config) {
		Random r = new Random();
		int numAtributos;
		int numInstancias;
		double soporte;
		double confianza;
		boolean atrDiscretos;
		int numIntervalos;
		
		if (config.getNumAtributos() == 0) {
			numAtributos = r.nextInt((maxNumAtr - minNumAtr) + 1) + minNumAtr;
		} else {
			numAtributos = config.getNumAtributos();
		}
		
		if (config.getNumInstancias() == 0) {
			numInstancias = r.nextInt((maxNumInstancias - minNumInstancias) + 1) + minNumInstancias;
		}
		else {
			numInstancias = config.getNumInstancias();
		}
		
		if (config.getSoporte() == 0) {
			soporte = r.nextDouble((maxSoporte - minSoporte) + 1) + minSoporte;
		} else {
			soporte = config.getSoporte();
		}
		
		if (config.getConfianza() == 0) {
			confianza = r.nextDouble((maxConfianza - minConfianza) + 1) + minConfianza;
		} else {
			confianza = config.getConfianza();
		}
		
		if (config.isAtrDiscretos() == ' ') {
			atrDiscretos = r.nextBoolean();
		} else if (config.isAtrDiscretos() == 's') {
			atrDiscretos = true;
		} else {
			atrDiscretos = false;
		}
		
		if (config.getNumIntervalos() == 0) {
			numIntervalos = r.nextInt((maxNumIntervalos - minNumIntervalos) + 1) + minNumIntervalos;
		} else {
			numIntervalos = config.getNumIntervalos();
		}
		
		return new GeneradorPreguntaReglasAsociacion(numAtributos, numInstancias, soporte, confianza, atrDiscretos, numIntervalos);
	}
	
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}

}
