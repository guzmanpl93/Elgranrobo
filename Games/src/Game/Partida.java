package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Partida extends JFrame {
    public Partida(ArrayList<String> equipos) {
        setTitle("Partida - El Gran Robo");
        setSize(1360, 740);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel fondo = new JPanel() {
            protected void paintComponent(Graphics g) {
                Image img = new ImageIcon("imagenes/mazmorra.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setLayout(new FlowLayout());
        fondo.setOpaque(false);
        

        JButton botonCombate = new JButton("Iniciar Combate");
        botonCombate.addActionListener(e -> {
            new Combate(equipos).setVisible(true);
            dispose();
        });

        fondo.add(botonCombate);
        setContentPane(fondo);
    }
}

