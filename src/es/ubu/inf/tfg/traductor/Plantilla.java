package es.ubu.inf.tfg.traductor;

import java.io.IOException;
import java.io.InputStream;

/**
 * Clase Plantilla que representa la plantilla de un archivo de texto.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class Plantilla {

	/**
	 * Atributo que almacena el contenido de la plantilla.
	 */
	private String plantilla;

	/**
	 * Constructor que recibe el nombre del archivo que contiene la plantilla y 
	 * almacena su contenido en el atributo "plantilla".
	 * 
	 * @param fichero - Nombre del archivo que contiene la plantilla.
	 */
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

	/**
	 * Metodo que permite reemplazar un atributo especificado en la plantilla 
	 * por un valor especificado.
	 * 
	 * @param atributo - Atributo que se desea reemplazar en la plantilla.
	 * @param valor - Valor que se desea asignar al atributo.
	 */
	public void reemplazar(String atributo, String valor) {
		plantilla = plantilla.replace("{" + atributo + "}", valor);
	}

	/**
	 * Metodo que permite obtener el contenido de la plantilla.
	 * 
	 * @return el contenido de la plantilla.
	 */
	public String toString() {
		return plantilla;
	}
}
