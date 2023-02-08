package es.ubu.inf.tfg.dominio;

import java.util.List;
import java.util.Objects;

public class Pregunta {
	
	List<Opcion> opciones;
	String enunciado;
	String titulo;
	
	public Pregunta (List<Opcion> opciones, String enunciado, String titulo) {
		this.opciones = opciones;
		this.enunciado = enunciado;
		this.titulo = titulo;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(enunciado, opciones, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pregunta other = (Pregunta) obj;
		return Objects.equals(enunciado, other.enunciado) && Objects.equals(opciones, other.opciones)
				&& Objects.equals(titulo, other.titulo);
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	

}
