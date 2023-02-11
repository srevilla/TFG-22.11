package es.ubu.inf.tfg.traductor;

import java.io.IOException;
import java.io.InputStream;

public class Plantilla {

	private String plantilla;

	public Plantilla(String fichero) {
		
		String contenido = "";
		try {
			InputStream inputStream = getClass().getResourceAsStream(fichero);
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);
			contenido = new String(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		plantilla = contenido;
	}

	public void reemplazar(String atributo, String valor) {
		plantilla = plantilla.replace("{" + atributo + "}", valor);
	}

	public String toString() {
		return plantilla;
	}

}
