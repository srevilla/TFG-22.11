package es.ubu.inf.tfg.ui;

import javax.swing.*;

import es.ubu.inf.tfg.generador.posiblesitemsets.ConfigPosiblesItemSets;
import es.ubu.inf.tfg.generador.posiblesitemsets.GeneradorBancoPreguntasPosiblesItemSets;

import java.awt.*;

public class VentanaPosiblesItemSets extends Ventana <ConfigPosiblesItemSets> {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JSpinner numPreguntasSpinner;
    private JSpinner numItemSetsSpinner;
    private JSpinner tamañoItemSetsSpinner;
    private JLabel numPreguntasLabel;
    private JLabel numItemSetsLabel;
    private JLabel tamañoItemSetsLabel;

    public VentanaPosiblesItemSets() {
        super(new JButton("Exportar XML"), new GeneradorBancoPreguntasPosiblesItemSets());
        
        setLayout(new GridLayout(6, 2, 10, 10));
        numPreguntasLabel = new JLabel("Numero de preguntas:");
        add(numPreguntasLabel);
        numPreguntasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        add(numPreguntasSpinner);
        numItemSetsLabel = new JLabel("Numero de ItemSets:");
        add(numItemSetsLabel);
        numItemSetsSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 20, 1));
        add(numItemSetsSpinner);
        tamañoItemSetsLabel = new JLabel("Tamaño de ItemSets:");
        add(tamañoItemSetsLabel);
        tamañoItemSetsSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 4, 1));
        add(tamañoItemSetsSpinner);
      
    }
    
	@Override
	public ConfigPosiblesItemSets obtenerConfiguracion() {
 	    int numPreguntas = (Integer) numPreguntasSpinner.getValue();
        int numItemSets = (Integer) numItemSetsSpinner.getValue();
        int tamItemSets = (Integer) tamañoItemSetsSpinner.getValue();

		return new ConfigPosiblesItemSets(numPreguntas, numItemSets, tamItemSets);
	}	
}





