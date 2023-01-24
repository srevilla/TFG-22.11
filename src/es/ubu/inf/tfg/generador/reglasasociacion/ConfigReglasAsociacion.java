package es.ubu.inf.tfg.generador.reglasasociacion;

import java.text.DecimalFormat;
import java.util.Random;

public class ConfigReglasAsociacion {

	private int numPreguntas;
	private int numAtributos;
	private int numInstancias;
	private double soporte;
	private double confianza;
	private boolean atrDiscretos;
	private int numIntervalos;
	
	public ConfigReglasAsociacion(int numPreguntas, int numAtributos, int numInstancias, double soporte, double confianza, boolean atrDiscretos, int numIntervalos) {
		this.numPreguntas = numPreguntas;
		this.numAtributos = numAtributos;
		this.numInstancias = numInstancias;
		this.soporte = soporte;
		this.confianza = confianza;
		this.atrDiscretos = atrDiscretos;
		this.numIntervalos = numIntervalos;
	}

	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
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

	public boolean isAtrDiscretos() {
		return atrDiscretos;
	}

	public void setAtrDiscretos(boolean atrDiscretos) {
		this.atrDiscretos = atrDiscretos;
	}

	public int getNumIntervalos() {
		return numIntervalos;
	}

	public void setNumIntervalos(int numIntervalos) {
		this.numIntervalos = numIntervalos;
	}
	
//	public ConfigReglasAsociacion(int numAtributos, int numInstancias) {
//		Random rand = new Random();
//		this.numAtributos = numAtributos;
//		this.numInstancias = numInstancias;
////		setSoporte(rand.nextDouble() * 0.7 + 0.3);
//		
////		setSoporte(Math.round((rand.nextDouble() * 0.7 + 0.3) * 10.0) / 10.0);
//		setSoporte(Math.round((Math.random() * (0.6 - 0.3) + 0.3) * 10.0) / 10.0);
//		
////		setConfianza(rand.nextDouble() * 0.8 + 0.2);
////		setConfianza(Math.round((rand.nextDouble() * 0.8 + 0.2) * 100.0) / 100.0);
//		setConfianza(Math.round((Math.random() * (1 - 0.65) + 0.65) * 100.0) / 100.0);
//
//		setAtrDiscretos(rand.nextBoolean());
//		setNumIntervalos(rand.nextInt(3) + 2);
//	}
	
	
	
}
