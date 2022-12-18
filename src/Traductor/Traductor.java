package Traductor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
//import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import PosiblesItemSets.Solucion;

public class Traductor {

	public Traductor() {
		// TODO Auto-generated constructor stub
	}
	
	public void exportarXML(Solucion problema) throws IOException {

		String ruta = "C:\\Users\\revi\\Desktop\\TFG 2.0\\PreguntaMoodle.xml";

		guardar(ruta, traduceProblemaPosiblesItemSets(problema));
	}
	
	public Plantilla traduceProblemaPosiblesItemSets(Solucion problema) {
		Plantilla plantilla = new Plantilla("plantillaPosiblesItemSets.xml");

		List<List<Character>> alternativas = problema.getOpciones();

		plantilla.set("numero", "16");
		plantilla.set("conjunto", problema.getTotalConjuntos().toString());
		plantilla.set("tamItemSets", String.valueOf(problema.getTotalConjuntos().get(0).size()));
		plantilla.set("tamItemSets+1", String.valueOf(problema.getTotalConjuntos().get(0).size()+1));
		plantilla.set("opcionA", alternativas.get(2).toString());
		plantilla.set("opcionB", alternativas.get(3).toString());
		plantilla.set("opcionC", alternativas.get(0).toString());
		plantilla.set("opcionD", alternativas.get(1).toString());
		
		return plantilla;
	}
	
	private void guardar(String ruta, Plantilla plantilla) throws IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(ruta), "UTF8"))) {
			writer.write(plantilla.toString());
		}
	}

}
