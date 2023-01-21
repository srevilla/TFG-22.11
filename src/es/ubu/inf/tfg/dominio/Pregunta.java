package es.ubu.inf.tfg.dominio;

import java.util.List;

public class Pregunta {
	
	List<Opcion> opciones;
	String enunciado;
	String titulo;
	
	public Pregunta (List<Opcion> opciones, String enunciado, String titulo) {
		this.opciones = opciones;
		this.enunciado = enunciado;
		this.titulo = titulo;
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
