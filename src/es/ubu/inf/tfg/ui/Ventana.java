package es.ubu.inf.tfg.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
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
import javax.swing.JProgressBar;
import javax.swing.JTextField;

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

		JLabel nombreFicheroEtiqueta = new JLabel("Nombre del fichero:");
		add(nombreFicheroEtiqueta);

		JTextField nombreFichero = new JTextField(20);
		add(nombreFichero);

		exportarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nombreFichero.getText().isEmpty()){
					JOptionPane.showMessageDialog(Ventana.this, "Ingrese un nombre para el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(Ventana.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File archivo = fileChooser.getSelectedFile();
					String nombre = nombreFichero.getText();
					final String ruta = archivo.getAbsolutePath() + "\\" + nombre + ".xml";

					File file = new File(ruta);

					try {
						file.createNewFile();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(Ventana.this, "Error al crear el archivo: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

					JDialog dialog = new JDialog(Ventana.this, "Generando preguntas", Dialog.ModalityType.APPLICATION_MODAL);
					JLabel label = new JLabel("Generando preguntas");
					JProgressBar progressBar = new JProgressBar();
					progressBar.setIndeterminate(true);
					dialog.add(label, BorderLayout.NORTH);
					dialog.add(progressBar, BorderLayout.CENTER);
					dialog.pack();
					dialog.setLocationRelativeTo(Ventana.this);
					dialog.setVisible(true);

					Thread t = new Thread(new Runnable() {
						public void run() {
							BancoPreguntas bancoPreguntas = gbp.generarBancoPreguntas(obtenerConfiguracion());
							try {
								Traductor traductor = new TraductorXML(ruta);
								traductor.traducir(bancoPreguntas);
							} catch (IOException e2) {
								JOptionPane.showMessageDialog(Ventana.this, "Error al exportar el archivo: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
							dialog.setVisible(false);
						}
					});
					t.start();

					JOptionPane.showMessageDialog(Ventana.this, "Exportación finalizada");
				}            
			}
		});
	}
}
