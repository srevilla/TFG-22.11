package es.ubu.inf.tfg.ui;

import javax.swing.*;

import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.ConfigAmpliacionItemSets;
import es.ubu.inf.tfg.generador.preguntas.ampliacionitemsets.GeneradorBancoPreguntasAmpliacionItemSets;

import java.awt.*;
import java.util.*;

/**
 * Clase VentanaAmpliacionItemSets que representa la ventana principal de la aplicación encargada de 
 * configurar la pregunta de Ampliación Item Sets.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class VentanaAmpliacionItemSets extends Ventana <ConfigAmpliacionItemSets> {

	/**
	 * Identificador único para la serialización de la clase.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 *  Spinner que permite seleccionar el número de preguntas a generar.
	 */
	private JSpinner numPreguntasSpinner;

	/**
	 * Spinner que permite seleccionar el número de item sets a generar o si se desea que sea aleatorio.
	 */
	private JSpinner numItemSetsSpinner;

	/**
	 * Spinner que permite seleccionar el tamaño de los item sets a generar o si se desea que sea aleatorio.
	 */
	private JSpinner tamItemSetsSpinner;

	/**
	 * Etiqueta para la descripción del spinner numPreguntasSpinner.
	 */
	private JLabel numPreguntasLabel;

	/**
	 * Etiqueta para la descripción del spinner numItemSetsSpinner.
	 */
	private JLabel numItemSetsLabel;

	/**
	 * Etiqueta para la descripción del spinner tamItemSetsSpinner.
	 */
	private JLabel tamItemSetsLabel;

	/**
	 * Maximo numero de preguntas a generar permitido.
	 */
	private static final int maxNumPreguntas = 100;

	/**
	 * Minimo numero de item sets permitido.
	 */
	private static final int minNumItemSets = 10;

	/**
	 * Maximo numero de item sets permitido.
	 */
	private static final int maxNumItemSets = 16;

	/**
	 * Minimo tamaño de item sets permitido.
	 */
	private static final int minTamItemSets = 3;

	/**
	 * Maximo tamaño de item sets permitido.
	 */
	private static final int maxTamItemSets = 4;

	/**
	 * Constructor de la clase que inicializa los componentes graficos.
	 */
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

	/**
	 * Este método obtiene la configuración actual de la ventana de ampliación de ItemSets.
	 * @return Una instancia de ConfigAmpliacionItemSets que representa la configuración actual.
	 */
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





