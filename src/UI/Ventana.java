package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class Ventana extends JFrame {
	public JPanel portada;
	public JPanel panel;

	public Ventana() {
		setSize(500,600);
//		setBounds(100,200,500,500);
//		setLocation(0,0);
		setLocationRelativeTo(null);
		setTitle("Generador de preguntas sobre reglas de asociación");
		iniciarComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void iniciarComponentes() {
		colocarComponentes();
		colocarPortada();
		colocarBotones();
//		colocarRadioBotones();
//		colocarListasDesplegables();
	}
	
	private void colocarComponentes() {
		portada = new JPanel();
		portada.setLayout(null);
		this.getContentPane().add(portada);
//		panel = new JPanel();
//		panel.setLayout(null);
//		this.getContentPane().add(panel);
	}
	
	private void colocarPortada() {
		
		JLabel titulo = new JLabel();
		String texto = "<html><body><center> GENERADOR DE PREGUNTAS <br> SOBRE REGLAS DE ASOCIACIÓN <center><body><html>";
		titulo.setText(texto);
		titulo.setBounds(85,10,300,80);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.BLACK);
		titulo.setFont(new Font("cooper black",0,20));
		portada.add(titulo);
		
		ImageIcon imagen = new ImageIcon("Portada.jpg");
		JLabel etiquetaPortada = new JLabel();
		etiquetaPortada.setText("Generador de preguntas sobre reglas de asociación");
		etiquetaPortada.setBounds(90,90,300,300);
		etiquetaPortada.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiquetaPortada.getWidth(), etiquetaPortada.getHeight(), Image.SCALE_SMOOTH)));
		portada.add(etiquetaPortada);
		
	}
	
	private void colocarBotones() {
		colocarBotonPortada();
	}
	
	private void colocarBotonPortada() {
		JButton botonPortada = new JButton();
		botonPortada.setText("Continuar");
		botonPortada.setBounds(200,400,100,40);
		botonPortada.setEnabled(true);
		botonPortada.setMnemonic('a'); //alt + letra
		botonPortada.setForeground(Color.BLACK);
//		boton1.setFont(new Font());
		portada.add(botonPortada);
		
		ActionListener botonPortadaAL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {

			}
		};
		
		botonPortada.addActionListener(botonPortadaAL);
		
		
		
	}
	
	private void colocarRadioBotones() {
		JRadioButton radioBoton1 = new JRadioButton("Opcion 1", true);
		radioBoton1.setBounds(50,100,100,50);
		radioBoton1.setText("Opcion a");
		panel.add(radioBoton1);
		
		JRadioButton radioBoton2 = new JRadioButton("Opcion 2", false);
		radioBoton2.setBounds(50,150,100,50);
		radioBoton2.setText("Opcion b");
		panel.add(radioBoton2);
		
		JRadioButton radioBoton3 = new JRadioButton("Opcion 3", false);
		radioBoton3.setBounds(50,200,100,50);
		radioBoton3.setText("Opcion c");
		panel.add(radioBoton3);
		
		ButtonGroup grupoRadioBotones = new ButtonGroup();
		grupoRadioBotones.add(radioBoton1);
		grupoRadioBotones.add(radioBoton2);
		grupoRadioBotones.add(radioBoton3);

	}
	
	private void colocarListasDesplegables() {
		String [] tiposPreguntas = {"Generación item-sets"};
		
		JComboBox desplegablePreguntas = new JComboBox(tiposPreguntas);
		desplegablePreguntas.setBounds(20,20,200,30);
		panel.add(desplegablePreguntas);
	}

}
