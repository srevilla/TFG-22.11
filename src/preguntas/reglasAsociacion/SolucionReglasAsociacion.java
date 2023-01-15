package preguntas.reglasAsociacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.core.Instance;
import weka.core.Instances;
//import org.apache.commons.text.TableFormatter;

public class SolucionReglasAsociacion {

	private Instances datos;
	private Instances datosUso;
	private Apriori apriori;
	private List<AssociationRule> reglas;
	private List<AssociationRule> reglasVerdaderas = new ArrayList<AssociationRule>();
	private List<AssociationRule> reglasFalsas = new ArrayList<AssociationRule>();
	private List<String> opciones;
	private int numOpcionesVerdaderas;
	private int numOpcionesFalsas;
	private double soporte;
	private double confianza;

	public SolucionReglasAsociacion (Instances datos, Instances datosDiscretizacion) {
		apriori = new Apriori();
		setDatos(datos);
		setDatosDiscretizacion(datosDiscretizacion);
	}
	
	public void setSoporteMinimo(double n) {
		apriori.setMinMetric(0.3); 
	}
	
	public void setConfianzaMinima(double n) {
		apriori.setLowerBoundMinSupport(0.2);
	}
	
	public void setNumReglas() {
		apriori.setNumRules(20);
	}
	
	public void ejecutaAlgoritmo() {
		try {
			apriori.buildAssociations(datosUso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obtenerReglas() {
		reglas = apriori.getAssociationRules().getRules();
	}
	
	public void generarSoluciones(double soporte, double confianza) {
		setSoporteMinimo(soporte);
		setConfianzaMinima(confianza);
		setNumReglas();
		ejecutaAlgoritmo();
		obtenerReglas();
		setSoporte(soporte);
		setConfianza(confianza);
		
		for (AssociationRule rule : reglas) {
			double confianzaRegla = rule.getPrimaryMetricValue();	
			BigDecimal bd = new BigDecimal(confianzaRegla).setScale(2, RoundingMode.HALF_UP);
			double confianzaRedondeada = bd.doubleValue();
			double soporteRegla = (double)rule.getTotalSupport()/datosUso.size();
			
            if (confianzaRedondeada >= confianza && 
                soporteRegla >= soporte) {
                reglasVerdaderas.add(rule);
            } else {
                reglasFalsas.add(rule);
            }
        }
	}
	
	private int generarIndex(int maxIndex) {
        int index = (int)(Math.random() * maxIndex);
        return index;
	}
	
	public void generarOpciones(int numRespuestasFalsas, int numRespuestasVerdaderas) {
		opciones = new ArrayList<String>();
		numOpcionesVerdaderas = numRespuestasVerdaderas;
		numOpcionesFalsas = numRespuestasFalsas;
		
		for (int i=0; i<numRespuestasVerdaderas; i++) {
			String regla = getReglasVerdaderas().get(generarIndex(getReglasVerdaderas().size())).toString();
			String solucionAñadir = regla.substring(0, regla.indexOf("<"));
			solucionAñadir = solucionAñadir.replaceAll(":\\s*\\d+(\\.\\d+)?", ": ").replaceAll(":", ""); //En esta expresion regular, :\\s* busca el patrón ": " y \\d+(\\.\\d+)? busca cualquier número entero o decimal después del espacio en blanco. El paréntesis alrededor de este último patrón hace que sea capturado y reemplazado en conjunto con el patrón anterior.
			
			if (!opciones.contains(solucionAñadir)) {
				opciones.add(solucionAñadir);
			} else {
				i--;
			}
		}
		
		for (int i=0; i<numRespuestasFalsas; i++) {
			String regla = getReglasFalsas().get(generarIndex(getReglasFalsas().size())).toString();
			String solucionAñadir = regla.substring(0, regla.indexOf("<"));
			solucionAñadir = solucionAñadir.replaceAll(":\\s*\\d+(\\.\\d+)?", ": ").replaceAll(":", "");
			
			if (!opciones.contains(solucionAñadir)) {
				opciones.add(solucionAñadir);
			} else {
				i--;
			}
		}
	}
	
	public String getContenidoData() {
		StringBuilder sb = new StringBuilder();
	    sb.append("<br>");
	    // Agrega los nombres de los atributos en la primera fila
	    for (int i = 0; i < datos.numAttributes(); i++) {
	        sb.append(datos.attribute(i).name());
	        sb.append(" ");
	    }
	    
	    sb.append("<br>");
	    sb.append(" ");
	    
	    for (int i = 0; i < datos.numInstances(); i++) {
	        Instance inst=datos.instance(i);
	        for(int j=0; j<inst.numAttributes(); j++) {
	        	if(!inst.attribute(j).isNominal()) {
	        		sb.append(String.valueOf(inst.value(j)));
		            sb.append(" ");
	        	} else {
	        		sb.append(inst.stringValue(j));
		            sb.append(" ");
	        	}	            
	        }
		    sb.append("<br>");
	    }
	    return sb.toString();
//		TableFormatter formatter = new TableFormatter();
//	    // Agrega los nombres de los atributos en la primera fila
//	    for (int i = 0; i < datos.numAttributes(); i++) {
//	        formatter.addRow(datos.attribute(i).name());
//	    }
//
//	    for (int i = 0; i < datos.numInstances(); i++) {
//	        Instance inst=datos.instance(i);
//	        String[] row = new String[inst.numAttributes()];
//	        for(int j=0;j<inst.numAttributes();j++) {
//	            row[j] = inst.stringValue(j);
//	        }
//	        formatter.addRow(row);
//	    }
//	    return formatter.format();
	}
	
	public boolean tieneSolucion(int numRespuestasVerdaderas, int numRespuestasFalsas) {
		if (reglasVerdaderas.size() >= numRespuestasVerdaderas
				&& reglasFalsas.size() >= numRespuestasFalsas) {
			return true;
		} else {
			return false;
		}
	}

	public Instances getDatos() {
		return datos;
	}

	public void setDatos(Instances datos) {
		this.datos = datos;
	}
	
	public Instances getDatosUso() {
		return datosUso;
	}

	public void setDatosDiscretizacion(Instances datosUso) {
		if(datosUso.isEmpty()) {
			this.datosUso = datos;
		} else {
			this.datosUso = datosUso;
		}
	}

	public Apriori getApriori() {
		return apriori;
	}

	public void setApriori(Apriori apriori) {
		this.apriori = apriori;
	}

	public List<AssociationRule> getReglas() {
		return reglas;
	}

	public void setReglas(List<AssociationRule> reglas) {
		this.reglas = reglas;
	}

	public List<AssociationRule> getReglasVerdaderas() {
		return reglasVerdaderas;
	}

	public void setReglasVerdaderas(List<AssociationRule> reglasVerdaderas) {
		this.reglasVerdaderas = reglasVerdaderas;
	}

	public List<AssociationRule> getReglasFalsas() {
		return reglasFalsas;
	}

	public void setReglasFalsas(List<AssociationRule> reglasFalsas) {
		this.reglasFalsas = reglasFalsas;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public int getNumOpcionesVerdaderas() {
		return numOpcionesVerdaderas;
	}

	public void setNumOpcionesVerdaderas(int numOpcionesVerdaderas) {
		this.numOpcionesVerdaderas = numOpcionesVerdaderas;
	}

	public int getNumOpcionesFalsas() {
		return numOpcionesFalsas;
	}

	public void setNumOpcionesFalsas(int numOpcionesFalsas) {
		this.numOpcionesFalsas = numOpcionesFalsas;
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
	
}
