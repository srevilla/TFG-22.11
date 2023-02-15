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

/**
 * Clase abstracta Ventana que extiende de JFrame.
 * 
 * @param <T> - Tipo de configuración a utilizar para generar el banco de preguntas.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public abstract class Ventana <T> extends JFrame {

	/**
	 * Identificador único para la serialización de la clase.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método abstracto que obtiene la configuración para generar el banco de preguntas.
	 * 
	 * @return La configuración para generar el banco de preguntas.
	 */
	public abstract T obtenerConfiguracion();		

	/**
	 * Constructor de la clase Ventana.
	 * 
	 * @param exportarBoton - Botón utilizado para exportar el banco de preguntas.
	 * @param gbp - Generador de banco de preguntas que se utilizará para generar el banco de preguntas.
	 */
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
					    if (fileConExtension.exists()) {
					        int respuesta = JOptionPane.showConfirmDialog(Ventana.this, "El archivo ya existe. ¿Desea sobreescribirlo?", "Advertencia", JOptionPane.YES_NO_OPTION);
					        if (respuesta != JOptionPane.YES_OPTION) {
					            return;
					        }
					    }
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
