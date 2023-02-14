package es.ubu.inf.tfg.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ubu.inf.tfg.generador.preguntas.reglasasociacion.ConfigReglasAsociacion;
import es.ubu.inf.tfg.generador.preguntas.reglasasociacion.GeneradorBancoPreguntasReglasAsociacion;

import java.awt.*;
import java.util.ArrayList;

/**
 * Clase VentanaReglasAsociacion que representa la ventana principal de la aplicación encargada de 
 * configurar la pregunta de Generacion Reglas Asociacion.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class VentanaReglasAsociacion extends Ventana <ConfigReglasAsociacion> {

	/**
	 * Identificador único para la serialización de la clase.
	 */	
	private static final long serialVersionUID = 1L;

	/**
	 * Etiqueta para la descripción del spinner numPreguntasSpinner.
	 */
	private JLabel numPreguntasEtiqueta;

	/**
	 * Etiqueta para la descripción del spinner numAtributosSpinner.
	 */
	private JLabel numAtributosEtiqueta;

	/**
	 * Etiqueta para la descripcion del spinner numInstanciasSpinner.
	 */
	private JLabel numInstanciasEtiqueta;

	/**
	 * Etiqueta para la descripcion del spinner soporteEtiqueta.
	 */
	private JLabel soporteEtiqueta;

	/**
	 * Etiqueta para la descripcion del spinner confianzaEtiqueta.
	 */
	private JLabel confianzaEtiqueta;

	/**
	 * Etiqueta para la descripcion del spinner atrDiscretosEtiqueta.
	 */
	private JLabel atrDiscretosEtiqueta;

	/**
	 * Etiqueta para la descripcion del spinner numIntervalosEtiqueta.
	 */
	private JLabel numIntervalosEtiqueta = null;

	/**
	 *  Spinner que permite seleccionar el número de preguntas a generar.
	 */
	private JSpinner numPreguntasSpinner;

	/**
	 *  Spinner que permite seleccionar el número de atributos a generar.
	 */
	private JSpinner numAtributosSpinner;

	/**
	 *  Spinner que permite seleccionar el número de instancias a generar.
	 */
	private JSpinner numInstanciasSpinner;

	/**
	 *  Spinner que permite seleccionar los valores del soporte.
	 */
	private JSpinner soporteSpinner;

	/**
	 *  Spinner que permite seleccionar los valores de la confianza.
	 */
	private JSpinner confianzaSpinner;

	/**
	 *  Spinner que permite seleccionar si se quieren atributos numericos o no.
	 */
	private JSpinner atrDiscretosSpinner;

	/**
	 * Spinner que permite seleccionar el numero de intervalos.
	 */
	private JSpinner numIntervalosSpinner = null;

	/**
	 * Maximo numero de preguntas a generar permitido.
	 */
	private static final int maxNumPreguntas = 100;

	/**
	 * Minimo numero de atributos permitido.
	 */
	private static final int minNumAtr = 4;

	/**
	 * Maximo numero de atributos permitido.
	 */
	private static final int maxNumAtr = 10;

	/**
	 * Minimo numero de instancias permitido.
	 */
	private static final int minNumInstancias = 10;

	/**
	 * Maximo numero de instancias permitido.
	 */
	private static final int maxNumInstancias = 20;

	/**
	 * Minimo soporte permitido.
	 */
	private static final double minSoporte = 0.3;

	/**
	 * Maximo soporte permitido.
	 */
	private static final double maxSoporte = 0.6;

	/**
	 * Minimo numero de intervalos permitido.
	 */
	private static final int minNumIntervalos = 2;

	/**
	 * Maximo numero de intervalos permitido.
	 */
	private static final int maxNumIntervalos = 4;

	/**
	 * Constructor de la clase que inicializa los componentes graficos.
	 */
	public VentanaReglasAsociacion() {
		super(new JButton("Exportar XML"), new GeneradorBancoPreguntasReglasAsociacion());

		setLayout(new GridLayout(8, 2, 10, 10));
		numPreguntasEtiqueta = new JLabel("Numero de preguntas:");
		add(numPreguntasEtiqueta);
		numPreguntasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, maxNumPreguntas, 1));
		add(numPreguntasSpinner);

		numAtributosEtiqueta = new JLabel("Numero de atributos:");
		add(numAtributosEtiqueta);
		ArrayList<Object> opcionesNumAtr = new ArrayList<>();
		opcionesNumAtr.add("Aleatorio");
		for (int i = minNumAtr; i <= maxNumAtr; i++) {
			opcionesNumAtr.add(i);
		}
		numAtributosSpinner = new JSpinner(new SpinnerListModel(opcionesNumAtr));
		add(numAtributosSpinner);

		numInstanciasEtiqueta = new JLabel("Numero de instancias:");
		add(numInstanciasEtiqueta);
		ArrayList<Object> opcionesNumIns = new ArrayList<>();
		opcionesNumIns.add("Aleatorio");
		for (int i = minNumInstancias; i <= maxNumInstancias; i++) {
			opcionesNumIns.add(i);
		}
		numInstanciasSpinner = new JSpinner(new SpinnerListModel(opcionesNumIns));
		add(numInstanciasSpinner);

		soporteEtiqueta = new JLabel("Soporte:");
		add(soporteEtiqueta);
		ArrayList<Object> opcionesNumSop = new ArrayList<>();
		opcionesNumSop.add("Aleatorio");
		for (double i = minSoporte; i <= maxSoporte; i += 0.1) {
			opcionesNumSop.add(i);
		}
		soporteSpinner = new JSpinner(new SpinnerListModel(opcionesNumSop));
		add(soporteSpinner);

		confianzaEtiqueta = new JLabel("Confianza:");
		add(confianzaEtiqueta);
		ArrayList<Object> opcionesNumCon = new ArrayList<>();
		opcionesNumCon.add("Aleatorio");

		opcionesNumCon.add(0.6);
		opcionesNumCon.add(0.7);
		opcionesNumCon.add(0.8);
		opcionesNumCon.add(0.9);
		opcionesNumCon.add(1.0);

		confianzaSpinner = new JSpinner(new SpinnerListModel(opcionesNumCon));
		add(confianzaSpinner);

		atrDiscretosEtiqueta = new JLabel("¿Quieres que haya atributos numéricos?");
		add(atrDiscretosEtiqueta);
		ArrayList<String> opcionesAtributos = new ArrayList<>();
		opcionesAtributos.add("Aleatorio");
		opcionesAtributos.add("Sí");
		opcionesAtributos.add("No");
		atrDiscretosSpinner = new JSpinner(new SpinnerListModel(opcionesAtributos));

		atrDiscretosSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(atrDiscretosSpinner.getValue().equals("Sí")) {
					numIntervalosEtiqueta = new JLabel("Numero de intervalos:");
					add(numIntervalosEtiqueta);
					ArrayList<Object> opcionesNumInt = new ArrayList<>();
					opcionesNumInt.add("Aleatorio");
					for (int i = minNumIntervalos; i <= maxNumIntervalos; i++) {
						opcionesNumInt.add(i);
					}
					numIntervalosSpinner = new JSpinner(new SpinnerListModel(opcionesNumInt));
					add(numIntervalosSpinner);
				} else {
					remove(numIntervalosEtiqueta);
					remove(numIntervalosSpinner);
				}
				revalidate();
				repaint();
			}
		});

		add(atrDiscretosSpinner);
	}

	/**
	 * Este método obtiene la configuración actual de la ventana de Generacion Item Sets.
	 * @return Una instancia de ConfigReglasAsociacion que representa la configuración actual.
	 */
	@Override
	public ConfigReglasAsociacion obtenerConfiguracion() {
		int numPreguntas = (Integer) numPreguntasSpinner.getValue();

		int numAtributos = 0;
		if (!numAtributosSpinner.getValue().equals("Aleatorio")) {
			numAtributos = (Integer) numAtributosSpinner.getValue();
		} 

		int numInstancias = 0;
		if (!numInstanciasSpinner.getValue().equals("Aleatorio")) {
			numInstancias = (Integer) numInstanciasSpinner.getValue();
		}

		Double soporte = (double) 0;
		if (!soporteSpinner.getValue().equals("Aleatorio")) {
			soporte = (Double) soporteSpinner.getValue();
		}

		Double confianza = (double) 0;
		if (!confianzaSpinner.getValue().equals("Aleatorio")) {
			confianza = (Double) confianzaSpinner.getValue();
		}

		char tieneDiscretos = 'n';
		int numIntervalos = 0;

		if(atrDiscretosSpinner.getValue().equals("Sí")) {
			tieneDiscretos = 's';
			if (!numIntervalosSpinner.getValue().equals("Aleatorio")) {
				numIntervalos = (Integer) numIntervalosSpinner.getValue();
			}
		}

		if (atrDiscretosSpinner.getValue().equals("Aleatorio")) {
			tieneDiscretos = ' ';
		}

		return new ConfigReglasAsociacion(numPreguntas, numAtributos, numInstancias, soporte, confianza, tieneDiscretos, numIntervalos);
	}	
}






