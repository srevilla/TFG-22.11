package es.ubu.inf.tfg.generador.preguntas.reglasasociacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.dominio.Pregunta;
import es.ubu.inf.tfg.dominio.UnexpectedException;
import es.ubu.inf.tfg.generador.datos.conjuntodatos.ConjuntoDatos;
import es.ubu.inf.tfg.generador.datos.conjuntodatos.GeneradorConjuntoDatos;
import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.core.Instance;
import weka.core.Instances;

public class GeneradorPreguntaReglasAsociacion {
		
	private double soporte;
	private double confianza;
	private boolean atrNumericos;
	private GeneradorConjuntoDatos gcd;
	
    private static final double minSoporte = 0.3;
    private static final double maxSoporte = 0.6;
    private static final double minConfianza = 0.6;
    private static final double maxConfianza = 1.0;
	private static final int numOpciones = 4;
	private static final int maxPuntuacion = 100;
		
	public GeneradorPreguntaReglasAsociacion(ConfigReglasAsociacion config) {
		Random r = new Random();
		gcd = new GeneradorConjuntoDatos(config);
		
		if (config.getSoporte() == 0) {
			soporte = minSoporte + (maxSoporte - minSoporte) * Math.round(r.nextDouble() * 10.0) / 10.0;
		} else {
			soporte = config.getSoporte();
		}
		
		if (config.getConfianza() == 0) {
			confianza = minConfianza + (maxConfianza - minConfianza) * Math.round(r.nextDouble() * 10.0) / 10.0;;
		} else {
			confianza = config.getConfianza();
		}
		
		if (config.isAtrNumericos() == ' ') {
			atrNumericos = r.nextBoolean();
		} else if (config.isAtrNumericos() == 's') {
			atrNumericos = true;
		} else {
			atrNumericos = false;
		}
		
	}
	
	private String obtenerEnunciado(ConjuntoDatos conjuntoDatos) {
		String enunciado  = "Sea el siguiente conjunto de datos: " + getContenidoData(conjuntoDatos.getDatosEnunciado()) + "\r\n"
				+ "Seleccione las reglas de asociacion con soporte y confianza mayores o iguales que, respectivamente, "+ (int)(soporte*10) + " y " + (int)(confianza*100) + "%.\r\n";
		if (atrNumericos) {
			return enunciado + "El atributo X debe discretizarse entre los siguientes intervalos: " + conjuntoDatos.getIntervalos();
		} else {
			return enunciado;
		}
		
	}
	
	private String obtenerTitulo() {
		return "Reglas Asociacion";
	}
	
