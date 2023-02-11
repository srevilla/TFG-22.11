package es.ubu.inf.tfg.generador.preguntas.itemsets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.dominio.UnexpectedException;
import es.ubu.inf.tfg.generador.datos.conjuntodatos.ConjuntoDatos;
import es.ubu.inf.tfg.generador.datos.conjuntodatos.GeneradorConjuntoDatos;
import es.ubu.inf.tfg.generador.preguntas.reglasasociacion.ConfigReglasAsociacion;
import weka.associations.Apriori;
import weka.core.Instance;
import weka.core.Instances;
import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.associations.ItemSet;

public class GeneradorPreguntaItemSets {
	
	double soporte;
	double confianza;
	private GeneradorConjuntoDatos gcd;
		
    private static final double minSoporte = 0.3;
    private static final double maxSoporte = 0.6;
    private static final double minConfianza = 0.6;
    private static final double maxConfianza = 1.0;
	private static final int numOpciones = 4;
	private static final int maxPuntuacion = 100;

	public GeneradorPreguntaItemSets(ConfigReglasAsociacion config) {
		Random r = new Random();
		gcd = new GeneradorConjuntoDatos(config);
		
		if (config.getSoporte() == 0) {
			soporte = minSoporte + (maxSoporte - minSoporte) * r.nextDouble();
			soporte = Math.round(soporte * 10.0) / 10.0;
		} else {
			soporte = config.getSoporte();
		}
		
		if (config.getConfianza() == 0) {
			confianza = minConfianza + (maxConfianza - minConfianza) * Math.round(r.nextDouble() * 10.0) / 10.0;;
		} else {
			confianza = config.getConfianza();
		}
		
	}
	
	private String obtenerEnunciado(ConjuntoDatos conjuntoDatos) {
		return "Sea el siguiente conjunto de datos: " + getContenidoData(conjuntoDatos.getDatosEnunciado()) + "\r\n"
				+ "Seleccione los item sets con soporte mayor o igual que "+ (int)(soporte*10) + ".\r\n";	
	}
	
	private String obtenerTitulo() {
		return "Reglas Item Sets";
	}
	
	public Pregunta generarPregunta() {
        Random random = new Random();
		int numRespuestasVerdaderas = random.nextInt(numOpciones-1)+1;
	    int numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
	    boolean tieneSolucion = false;
	    Pregunta pregunta = null;
	    
	    while(!tieneSolucion) {
//	    	Random r = new Random();
//			int numRespuestasVerdaderas = r.nextInt(numOpciones-1)+1;
//		    int numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
//			soporte = minSoporte + (maxSoporte - minSoporte) * r.nextDouble();
//			soporte = Math.round(soporte * 10.0) / 10.0;
			Apriori apriori = new Apriori();

//			apriori.setLowerBoundMinSupport(soporte-0.3);
//			apriori.setUpperBoundMinSupport(soporte+0.3);
//			apriori.setMinMetric(0.8); 
			apriori.setNumRules(100);
			
			ConjuntoDatos conjuntoDatos = gcd.crearConjuntoDatos();
			Instances datosCalculos = conjuntoDatos.getDatosCalculos();
			
			try {
				apriori.buildAssociations(datosCalculos);
			} catch (Exception e) {
				throw new UnexpectedException(e);
			}
			
			List<AssociationRule> reglas = apriori.getAssociationRules().getRules();
		    		    
		    List<Collection<Item>> premiseItemsList = new ArrayList<>();
		    List<Collection<Item>> consequenceItemsList = new ArrayList<>();
		    List<Integer> soportesItemsSetsPremisa = new ArrayList<>();
		    List<Integer> soportesItemsSetsConseq = new ArrayList<>();

		    
		    for (int i = 0; i < reglas.size(); i++) {
		      AssociationRule rule = reglas.get(i);
		      Collection<Item> premiseItems = rule.getPremise();
		      Collection<Item> consequenceItems = rule.getConsequence();
		      
		      if(premiseItems.size()>=2) {
			      premiseItemsList.add(premiseItems);
			      soportesItemsSetsPremisa.add(rule.getPremiseSupport());
		      }
		      
		      if(consequenceItems.size()>=2) {
			      consequenceItemsList.add(consequenceItems);
			      soportesItemsSetsConseq.add(rule.getConsequenceSupport());
		      }
		    }
		    
		    List<Collection<Item>> combinedItemsList = new ArrayList<>();
		    combinedItemsList.addAll(premiseItemsList);
		    combinedItemsList.addAll(consequenceItemsList);	
		    
		    List<Integer> combinedSoportes = new ArrayList<>();
		    combinedSoportes.addAll(soportesItemsSetsPremisa);
		    combinedSoportes.addAll(soportesItemsSetsConseq);
		    
		    List<Collection<Item>> itemSetsVerdaderos = new ArrayList<>();
		    List<Collection<Item>> itemSetsFalsos = new ArrayList<>();

		    for (int i=0; i<combinedSoportes.size(); i++) {
		    	if(combinedSoportes.get(i)/(double)datosCalculos.size() >= soporte) {
		    		itemSetsVerdaderos.add(combinedItemsList.get(i));
		    	} else {
		    		itemSetsFalsos.add(combinedItemsList.get(i));
		    	}
		    }
		    
	        tieneSolucion = tieneSolucion(numRespuestasVerdaderas, numRespuestasFalsas, itemSetsVerdaderos, itemSetsFalsos);
	        if(tieneSolucion) {
	            List<Opcion> opciones = new ArrayList<>();
	            List<List<String>> opcionesGeneradas = generarOpciones(numRespuestasVerdaderas, numRespuestasFalsas, itemSetsVerdaderos, itemSetsFalsos);
	           	List<String> opcionesVerdaderas = opcionesGeneradas.get(0);
	           	List<String> opcionesFalsas = opcionesGeneradas.get(1);
	           	
            	double pesoVerdaderas = (double) establecerPuntuacion(numRespuestasVerdaderas);
            	double pesoFalsas = -establecerPuntuacion(numRespuestasFalsas);
            	
            	for (int i=0; i<numRespuestasVerdaderas; i++) {
            		String texto = opcionesVerdaderas.get(i).toString();
	            	Opcion opcion = new Opcion(pesoVerdaderas, texto);
	            	opciones.add(opcion);
	            }
            	
            	for (int i=0; i<numRespuestasFalsas; i++) {
            		String texto = opcionesFalsas.get(i).toString();
	            	Opcion opcion = new Opcion(pesoFalsas, texto);
	            	opciones.add(opcion);
	            }
            	
            	pregunta = new Pregunta(opciones, obtenerEnunciado(conjuntoDatos), obtenerTitulo());
	        }
	    }
		    	    
	    return pregunta;
	}
	
