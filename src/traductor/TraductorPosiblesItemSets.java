package traductor;

import java.util.List;

import preguntas.posiblesItemSets.SolucionPosiblesItemSets;

public class TraductorPosiblesItemSets extends Traductor {
	
	public TraductorPosiblesItemSets () {
		super();
	}

	public void generarPlantillas(List<SolucionPosiblesItemSets> totalProblemas) {
		for (SolucionPosiblesItemSets s : totalProblemas) {
			totalPlantillas.add(traduceProblema(s));
		}
		generarDocumento();
	}
	
	public Plantilla traduceProblema(SolucionPosiblesItemSets problema) {
		plantilla = new Plantilla("plantillaPosiblesItemSets.xml");

		List<List<Character>> alternativas = problema.getOpciones();
		plantilla.reemplazar("numero", String.valueOf(numPregunta));
		plantilla.reemplazar("conjunto", problema.getTotalConjuntos().toString());
		plantilla.reemplazar("tamItemSets", String.valueOf(problema.getTotalConjuntos().get(0).size()));
		plantilla.reemplazar("tamItemSets+1", String.valueOf(problema.getTotalConjuntos().get(0).size()+1));
		
		establecerPuntuaciones(problema);
		
		plantilla.reemplazar("opcionA", alternativas.get(0).toString());
		plantilla.reemplazar("opcionB", alternativas.get(1).toString());
		plantilla.reemplazar("opcionC", alternativas.get(2).toString());
		plantilla.reemplazar("opcionD", alternativas.get(3).toString());
		
		numPregunta++;
		
		return plantilla;
	}
	
	private void establecerPuntuaciones(SolucionPosiblesItemSets problema) {
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
