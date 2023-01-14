package traductor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Traductor {

	Plantilla plantillaFinal = new Plantilla("plantilla.xml");
	Plantilla plantilla;
	List<Plantilla> totalPlantillas = new ArrayList<Plantilla>();

	int numPregunta = 1; 
	
	public Traductor() {
		// TODO Auto-generated constructor stub
	}
	
	public void exportarXML(String ruta) throws IOException {

		guardar(ruta, plantillaFinal);
	}
	
	protected void generarDocumento() {
		
		StringBuilder documento = new StringBuilder();
		
		for (Plantilla plantilla : totalPlantillas) {
			documento.append(plantilla.toString());
		}
		plantillaFinal.reemplazar("documento", documento.toString());
	}
	
	private void guardar(String ruta, Plantilla plantilla) throws IOException {
		FileWriter fw = new FileWriter(ruta);
		fw.write(plantilla.toString());
		fw.close();
	}
}
