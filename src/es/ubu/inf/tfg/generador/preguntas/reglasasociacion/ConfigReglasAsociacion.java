package es.ubu.inf.tfg.generador.preguntas.reglasasociacion;

public class ConfigReglasAsociacion {

	private int numPreguntas;
	private int numAtributos;
	private int numInstancias;
	private double soporte;
	private double confianza;
	private char atrNumericos;
	private int numIntervalos;
	
	public ConfigReglasAsociacion(int numPreguntas, int numAtributos, int numInstancias, double soporte, double confianza, char atrNumericos, int numIntervalos) {
		this.numPreguntas = numPreguntas;
		this.numAtributos = numAtributos;
		this.numInstancias = numInstancias;
		this.soporte = soporte;
		this.confianza = confianza;
		this.atrNumericos = atrNumericos;
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

	public char isAtrNumericos() {
		return atrNumericos;
	}

	public void setAtrNumericos(char atrDiscretos) {
		this.atrNumericos = atrDiscretos;
	}

	public int getNumIntervalos() {
		return numIntervalos;
	}

	public void setNumIntervalos(int numIntervalos) {
		this.numIntervalos = numIntervalos;
	}
		
}
