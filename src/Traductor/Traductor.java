package Traductor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
//import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import PosiblesItemSets.Solucion;
//import es.ubu.inf.tfg.doc.datos.Plantilla;

public class Traductor {

	Plantilla plantillaFinal = new Plantilla("plantilla.xml");
	Plantilla plantilla;
	List<Plantilla> totalPlantillas = new ArrayList<Plantilla>();

	int numPregunta = 1; 
	
	public Traductor() {
		// TODO Auto-generated constructor stub
	}
	
	public void generarPlantillas(List<Solucion> totalProblemas) {
		for (Solucion s : totalProblemas) {
			totalPlantillas.add(traduceProblemaPosiblesItemSets(s));
		}
		generarDocumento();
	}
	
	public void exportarXML(String ruta) throws IOException {

		guardar(ruta, plantillaFinal);
	}
	
	private void generarDocumento() {
		
		StringBuilder documento = new StringBuilder();
		
		for (Plantilla plantilla : totalPlantillas) {
			documento.append(plantilla.toString());
		}
		plantillaFinal.set("documento", documento.toString());
	}
	
	public Plantilla traduceProblemaPosiblesItemSets(Solucion problema) {
		plantilla = new Plantilla("plantillaPosiblesItemSets.xml");

		List<List<Character>> alternativas = problema.getOpciones();
		plantilla.set("numero", String.valueOf(numPregunta));
		plantilla.set("conjunto", problema.getTotalConjuntos().toString());
		plantilla.set("tamItemSets", String.valueOf(problema.getTotalConjuntos().get(0).size()));
		plantilla.set("tamItemSets+1", String.valueOf(problema.getTotalConjuntos().get(0).size()+1));
		
		establecerPuntuaciones(problema);
		
		plantilla.set("opcionA", alternativas.get(0).toString());
		plantilla.set("opcionB", alternativas.get(1).toString());
		plantilla.set("opcionC", alternativas.get(2).toString());
		plantilla.set("opcionD", alternativas.get(3).toString());
		
		numPregunta++;
		
		return plantilla;
	}
	
	private void establecerPuntuaciones(Solucion problema) {
		switch(problema.getNumOpcionesVerdaderas()) {
		case 1:
			plantilla.set("puntuacionA", "100");
			plantilla.set("puntuacionB", "-33.3333333");
			plantilla.set("puntuacionC", "-33.3333333");
			plantilla.set("puntuacionD", "-33.3333333");
			break;
		case 2:
			plantilla.set("puntuacionA", "50");
			plantilla.set("puntuacionB", "50");
			plantilla.set("puntuacionC", "-50");
			plantilla.set("puntuacionD", "-50");
			break;
		case 3:
			plantilla.set("puntuacionA", "33.3333333");
			plantilla.set("puntuacionB", "33.3333333");
			plantilla.set("puntuacionC", "33.3333333");
			plantilla.set("puntuacionD", "-100");
			break;
		case 4:
			plantilla.set("puntuacionA", "25");
			plantilla.set("puntuacionB", "25");
			plantilla.set("puntuacionC", "25");
			plantilla.set("puntuacionD", "25");
			break;
		}
	}
	
	private void guardar(String ruta, Plantilla plantilla) throws IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(ruta), "UTF8"))) {
			writer.write(plantilla.toString());
		}
	}

}
