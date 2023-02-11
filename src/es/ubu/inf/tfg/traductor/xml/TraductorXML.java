package es.ubu.inf.tfg.traductor.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.dominio.Opcion;
import es.ubu.inf.tfg.traductor.Traductor;
import es.ubu.inf.tfg.traductor.Plantilla;

public class TraductorXML implements Traductor {
	
	private String ruta;
		
	public TraductorXML(String ruta) {
		this.ruta = ruta;
	}
	
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
	
	private void generarPlantillas(BancoPreguntas bancoPreguntas, Plantilla plantilla) {
		List<Plantilla> totalDocumentos = new ArrayList<Plantilla>();

		for (int i=0; i<bancoPreguntas.getPreguntas().size(); i++) {
			totalDocumentos.add(traducePregunta(bancoPreguntas, i));
		}
		generarDocumento(totalDocumentos, plantilla);
	}
	
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
	
	private void establecerTextoOpcion(List<Opcion> opciones, Plantilla documentoPregunta) {
		documentoPregunta.reemplazar("opcionA", opciones.get(0).getTexto());
		documentoPregunta.reemplazar("opcionB", opciones.get(1).getTexto());
		documentoPregunta.reemplazar("opcionC", opciones.get(2).getTexto());
		documentoPregunta.reemplazar("opcionD", opciones.get(3).getTexto());
	}
	
	private void establecerPesoOpcion(List<Opcion> opciones, Plantilla documentoPregunta) {
		documentoPregunta.reemplazar("pesoA", String.valueOf(opciones.get(0).getPeso()));
		documentoPregunta.reemplazar("pesoB", String.valueOf(opciones.get(1).getPeso()));
		documentoPregunta.reemplazar("pesoC", String.valueOf(opciones.get(2).getPeso()));
		documentoPregunta.reemplazar("pesoD", String.valueOf(opciones.get(3).getPeso()));
	}
	
	private void generarDocumento(List<Plantilla> totalDocumentos, Plantilla plantilla) {
		
		StringBuilder documento = new StringBuilder();
		
		for (Plantilla doc : totalDocumentos) {
			documento.append(doc.toString());
		}
		plantilla.reemplazar("documento", documento.toString());
	}
	
	private void exportarXML(String ruta, Plantilla plantilla) throws IOException {
		guardar(ruta, plantilla);
	}
	
	private void guardar(String ruta, Plantilla plantilla) throws IOException {
		FileWriter fw = new FileWriter(ruta);
		fw.write(plantilla.toString());
		fw.close();
	}

}
