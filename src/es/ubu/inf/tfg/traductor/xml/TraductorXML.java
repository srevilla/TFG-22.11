package es.ubu.inf.tfg.traductor.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.traductor.Traductor;
import es.ubu.inf.tfg.traductor.Plantilla;

/**
 * Clase TraductorXML que implementa la interfaz Traductor
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class TraductorXML implements Traductor {

	/**
	 * Atributo que almacena la ruta en la que se va a guardar el archivo traducido.
	 */
	private String ruta;

	/**
	 * Constructor que inicializa la ruta en la que se va a guardar el archivo traducido.
	 * @param ruta - La ruta en la que se va a guardar el archivo traducido.
	 */
	public TraductorXML(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Método que traduce un banco de preguntas a un archivo XML.
	 * @param bancoPreguntas - El objeto BancoPreguntas que se desea traducir
	 * @throws IOException en caso de que haya un problema al escribir el archivo XML
	 */
	@Override
	public void traducir(BancoPreguntas bancoPreguntas) throws IOException {
		Plantilla plantilla = new Plantilla("plantilla.xml");
		generarPlantillas(bancoPreguntas, plantilla);
		try {
			exportarXML(ruta, plantilla);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que genera las plantillas de cada pregunta a partir de un banco de preguntas.
	 * @param bancoPreguntas - El banco de preguntas que se desea traducir
	 * @param plantilla - la plantilla principal que contiene todas las plantillas de preguntas
	 */
	private void generarPlantillas(BancoPreguntas bancoPreguntas, Plantilla plantilla) {
		List<Plantilla> totalDocumentos = new ArrayList<Plantilla>();

		for (int i=0; i<bancoPreguntas.getPreguntas().size(); i++) {
			totalDocumentos.add(traducePregunta(bancoPreguntas, i));
		}
		generarDocumento(totalDocumentos, plantilla);
	}

	/**
	 * Método privado que traduce una pregunta específica a una plantilla
	 * @param bancoPreguntas - El banco de preguntas que se desea traducir
	 * @param numPregunta - El número de la pregunta que se desea traducir
	 * @return la plantilla de la pregunta específica
	 */
	private Plantilla traducePregunta(BancoPreguntas bancoPreguntas, int numPregunta) {
		Plantilla documentoPregunta = new Plantilla("documento.xml");

		documentoPregunta.reemplazar("numero", String.valueOf(numPregunta+1));
		documentoPregunta.reemplazar("titulo", bancoPreguntas.getPreguntas().get(numPregunta).getTitulo() + "_" + (numPregunta+1));
		documentoPregunta.reemplazar("enunciado", bancoPreguntas.getPreguntas().get(numPregunta).getEnunciado());		
		List<Opcion> opciones = bancoPreguntas.getPreguntas().get(numPregunta).getOpciones();
		establecerTextoOpcion(opciones, documentoPregunta);
		establecerPesoOpcion(opciones, documentoPregunta);

		numPregunta++;

		return documentoPregunta;
	}

	/**
	 * Método que establece el texto de las opciones en la plantilla.
	 * @param opciones - Lista de opciones de la pregunta
	 * @param documentoPregunta - Plantilla del documento de la pregunta
	 */
	private void establecerTextoOpcion(List<Opcion> opciones, Plantilla documentoPregunta) {
		documentoPregunta.reemplazar("opcionA", opciones.get(0).getTexto());
		documentoPregunta.reemplazar("opcionB", opciones.get(1).getTexto());
		documentoPregunta.reemplazar("opcionC", opciones.get(2).getTexto());
		documentoPregunta.reemplazar("opcionD", opciones.get(3).getTexto());
	}

	/**
	 * Método que establece el peso de las opciones en la plantilla.
	 * @param opciones - Lista de opciones de la pregunta
	 * @param documentoPregunta - Plantilla del documento de la pregunta
	 */
	private void establecerPesoOpcion(List<Opcion> opciones, Plantilla documentoPregunta) {
		documentoPregunta.reemplazar("pesoA", String.valueOf(opciones.get(0).getPeso()));
		documentoPregunta.reemplazar("pesoB", String.valueOf(opciones.get(1).getPeso()));
		documentoPregunta.reemplazar("pesoC", String.valueOf(opciones.get(2).getPeso()));
		documentoPregunta.reemplazar("pesoD", String.valueOf(opciones.get(3).getPeso()));
	}

	/**
	 * Método que genera un documento a partir de una lista de plantillas.
	 * @param totalDocumentos - Lista de plantillas
	 * @param plantilla - Plantilla que contiene el documento final
	 */
	private void generarDocumento(List<Plantilla> totalDocumentos, Plantilla plantilla) {

		StringBuilder documento = new StringBuilder();

		for (Plantilla doc : totalDocumentos) {
			documento.append(doc.toString());
		}
		plantilla.reemplazar("documento", documento.toString());
	}

	/**
	 * Método que exporta una plantilla a un archivo XML en una ruta específica.
	 * @param ruta - Ruta donde se guardará el archivo XML
	 * @param plantilla - Plantilla que se exportará
	 * @throws IOException - Excepción que se lanza en caso de error al escribir el archivo
	 */
	private void exportarXML(String ruta, Plantilla plantilla) throws IOException {
		guardar(ruta, plantilla);
	}

	/**
	 * Este método se encarga de guardar un archivo con el contenido de una plantilla en la ruta especificada.
	 * @param ruta - La ruta donde se desea guardar el archivo.
	 * @param plantilla - La plantilla que contiene el contenido a guardar.
	 * @throws IOException - Si hay algún error en la escritura del archivo.
	 */
	private void guardar(String ruta, Plantilla plantilla) throws IOException {
		FileWriter fw = new FileWriter(ruta);
		fw.write(plantilla.toString());
		fw.close();
	}
}
