package es.ubu.inf.tfg.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ubu.inf.tfg.generador.reglasasociacion.ConfigReglasAsociacion;
import es.ubu.inf.tfg.generador.reglasasociacion.GeneradorBancoPreguntasReglasAsociacion;

import java.awt.*;
import java.util.ArrayList;

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
    private JSpinner atrDiscretosSpinner;
    private JSpinner numIntervalosSpinner = null;
    
    private static final int maxNumPreguntas = 100;
    private static final int minNumAtr = 4;
    private static final int maxNumAtr = 10;
    private static final int minNumInstancias = 10;
    private static final int maxNumInstancias = 20;
    private static final double minSoporte = 0.3;
    private static final double maxSoporte = 0.6;
    private static final double minConfianza = 0.6;
    private static final double maxConfianza = 1.0;
    private static final int minNumIntervalos = 2;
    private static final int maxNumIntervalos = 4;
    

    public VentanaReglasAsociacion() {
        super(new JButton("Exportar XML"), new GeneradorBancoPreguntasReglasAsociacion());
        
        setLayout(new GridLayout(9, 2, 10, 10));
        numPreguntasEtiqueta = new JLabel("Numero de preguntas:");
        add(numPreguntasEtiqueta);
        numPreguntasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, maxNumPreguntas, 1));
        add(numPreguntasSpinner);
        
        numAtributosEtiqueta = new JLabel("Numero de atributos:");
        add(numAtributosEtiqueta);
        ArrayList<Object> opcionesNumAtr = new ArrayList<>();
        opcionesNumAtr.add(" ");
        for (int i = minNumAtr; i <= maxNumAtr; i++) {
        	opcionesNumAtr.add(i);
        }
        numAtributosSpinner = new JSpinner(new SpinnerListModel(opcionesNumAtr));
        add(numAtributosSpinner);
        
        numInstanciasEtiqueta = new JLabel("Numero de instancias:");
        add(numInstanciasEtiqueta);
        ArrayList<Object> opcionesNumIns = new ArrayList<>();
        opcionesNumIns.add(" ");
        for (int i = minNumInstancias; i <= maxNumInstancias; i++) {
        	opcionesNumIns.add(i);
        }
        numInstanciasSpinner = new JSpinner(new SpinnerListModel(opcionesNumIns));
        add(numInstanciasSpinner);
        
        soporteEtiqueta = new JLabel("Soporte:");
        add(soporteEtiqueta);
        ArrayList<Object> opcionesNumSop = new ArrayList<>();
        opcionesNumSop.add(" ");
        for (double i = minSoporte; i <= maxSoporte; i += 0.1) {
        	opcionesNumSop.add(i);
        }
        soporteSpinner = new JSpinner(new SpinnerListModel(opcionesNumSop));
        add(soporteSpinner);
        
        confianzaEtiqueta = new JLabel("Confianza:");
        add(confianzaEtiqueta);
        ArrayList<Object> opcionesNumCon = new ArrayList<>();
        opcionesNumCon.add(" ");
//        for (double i = minConfianza; i <= maxConfianza; i += 0.1) {
//        	opcionesNumCon.add(i);
//        }
        opcionesNumCon.add(0.6);
        opcionesNumCon.add(0.7);
        opcionesNumCon.add(0.8);
        opcionesNumCon.add(0.9);
        opcionesNumCon.add(1.0);

        confianzaSpinner = new JSpinner(new SpinnerListModel(opcionesNumCon));
        add(confianzaSpinner);
        
        atrDiscretosEtiqueta = new JLabel("¿Quieres que haya atributos discretos?");
        add(atrDiscretosEtiqueta);
        ArrayList<String> opcionesAtributos = new ArrayList<>();
        opcionesAtributos.add(" ");
        opcionesAtributos.add("Sí");
        opcionesAtributos.add("No");
        atrDiscretosSpinner = new JSpinner(new SpinnerListModel(opcionesAtributos));

        atrDiscretosSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(atrDiscretosSpinner.getValue().equals("Sí")) {
                    numIntervalosEtiqueta = new JLabel("Numero de intervalos:");
                    add(numIntervalosEtiqueta);
                    ArrayList<Object> opcionesNumInt = new ArrayList<>();
                    opcionesNumInt.add(" ");
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
    
	@Override
	public ConfigReglasAsociacion obtenerConfiguracion() {
 	    int numPreguntas = (Integer) numPreguntasSpinner.getValue();
 	    
 	    int numAtributos = 0;
 	    if (!numAtributosSpinner.getValue().equals(" ")) {
 	    	numAtributos = (Integer) numAtributosSpinner.getValue();
 	    } 
 	    
 	    int numInstancias = 0;
 	    if (!numInstanciasSpinner.getValue().equals(" ")) {
 	    	numInstancias = (Integer) numInstanciasSpinner.getValue();
 	    }
 	    
 	    Double soporte = (double) 0;
 	    if (!soporteSpinner.getValue().equals(" ")) {
 	    	soporte = (Double) soporteSpinner.getValue();
 	    }
 	    
 	    Double confianza = (double) 0;
 	    if (!confianzaSpinner.getValue().equals(" ")) {
 	    	confianza = (Double) confianzaSpinner.getValue();
 	    }
 	    
        char tieneDiscretos = 'n';
        int numIntervalos = 0;
        
        if(atrDiscretosSpinner.getValue().equals("Sí")) {
        	tieneDiscretos = 's';
     	    if (!numIntervalosSpinner.getValue().equals(" ")) {
     	    	numIntervalos = (Integer) numIntervalosSpinner.getValue();
     	    }
        }
        
        if (atrDiscretosSpinner.getValue().equals(" ")) {
        	tieneDiscretos = ' ';
        }

		return new ConfigReglasAsociacion(numPreguntas, numAtributos, numInstancias, soporte, confianza, tieneDiscretos, numIntervalos);
	}	
}






