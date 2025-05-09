package Game;

import javax.swing.*;

import Game.Menu;

import java.awt.*;
import java.util.ArrayList;

public class Partida extends JFrame {
    public Partida(ArrayList<String> equipos) {
        setTitle("Partida - El Gran Robo");
//        setSize(1500, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Background fondo = new Background("imagenes/mazmorra4.png");
        setContentPane(fondo);
        fondo.setLayout(null);
       
     // Tamaño pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;
     // === BOTÓN INICIAR COMBATE ===
        JButton botonCombate = new JButton("Iniciar Combate");
        botonCombate.setFont(new Font("Arial", Font.BOLD, 18));
        botonCombate.setForeground(Color.BLACK);
        botonCombate.setBackground(new Color(0, 0, 0, 50)); // semi-transparente
        botonCombate.setFocusPainted(false);
        botonCombate.setBounds(centerX - 100, centerY - 30, 200, 60);

        botonCombate.addActionListener(e -> {
            new Combate(equipos).setVisible(true);
            dispose();
        });
     // === BOTÓN VOLVER AL MENÚ ===
        ImageIcon iconoMenu = new ImageIcon("imagenes/menu2.png"); // Usa la imagen que subiste
        Image iconoRedimensionado = iconoMenu.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton botonMenu = new JButton(new ImageIcon(iconoRedimensionado));
        botonMenu.setToolTipText("Volver al Menú");
        botonMenu.setContentAreaFilled(false);
        botonMenu.setBorderPainted(false);
        botonMenu.setFocusPainted(false);
        botonMenu.setBounds(30, 30, 50, 50);

        botonMenu.addActionListener(e -> {
            new Menu().setVisible(true);
            dispose();
        });

     // === BOTÓN SALIR ===
        ImageIcon iconoSalir = new ImageIcon("imagenes/salir1.png"); // Imagen para salir
        Image salirRedimensionado = iconoSalir.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton botonSalir = new JButton(new ImageIcon(salirRedimensionado));
        botonSalir.setToolTipText("Salir del juego");
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        botonSalir.setFocusPainted(false);
        botonSalir.setBounds(screenSize.width - 70, 30, 40, 40);

        botonSalir.addActionListener(e -> {
            System.exit(0);
        });
     
        // === AGREGAR BOTONES AL PANEL ===
        fondo.add(botonCombate);
        fondo.add(botonMenu);
        fondo.add(botonSalir);

        
        fondo.add(botonCombate);
        setVisible(true);
    }
   
}

