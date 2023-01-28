package es.ubu.inf.tfg.generador.reglasasociacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class GeneradorPreguntaReglasAsociacion {
		
	double soporte;
	double confianza;
	int numIntervalos;
	int numAtributos;
	int numInstancias;
	private boolean atrDiscretos;
	private Instances datos;
	private Instances datosUso;
	private String intervalos;
	private Apriori apriori;
	private List<AssociationRule> reglas;
	private List<AssociationRule> reglasVerdaderas;
	private List<AssociationRule> reglasFalsas;
	private List<String> opcionesVerdaderas;
	private List<String> opcionesFalsas;

	
	public GeneradorPreguntaReglasAsociacion(int numAtributos, int numInstancias, double soporte, double confianza, boolean atrDiscretos, int numIntervalos) {
		this.numAtributos = numAtributos;
		this.numInstancias = numInstancias;
		this.soporte = soporte;
		this.confianza = confianza;
		this.atrDiscretos = atrDiscretos;
		this.numIntervalos = numIntervalos;
	}
	
	public String obtenerEnunciado() {
		String enunciado  = "Sea el siguiente conjunto de datos: " + getContenidoData() + "\r\n"
				+ "Obtenga aquellas reglas de asociacion con soporte y confianza mayores o iguales que, respectivamente, "+ (int)(soporte*10) + " y " + (int)(confianza*100) + "%.\r\n";
		if (atrDiscretos) {
			return enunciado + "El atributo X debe discretizarse entre los siguientes intervalos: " + intervalos;
		} else {
			return enunciado;
		}
		
	}
	
	public String obtenerTitulo() {
		return "Reglas Asociacion";
	}
	
	private ArrayList<Attribute> crearAtributos(int numAtributos) {
		ArrayList<Attribute> atributos = new ArrayList<>();
		String nombreAtributo;
		List<String> etiquetas;
		
		for (int i = 0; i < numAtributos; i++) {			
	        nombreAtributo = Character.toString((char) ('A' + i));
	        etiquetas = new ArrayList<>();
	        etiquetas.add("T");
	        etiquetas.add("F");
	        atributos.add(new Attribute(nombreAtributo, etiquetas));
	    }
		return atributos;
	}
	
	public void crearConjuntoDatos() {
		Random rand;
		
		crearAtributos(numAtributos);
	    datos = new Instances("datos", crearAtributos(numAtributos), 0);

	    rand = new Random();

	    for (int i = 0; i < numInstancias; i++) {

	      Instance instancia = new DenseInstance(numAtributos);
	      instancia.setDataset(datos); 
	      
	      for (int j = 0; j < numAtributos; j++) {
	    	  instancia.setValue(j, (rand.nextInt(2) == 0) ? "T" : "F");
	      }
	      datos.add(instancia);
	    }
	}
	
	public void añadirDiscretizacion() {
		Attribute nuevoAtr = new Attribute("X");
		datos.insertAttributeAt(nuevoAtr, datos.numAttributes());
		
		Random random = new Random();
		int atrIndex = datos.attribute("X").index();

		for (int i = 0; i < datos.numInstances(); i++) {
			double randomValue = Math.round((0 + (100 - 0) * random.nextDouble())*10)/10.0;
		    datos.instance(i).setValue(atrIndex, randomValue);
		}
		
		Discretize discretizar = new Discretize();

		discretizar.setAttributeIndices("last");
		discretizar.setBins(numIntervalos);
		discretizar.setUseEqualFrequency(false);
		
		try {
			discretizar.setInputFormat(datos);
			datosUso = Filter.useFilter(datos, discretizar);
			double[] puntosCorte = discretizar.getCutPoints(datosUso.numAttributes()-1);
			establecerIntervalos(puntosCorte);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void generarSoluciones() {
		apriori = new Apriori();
		reglasVerdaderas = new ArrayList<AssociationRule>();
		reglasFalsas = new ArrayList<AssociationRule>();
		
		setDatosUso(datosUso);
		setSoporteMinimo(soporte);
		setConfianzaMinima(confianza);
		setNumReglas();
		ejecutaAlgoritmo();
		obtenerReglas();
		
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
		opcionesVerdaderas = new ArrayList<String>();
		opcionesFalsas = new ArrayList<String>();
		
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
	
	private void establecerIntervalos(double[] puntosCorte) {
	    intervalos = "[";
	    intervalos += "- ; " + Math.round(puntosCorte[0] * 10.0) / 10.0;
	    for (int i = 0; i < puntosCorte.length - 1; i++) {
	        intervalos += "], [" + Math.round((puntosCorte[i] + 0.1) * 10.0) / 10.0 + " ; " + Math.round((puntosCorte[i + 1]) * 10.0) / 10.0;
	    }
	    intervalos += "], [" + Math.round((puntosCorte[puntosCorte.length - 1] + 0.1) * 10.0) / 10.0 + " ; -]";
	}
	
	public boolean tieneSolucion(int numRespuestasVerdaderas, int numRespuestasFalsas) {
		if (reglasVerdaderas.size() >= numRespuestasVerdaderas
				&& reglasFalsas.size() >= numRespuestasFalsas) {
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

	public int getNumIntervalos() {
		return numIntervalos;
	}

	public void setNumIntervalos(int numIntervalos) {
		this.numIntervalos = numIntervalos;
	}

	public int getNumAtributos() {
		return numAtributos;
	}

	public void setNumAtributos(int numAtributos) {
		this.numAtributos = numAtributos;
	}

	public int getNumInstancias() {
		return numInstancias;
	}

	public void setNumInstancias(int numInstancias) {
		this.numInstancias = numInstancias;
	}

	public boolean getAtrDiscretos() {
		return atrDiscretos;
	}

	public void setAtrDiscretos(boolean atrDiscretos) {
		this.atrDiscretos = atrDiscretos;
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

	public void setDatosUso(Instances datosUso) {
		if(datosUso == null) {
			this.datosUso = datos;
		} else {
			this.datosUso = datosUso;
		}
	}

	public String getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(String intervalos) {
		this.intervalos = intervalos;
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

	public List<String> getOpcionesVerdaderas() {
		return opcionesVerdaderas;
	}

	public void setOpcionesVerdaderas(List<String> opcionesVerdaderas) {
		this.opcionesVerdaderas = opcionesVerdaderas;
	}

	public List<String> getOpcionesFalsas() {
		return opcionesFalsas;
	}

	public void setOpcionesFalsas(List<String> opcionesFalsas) {
		this.opcionesFalsas = opcionesFalsas;
	}
	
	

}
