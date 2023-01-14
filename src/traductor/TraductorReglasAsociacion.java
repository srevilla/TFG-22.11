package traductor;

import java.text.DecimalFormat;
import java.util.List;

import preguntas.reglasAsociacion.SolucionReglasAsociacion;
import weka.associations.AssociationRule;

public class TraductorReglasAsociacion extends Traductor {
	
	public TraductorReglasAsociacion() {
		super();
	}

	public void generarPlantillas(List<SolucionReglasAsociacion> totalProblemas) {
		for (SolucionReglasAsociacion s : totalProblemas) {
			totalPlantillas.add(traduceProblema(s));
		}
		generarDocumento();
	}
	
	public Plantilla traduceProblema(SolucionReglasAsociacion problema) {
		plantilla = new Plantilla("plantillaReglasAsociacion.xml");
		DecimalFormat df = new DecimalFormat("#.##");

		List<String> alternativas = problema.getOpciones();
		plantilla.reemplazar("numero", String.valueOf(numPregunta));
		plantilla.reemplazar("conjunto", problema.getContenidoData());
		plantilla.reemplazar("soporte", String.valueOf(df.format(problema.getSoporte()*10)));
		plantilla.reemplazar("confianza", String.valueOf(Math.round(problema.getConfianza()*100)));
		establecerPuntuaciones(problema);
		
		plantilla.reemplazar("opcionA", alternativas.get(0).toString());
		plantilla.reemplazar("opcionB", alternativas.get(1).toString());
		plantilla.reemplazar("opcionC", alternativas.get(2).toString());
		plantilla.reemplazar("opcionD", alternativas.get(3).toString());
		
		numPregunta++;
		
		return plantilla;
	}
	
	private void establecerPuntuaciones(SolucionReglasAsociacion problema) {
		switch(problema.getNumOpcionesVerdaderas()) {
		case 1:
			plantilla.reemplazar("puntuacionA", "100");
			plantilla.reemplazar("puntuacionB", "-33.3333333");
			plantilla.reemplazar("puntuacionC", "-33.3333333");
			plantilla.reemplazar("puntuacionD", "-33.3333333");
			break;
		case 2:
			plantilla.reemplazar("puntuacionA", "50");
			plantilla.reemplazar("puntuacionB", "50");
			plantilla.reemplazar("puntuacionC", "-50");
			plantilla.reemplazar("puntuacionD", "-50");
			break;
		case 3:
			plantilla.reemplazar("puntuacionA", "33.3333333");
			plantilla.reemplazar("puntuacionB", "33.3333333");
			plantilla.reemplazar("puntuacionC", "33.3333333");
			plantilla.reemplazar("puntuacionD", "-100");
			break;
		case 4:
			plantilla.reemplazar("puntuacionA", "25");
			plantilla.reemplazar("puntuacionB", "25");
			plantilla.reemplazar("puntuacionC", "25");
			plantilla.reemplazar("puntuacionD", "25");
			break;
		}
	}
}
