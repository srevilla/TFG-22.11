package es.ubu.inf.tfg.dominio;

import java.util.Objects;

/**
 * Clase Opcion que representa una opción de respuesta en una pregunta.
 * Contiene un peso asociado a la opción y un texto que representa la opción.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class Opcion {

	/**
	 * Atributo que almacena el peso asociado a la opción.
	 */
	private double peso;

	/**
	 * Atributo que almacena el texto que representa la opción.
	 */
	private String texto;

	/**
	 * Constructor que inicializa una nueva opción con el peso y el texto especificados.
	 * @param peso - el peso asociado a la opción.
	 * @param texto - el texto que representa la opción.
	 */
	public Opcion (double peso, String texto) {
		this.peso = peso;
		this.texto = texto;
	}

	/**
	 * Devuelve el código hash de esta opción.
	 * @return el código hash de esta opción.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(peso, texto);
	}


	/**
	 * Determina si esta opción es igual al objeto especificado.
	 * @param obj - El objeto con el que se compara esta opción.
	 * @return `true` si esta opción es igual al objeto especificado, `false` en caso contrario.
	 */
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

	/**
	 * Devuelve el peso asociado a la opción.
	 * @return el peso asociado a la opción.
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Establece el peso asociado a la opción.
	 * @param peso - El peso asociado a la opción.
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * Devuelve el texto que representa la opción.
	 * @return el texto que representa la opción.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Establece el texto que representa la opción.
	 * @param texto - El texto que representa la opción.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}	
}
