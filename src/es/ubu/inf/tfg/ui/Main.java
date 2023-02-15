package es.ubu.inf.tfg.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase Main que extiende de JFrame e implementa el metodo main para crear la interfaz grafica 
 * de usuario de la aplicacion.
 * 
 * @author Sergio Revilla Merino
 * @version 1.0.0
 * @since 2023
 */
public class Main extends JFrame {

	/**
	 * Atributo para identificar la clase en la serializacion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Boton de Generacion Item Sets
	 */
	private JButton boton1;

	/**
	 * Boton de Generacion Reglas Asociacion
	 */
	private JButton boton2;

	/**
	 * Boton de Ampliacion Item Sets
	 */
	private JButton boton3;

	/**
	 * Método principal que ejecuta la aplicación y crea la ventana principal.
	 * @param args - Arguemtos del programa
	 */
	public static void main(String[] args) {
		Main vp = new Main();
		vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vp.setSize(300, 300);
		vp.setLayout(new BorderLayout());
		vp.add(vp.createToolBar(), BorderLayout.NORTH);
		vp.add(vp.createButtons(), BorderLayout.CENTER);
		vp.setVisible(true);
	}

	/**
	 * Constructor principal de la clase Main
	 * Inicializa la ventana del menú principal con los botones "Generación Item Sets", "Generación Reglas de Asociación" y "Ampliación Item Sets"
	 * y sus respectivos eventos de acción que crean las ventanas secundarias correspondientes
	 */
	public Main() {
		super("Menu principal");
		setLayout(new FlowLayout());
		boton3 = new JButton("Ampliación Item Sets");
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAmpliacionItemSets v3 = new VentanaAmpliacionItemSets();
				v3.setVisible(true);
				v3.setSize(500, 300);
			}
		});

		boton2 = new JButton("Generación Reglas de Asociación");
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReglasAsociacion v2 = new VentanaReglasAsociacion();
				v2.setVisible(true);
				v2.setSize(500, 300);
			}
		});

		boton1 = new JButton("Generación Item Sets");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaItemSets v1 = new VentanaItemSets();
				v1.setVisible(true);
				v1.setSize(500, 300);
			}
		});
	}

	/**
	 * Crea una barra de herramientas con un botón "Acerca de".
	 * Al hacer clic en el botón se muestra un mensaje con información sobre la aplicación.
	 * 
	 * @return la barra de herramientas creada
	 */
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton acercaDeBoton = new JButton("Acerca de");
		acercaDeBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Main.this, "AssoQuiz-Generator\r\n"
						+ "\r\n"
						+ "GPL-3.0 License\r\n"
						+ "Copyright (c) 2023 Sergio Revilla\r\n"
						+ "\r\n"
						+ "Desarrollo de una aplicación para la Universidad de Burgos que\r\n"
						+ "consta de una interfaz gráfica en la que el usuario debe de seleccionar\r\n"
						+ "entre unos tipos de preguntas a generar, configurando unos parámetros\r\n"
						+ "diferentes en cada una. La aplicación genera un archivo en formato\r\n"
						+ ".XML para ser importado en la plataforma Moodle con la finalidad de\r\n"
						+ "generar de forma automática cuestionarios sobre el tema de reglas de asociación\r\n"
						+ "para la asignatura de minería de datos.");
			}
		});
		toolBar.add(acercaDeBoton);
		return toolBar;
	}

	/**
	 * Crea un panel con los botones de la aplicación.
	 * 
	 * @return un JPanel que contiene los botones de la aplicación.
	 */
	private JPanel createButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));
		buttonPanel.add(boton1);
		buttonPanel.add(boton2);
		buttonPanel.add(boton3);
		return buttonPanel;
	}

}
