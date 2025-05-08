package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Partida extends JFrame {
    public Partida(ArrayList<String> equipos) {
        setTitle("Partida - El Gran Robo");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel fondo = new JPanel() {
        	private Image fondo = new ImageIcon("imagenes/mazmorra.png").getImage();
        	@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setBackground(null);
        fondo.setLayout(null);
//        panelFondo.setLayout(new FlowLayout());
//        panelFondo.setOpaque(false);
        

        JButton botonCombate = new JButton("Iniciar Combate");
        botonCombate.addActionListener(e -> {
            new Combate(equipos).setVisible(true);
            dispose();
        });

        fondo.add(botonCombate);
        setContentPane(fondo);
    }
}

