package es.ubu.inf.tfg.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JButton boton1;
    private JButton boton2;

    public static void main(String[] args) {
        Main vp = new Main();
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vp.setSize(300, 300);
        vp.setLayout(new GridLayout(2,1));
        vp.add(vp.boton1);
        vp.add(vp.boton2);
        vp.setVisible(true);
    }
    
    public Main() {
        super("Menu principal");
        setLayout(new FlowLayout());
        boton1 = new JButton("Posibles Item Sets");
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPosiblesItemSets v1 = new VentanaPosiblesItemSets();
                v1.setVisible(true);
                v1.setSize(500, 300);
                
            }
            
        });
		
        boton2 = new JButton("Reglas de Asociación");
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaReglasAsociacion v2 = new VentanaReglasAsociacion();
                v2.setVisible(true);
                v2.setSize(500, 300);
            }
        });
        add(boton1);
        add(boton2);
    }

}


