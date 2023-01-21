package es.ubu.inf.tfg.dominio;

import java.util.ArrayList;
import java.util.List;

public class BancoPreguntas {

	List<Pregunta> preguntas = new ArrayList<>();
	
	public BancoPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	
}
