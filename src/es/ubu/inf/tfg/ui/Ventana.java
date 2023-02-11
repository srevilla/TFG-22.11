package es.ubu.inf.tfg.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import es.ubu.inf.tfg.dominio.BancoPreguntas;
import es.ubu.inf.tfg.generador.GeneradorBancoPreguntas;
import es.ubu.inf.tfg.traductor.Traductor;
import es.ubu.inf.tfg.traductor.xml.TraductorXML;

public abstract class Ventana <T> extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public abstract T obtenerConfiguracion();		
	
	    public Ventana(JButton exportarBoton, GeneradorBancoPreguntas<T> gbp) {
	        add(exportarBoton);
	        add(new JLabel());

	        exportarBoton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                boolean exportacionExitosa = false;
	                JFileChooser fileChooser = new JFileChooser();
	                File file = null;

	                while (file == null) {
	                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	                    int returnVal = fileChooser.showSaveDialog(Ventana.this);

	                    if (returnVal == JFileChooser.APPROVE_OPTION) {
	                        file = fileChooser.getSelectedFile();

	                        if (file.getName().length() == 0) {
	                            JOptionPane.showMessageDialog(Ventana.this, "Debe introducir un nombre para el archivo", "Error", JOptionPane.ERROR_MESSAGE);
	                            file = null;
	                        }
	                    } else {
	                        break;
	                    }
	                }

	                if (file != null) {
	                    try {
	                        File fileConExtension = new File(file.getAbsolutePath() + ".xml");
	                        fileConExtension.createNewFile();
	                        
	                		// Mostrar la barra de progreso
	                		JOptionPane pane = new JOptionPane("Exportando");
	                		JDialog dialog = pane.createDialog(Ventana.this, "Exportando preguntas...");
	                		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	                		dialog.setModal(false);
	                		dialog.setVisible(true);
	                		
	                        BancoPreguntas bancoPreguntas = gbp.generarBancoPreguntas(obtenerConfiguracion());

	                        try {
	                            Traductor traductor = new TraductorXML(fileConExtension.getAbsolutePath());
	                            traductor.traducir(bancoPreguntas);
	                            exportacionExitosa = true;
	                        } catch (IOException e2) {
	                            JOptionPane.showMessageDialog(Ventana.this, "Error al exportar el archivo: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                        }
	                	dialog.setVisible(false);
	                } catch (IOException e1) {
	                    JOptionPane.showMessageDialog(Ventana.this, "Error al crear el archivo: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	            
	            if (exportacionExitosa) {
	                JOptionPane.showMessageDialog(Ventana.this, "Exportación finalizada");
	            }            	                   
			}            
		});
	}
}