	public Pregunta generarPregunta() {
		
        Random random = new Random();
		int numRespuestasVerdaderas = random.nextInt(numOpciones-1)+1;
	    int numRespuestasFalsas = numOpciones - numRespuestasVerdaderas;
	    boolean tieneSolucion = false;
	    Pregunta pregunta = null;
	    
		while(!tieneSolucion) {
			Apriori apriori = new Apriori();
			List<AssociationRule> reglasVerdaderas = new ArrayList<AssociationRule>();
			List<AssociationRule> reglasFalsas = new ArrayList<AssociationRule>();
	        		
			apriori.setMinMetric(0.3); 
			apriori.setLowerBoundMinSupport(0.2);
			apriori.setNumRules(20);
			
			ConjuntoDatos conjuntoDatos = gcd.crearConjuntoDatos();
			Instances datosCalculos = conjuntoDatos.getDatosCalculos();
			
			try {
				apriori.buildAssociations(datosCalculos);
			} catch (Exception e) {
				throw new UnexpectedException(e);
			}	
			
			List<AssociationRule> reglas = apriori.getAssociationRules().getRules();
			
			for (AssociationRule rule : reglas) {
				double confianzaRegla = rule.getPrimaryMetricValue();	
				BigDecimal bd = new BigDecimal(confianzaRegla).setScale(2, RoundingMode.HALF_UP);
				double confianzaRedondeada = bd.doubleValue();
				double soporteRegla = (double)rule.getTotalSupport()/datosCalculos.size();
				
	            if (confianzaRedondeada >= confianza && 
	                soporteRegla >= soporte) {
	                reglasVerdaderas.add(rule);
	            } else {
	                reglasFalsas.add(rule);
	            }
	        }
			
	        tieneSolucion = tieneSolucion(numRespuestasVerdaderas, numRespuestasFalsas, reglasVerdaderas, reglasFalsas);

	        if(tieneSolucion) {
	            List<Opcion> opciones = new ArrayList<>();
	            List<List<String>> opcionesGeneradas = generarOpciones(numRespuestasVerdaderas, numRespuestasFalsas, reglasVerdaderas, reglasFalsas);
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
	
	private List<List<String>> generarOpciones(int numRespuestasVerdaderas, int numRespuestasFalsas, List<AssociationRule> reglasVerdaderas, List<AssociationRule> reglasFalsas) {
		List<List<String>> resultado = new ArrayList<>();
		List<String> opcionesVerdaderas = new ArrayList<String>();
		List<String> opcionesFalsas = new ArrayList<String>();
		
		for (int i=0; i<numRespuestasVerdaderas; i++) {
			String regla = reglasVerdaderas.get(generarIndex(reglasVerdaderas.size())).toString();			
			String solucionFinal = modificarString(regla);

			if (!opcionesVerdaderas.contains(solucionFinal)) {
				opcionesVerdaderas.add(solucionFinal);
			} else {
				i--;
			}
		}
		
		for (int i=0; i<numRespuestasFalsas; i++) {
			String regla = reglasFalsas.get(generarIndex(reglasFalsas.size())).toString();		
			String solucionFinal = modificarString(regla);
			
			if (!opcionesFalsas.contains(solucionFinal)) {
				opcionesFalsas.add(solucionFinal);
			} else {
				i--;
			}
		}
		
		resultado.add(opcionesVerdaderas);
		resultado.add(opcionesFalsas);
		
		return resultado;
	}
	
	private String modificarString(String regla) {
		String solucionAñadir = regla.substring(0, regla.indexOf("<"));
		solucionAñadir = solucionAñadir.replaceAll(":\\s*\\d+(\\.\\d+)?", ": ").replaceAll(":", "");

		Pattern patron = Pattern.compile("-?\\d+(\\.\\d+)?");
		Matcher matcher = patron.matcher(solucionAñadir);

		while (matcher.find()) {
		    String numero = matcher.group();
		    double num = Double.parseDouble(numero);
		    double rounded = Math.round(num * 10.0) / 10.0;
		    solucionAñadir = solucionAñadir.replace(numero, String.valueOf(rounded));
		}
		
		return solucionAñadir;
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
	
	private boolean tieneSolucion(int numRespuestasVerdaderas, int numRespuestasFalsas, List<AssociationRule> reglasVerdaderas, List<AssociationRule> reglasFalsas) {
		
		Set<String> reglasVerdaderasSet = new HashSet<>();
		Set<String> reglasFalsasSet = new HashSet<>();

		for(AssociationRule regla : reglasVerdaderas) {
			reglasVerdaderasSet.add(regla.toString());
		}
		
		for(AssociationRule regla : reglasFalsas) {
			reglasFalsasSet.add(regla.toString());
		}
		if (reglasVerdaderasSet.size() >= numRespuestasVerdaderas
				&& reglasFalsasSet.size() >= numRespuestasFalsas) {
			return true;
		} else {
			return false;
		}
	}

	public double getSoporte() {
		return soporte;
	}

	public void setSoporte(double soporte) {
		this.soporte = soporte;
	}

	public double getConfianza() {
		return confianza;
	}

	public void setConfianza(double confianza) {
		this.confianza = confianza;
	}

	public boolean getAtrNumericos() {
		return atrNumericos;
	}

	public void setAtrNumericos(boolean atrDiscretos) {
		this.atrNumericos = atrDiscretos;
	}
	
}
