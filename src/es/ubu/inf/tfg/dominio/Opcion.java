package es.ubu.inf.tfg.dominio;

import java.util.Objects;

public class Opcion {

	private double peso;
	private String texto;
	
	public Opcion (double peso, String texto) {
		this.peso = peso;
		this.texto = texto;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(peso, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opcion other = (Opcion) obj;
		return Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso)
				&& Objects.equals(texto, other.texto);
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
