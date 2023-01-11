package ReglasAsociacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
//import weka.core.Attribute;
//import weka.core.DenseInstance;
//import weka.core.Instance;
import weka.core.Instances;

public class Solucion {

	private Instances datos;
	private Apriori apriori;
	private List<AssociationRule> reglas;
	private List<AssociationRule> reglasVerdaderas = new ArrayList<AssociationRule>();
	private List<AssociationRule> reglasFalsas = new ArrayList<AssociationRule>();

	public Solucion (Instances datos) {
		apriori = new Apriori();
		setDatos(datos);
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
			apriori.buildAssociations(datos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obtenerReglas() {
		ArrayList<Object>[] todasReglas = apriori.getAllTheRules();
		reglas = apriori.getAssociationRules().getRules();
		int a = apriori.getNumRules();
	}
	
	public void generarSoluciones(double soporte, double confianza) {
//		try {
//			apriori.setOptions(new String[]{"-T", "0,1", "-C", "0,2"});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		setSoporteMinimo(soporte);
		setConfianzaMinima(confianza);
		setNumReglas();
		ejecutaAlgoritmo();
		obtenerReglas();
		
		for (AssociationRule rule : reglas) {
			double confianzaRegla = rule.getPrimaryMetricValue();	
			BigDecimal bd = new BigDecimal(confianzaRegla).setScale(2, RoundingMode.HALF_UP);
			double confianzaRedondeada = bd.doubleValue();
			double soporteRegla = (double)rule.getTotalSupport()/datos.size();
			
            if (confianzaRedondeada >= confianza && 
                soporteRegla >= soporte) {
                reglasVerdaderas.add(rule);
            } else {
                reglasFalsas.add(rule);
            }
        }
		
//		for (AssociationRule rule : reglas) {
//			try {
//				if (rule.getTotalSupport() >= soporte*10 && rule.getMetricValuesForRule()[0] >= confianza*100) {
//					reglasVerdaderas.add(rule);
//				} else {
//					reglasFalsas.add(rule);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
	
	
}
