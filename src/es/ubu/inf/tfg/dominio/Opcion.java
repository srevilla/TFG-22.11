package es.ubu.inf.tfg.dominio;

public class Opcion {

	private double peso;
	private String texto;
	
	public Opcion (double peso, String texto) {
		this.peso = peso;
		this.texto = texto;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	
}