	private int generarIndex(int maxIndex) {
        int index = (int)(Math.random() * maxIndex);
        return index;
	}
	
	private double establecerPuntuacion(int numRespuestas) {
		return (double)maxPuntuacion/numRespuestas;
	}	    	  
	
	private List<List<String>> generarOpciones(int numRespuestasVerdaderas, int numRespuestasFalsas, List<Collection<Item>> itemSetsVerdaderos, List<Collection<Item>> itemSetsFalsos) {
		List<List<String>> resultado = new ArrayList<>();
		List<String> opcionesVerdaderas = new ArrayList<String>();
		List<String> opcionesFalsas = new ArrayList<String>();
		
		for (int i=0; i<numRespuestasVerdaderas; i++) {
			String itemSet = itemSetsVerdaderos.get(generarIndex(itemSetsVerdaderos.size())).toString();			

			if (!opcionesVerdaderas.contains(itemSet)) {
				opcionesVerdaderas.add(itemSet);
			} else {
				i--;
			}
		}
		
		for (int i=0; i<numRespuestasFalsas; i++) {
			String itemSet = itemSetsFalsos.get(generarIndex(itemSetsFalsos.size())).toString();		
			
			if (!opcionesFalsas.contains(itemSet)) {
				opcionesFalsas.add(itemSet);
			} else {
				i--;
			}
		}
		
		resultado.add(opcionesVerdaderas);
		resultado.add(opcionesFalsas);
		
		return resultado;
	}
	
	private boolean tieneSolucion(int numRespuestasVerdaderas, int numRespuestasFalsas, List<Collection<Item>> itemSetsVerdaderos, List<Collection<Item>> itemSetsFalsos) {
		
		Set<String> itemSetsVerdaderosSet = new HashSet<>();
		Set<String> itemSetsFalsosSet = new HashSet<>();

		for(Collection<Item> itemSet : itemSetsVerdaderos) {
			itemSetsVerdaderosSet.add(itemSet.toString());
		}
		
		for(Collection<Item> itemSet : itemSetsFalsos) {
			itemSetsFalsosSet.add(itemSet.toString());
		}
		
		if (itemSetsVerdaderosSet.size() >= numRespuestasVerdaderas
				&& itemSetsFalsosSet.size() >= numRespuestasFalsas) {
			return true;
		} else {
			return false;
		}
	}

	private String getContenidoData(Instances datos) {
		StringBuilder sb = new StringBuilder();
		  sb.append("<table>");

		  // Agrega los nombres de los atributos en la primera fila
		  sb.append("<tr>");
		  for (int i = 0; i < datos.numAttributes(); i++) {
		    sb.append("<th>");
		    sb.append(datos.attribute(i).name());
		    sb.append("</th>");
		  }
		  sb.append("</tr>");

		  // Agrega los valores de las instancias
		  for (int i = 0; i < datos.numInstances(); i++) {
		    Instance inst = datos.instance(i);
		    sb.append("<tr>");
		    for (int j = 0; j < inst.numAttributes(); j++) {
		      sb.append("<td>");
		      if (!inst.attribute(j).isNominal()) {
		        sb.append(String.valueOf(inst.value(j)));
		      } else {
		        sb.append(inst.stringValue(j));
		      }
		      sb.append("</td>");
		    }
		    sb.append("</tr>");
		  }

		  sb.append("</table>");
		  return sb.toString();
	}
}
