package es.ubu.inf.tfg.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;

    public static void main(String[] args) {
        Main vp = new Main();
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vp.setSize(300, 300);
        vp.setLayout(new BorderLayout());
        vp.add(vp.createToolBar(), BorderLayout.NORTH);
        vp.add(vp.createButtons(), BorderLayout.CENTER);
        vp.setVisible(true);
    }

    public Main() {
        super("Menu principal");
        setLayout(new FlowLayout());
        boton1 = new JButton("Ampliación Item Sets");
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaAmpliacionItemSets v1 = new VentanaAmpliacionItemSets();
                v1.setVisible(true);
                v1.setSize(500, 300);
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
        
        boton3 = new JButton("Generación Item Sets");
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaItemSets v3 = new VentanaItemSets();
                v3.setVisible(true);
                v3.setSize(500, 300);
            }
        });
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        JButton acercaDeBoton = new JButton("Acerca de");
        acercaDeBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Main.this, "MIT License\r\n"
                		+ "\r\n"
                		+ "Copyright (c) 2023 Sergio Revilla\r\n"
                		+ "\r\n"
                		+ "Permission is hereby granted, free of charge, to any person obtaining a copy\r\n"
                		+ "of this software and associated documentation files (the \"Software\"), to deal\r\n"
                		+ "in the Software without restriction, including without limitation the rights\r\n"
                		+ "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\r\n"
                		+ "copies of the Software, and to permit persons to whom the Software is\r\n"
                		+ "furnished to do so, subject to the following conditions:\r\n"
                		+ "\r\n"
                		+ "The above copyright notice and this permission notice shall be included in all\r\n"
                		+ "copies or substantial portions of the Software.\r\n"
                		+ "\r\n"
                		+ "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\r\n"
                		+ "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\r\n"
                		+ "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\r\n"
                		+ "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\r\n"
                		+ "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\r\n"
                		+ "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\r\n"
                		+ "SOFTWARE.");
            }
        });
        toolBar.add(acercaDeBoton);
        return toolBar;
    }

    private JPanel createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(boton1);
        buttonPanel.add(boton2);
        buttonPanel.add(boton3);
        return buttonPanel;
    }

}
