package es.ubu.inf.tfg.generador.conjuntodatos;

import weka.core.Instances;

public class ConjuntoDatos {
	
	private Instances datosEnunciado;
	private Instances datosCalculos;
	private String intervalos;

	public ConjuntoDatos(Instances datosEnunciado, Instances datosCalculos, String intervalos) {
		this.datosEnunciado = datosEnunciado;
		this.datosCalculos = datosCalculos;
		this.intervalos = intervalos;
	}

	public Instances getDatosEnunciado() {
		return datosEnunciado;
	}

	public void setDatosEnunciado(Instances datosEnunciado) {
		this.datosEnunciado = datosEnunciado;
	}
	
	public Instances getDatosCalculos() {
		return datosCalculos;
	}

	public void setDatosCalculos(Instances datosCalculos) {
		this.datosCalculos = datosCalculos;
	}

	public String getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(String intervalos) {
		this.intervalos = intervalos;
	}
	
}
