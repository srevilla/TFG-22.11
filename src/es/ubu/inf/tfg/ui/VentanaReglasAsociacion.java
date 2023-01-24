package es.ubu.inf.tfg.ui;

import javax.swing.*;

import es.ubu.inf.tfg.generador.reglasasociacion.ConfigReglasAsociacion;
import es.ubu.inf.tfg.generador.reglasasociacion.GeneradorBancoPreguntasReglasAsociacion;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VentanaReglasAsociacion extends Ventana <ConfigReglasAsociacion> {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    private JLabel numPreguntasEtiqueta;
    private JLabel numAtributosEtiqueta;
    private JLabel numInstanciasEtiqueta;
    private JLabel soporteEtiqueta;
    private JLabel confianzaEtiqueta;
    private JLabel atrDiscretosEtiqueta;
	private JLabel numIntervalosEtiqueta = null;
	
	private JSpinner numPreguntasSpinner;
	private JSpinner numAtributosSpinner;
	private JSpinner numInstanciasSpinner;
    private JSpinner soporteSpinner;
    private JSpinner confianzaSpinner;
    private JCheckBox atrDiscretos;
    private JSpinner numIntervalosSpinner = null;
    

    public VentanaReglasAsociacion() {
        super(new JButton("Exportar XML"), new GeneradorBancoPreguntasReglasAsociacion());
        
        setLayout(new GridLayout(9, 2, 10, 10));
        numPreguntasEtiqueta = new JLabel("Numero de preguntas:");
        add(numPreguntasEtiqueta);
        numPreguntasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        add(numPreguntasSpinner);
        
        numAtributosEtiqueta = new JLabel("Numero de atributos:");
        add(numAtributosEtiqueta);
        numAtributosSpinner = new JSpinner(new SpinnerNumberModel(4, 4, 10, 1));
        add(numAtributosSpinner);
        
        numInstanciasEtiqueta = new JLabel("Numero de instancias:");
        add(numInstanciasEtiqueta);
        numInstanciasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        add(numInstanciasSpinner);
        
        soporteEtiqueta = new JLabel("Soporte:");
        add(soporteEtiqueta);
        soporteSpinner = new JSpinner(new SpinnerNumberModel(0.3, 0.3, 0.6, 0.1));
        add(soporteSpinner);
        
        confianzaEtiqueta = new JLabel("Confianza:");
        add(confianzaEtiqueta);
        confianzaSpinner = new JSpinner(new SpinnerNumberModel(0.6, 0.6, 1.0, 0.1));
        add(confianzaSpinner);
        
        atrDiscretosEtiqueta = new JLabel("¿Quieres que haya atributos discretos?");
        add(atrDiscretosEtiqueta);
        
        atrDiscretos = new JCheckBox();
        atrDiscretos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
                    numIntervalosEtiqueta = new JLabel("Numero de intervalos:");
                    add(numIntervalosEtiqueta);
                    numIntervalosSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 4, 1));
                    add(numIntervalosSpinner);
                } else {
                    remove(numIntervalosEtiqueta);
                    remove(numIntervalosSpinner);
                }
                revalidate();
                repaint();
            }
        });
        add(atrDiscretos);
    }
    
	@Override
	public ConfigReglasAsociacion obtenerConfiguracion() {
 	    int numPreguntas = (Integer) numPreguntasSpinner.getValue();
 	    int numAtributos = (Integer) numAtributosSpinner.getValue();
 	    int numInstancias = (Integer) numInstanciasSpinner.getValue();
        Double soporte = (Double) soporteSpinner.getValue();
        Double confianza = (Double) confianzaSpinner.getValue();
        boolean tieneDiscretos = false;
        int numIntervalos = 0;
        
        if(atrDiscretos.isSelected()) {
        	tieneDiscretos = true;
        	numIntervalos = (Integer) numIntervalosSpinner.getValue();
        }

		return new ConfigReglasAsociacion(numPreguntas, numAtributos, numInstancias, soporte, confianza, tieneDiscretos, numIntervalos);
	}	
}






