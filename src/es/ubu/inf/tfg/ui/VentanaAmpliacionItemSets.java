package es.ubu.inf.tfg.ui;

import javax.swing.*;

import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.ConfigAmpliacionItemSets;
import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.GeneradorBancoPreguntasAmpliacionItemSets;

import java.awt.*;
import java.util.*;

public class VentanaAmpliacionItemSets extends Ventana <ConfigAmpliacionItemSets> {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JSpinner numPreguntasSpinner;
    private JSpinner numItemSetsSpinner;
    private JSpinner tamItemSetsSpinner;
    private JLabel numPreguntasLabel;
    private JLabel numItemSetsLabel;
    private JLabel tamItemSetsLabel;
    
    private static final int maxNumPreguntas = 100;
	private static final int minNumItemSets = 10;
	private static final int maxNumItemSets = 16;
	private static final int minTamItemSets = 3;
	private static final int maxTamItemSets = 4;

    public VentanaAmpliacionItemSets() {
        super(new JButton("Exportar XML"), new GeneradorBancoPreguntasAmpliacionItemSets());
        
        setLayout(new GridLayout(4, 2, 10, 10));
        numPreguntasLabel = new JLabel("Numero de preguntas:");
        add(numPreguntasLabel);
        numPreguntasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, maxNumPreguntas, 1));
        add(numPreguntasSpinner);
        
        numItemSetsLabel = new JLabel("Numero de ItemSets:");
        add(numItemSetsLabel);
        ArrayList<Object> opcionesNum = new ArrayList<>();
        opcionesNum.add("Aleatorio");
        for (int i = minNumItemSets; i <= maxNumItemSets; i++) {
        	opcionesNum.add(i);
        }
        numItemSetsSpinner = new JSpinner(new SpinnerListModel(opcionesNum));
        add(numItemSetsSpinner);
        
        tamItemSetsLabel = new JLabel("Tamaño de ItemSets:");
        add(tamItemSetsLabel);
        ArrayList<Object> opcionesTam = new ArrayList<>();
        opcionesTam.add("Aleatorio");
        for (int i = minTamItemSets; i <= maxTamItemSets; i++) {
        	opcionesTam.add(i);
        }
        tamItemSetsSpinner = new JSpinner(new SpinnerListModel(opcionesTam));
        add(tamItemSetsSpinner);
      
    }
    
	@Override
	public ConfigAmpliacionItemSets obtenerConfiguracion() {
 	    int numPreguntas = (Integer) numPreguntasSpinner.getValue();
 	    
 	    int numItemSets;
        if (numItemSetsSpinner.getValue().equals("Aleatorio")) {
        	numItemSets = 0;
        } else {
            numItemSets = (Integer) numItemSetsSpinner.getValue();
        }

        int tamItemSets;
        if (tamItemSetsSpinner.getValue().equals("Aleatorio")) {
        	tamItemSets = 0;
        } else {
            tamItemSets = (Integer) tamItemSetsSpinner.getValue();
        }

		return new ConfigAmpliacionItemSets(numPreguntas, numItemSets, tamItemSets);
	}	
}





