package es.ubu.inf.tfg.generador.reglasasociacion;

import java.text.DecimalFormat;
import java.util.Random;

public class ConfigReglasAsociacion {

	private double soporte;
	private double confianza;
	private int numIntervalos;
	private int numAtributos;
	private int numInstancias;
	private boolean atrDiscretos;
	
//	public ConfigReglasAsociacion(double soporte, double confianza, int numIntervalos, int numAtributos, int numInstancias, String atrDiscretos) {
//		this.soporte = soporte;
//		this.confianza = confianza;
//		this.numIntervalos = numIntervalos;
//		this.numAtributos = numAtributos;
//		this.numInstancias = numInstancias;
//		this.atrDiscretos = atrDiscretos;
//	}
	public ConfigReglasAsociacion(int numAtributos, int numInstancias) {
		Random rand = new Random();
		this.numAtributos = numAtributos;
		this.numInstancias = numInstancias;
//		setSoporte(rand.nextDouble() * 0.7 + 0.3);
		
//		setSoporte(Math.round((rand.nextDouble() * 0.7 + 0.3) * 10.0) / 10.0);
		setSoporte(Math.round((Math.random() * (0.6 - 0.3) + 0.3) * 10.0) / 10.0);
		
//		setConfianza(rand.nextDouble() * 0.8 + 0.2);
//		setConfianza(Math.round((rand.nextDouble() * 0.8 + 0.2) * 100.0) / 100.0);
		setConfianza(Math.round((Math.random() * (1 - 0.65) + 0.65) * 100.0) / 100.0);

		setAtrDiscretos(rand.nextBoolean());
		setNumIntervalos(rand.nextInt(3) + 2);
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
		if(atrDiscretos) {
			this.numInstancias = numInstancias;
		} else {
			this.numInstancias = 0;
		}
	}

	public boolean getAtrDiscretos() {
		return atrDiscretos;
	}

	public void setAtrDiscretos(boolean atrDiscretos) {
		this.atrDiscretos = atrDiscretos;
	}
	
	
}
