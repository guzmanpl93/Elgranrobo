package Game;

import java.awt.*;

import javax.swing.*;

import Game.Menu;

public class CargarPartida extends JFrame {
    public CargarPartida() {
        setTitle("Cargar Partida");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel botones = new JPanel();
        
//        CREAR BOTON VOLVER MENU
        botones.setOpaque(false);
        ImageIcon iconoOriginal1 = new ImageIcon("imagenes/menu.png");
        Image imagenEscalada1 = iconoOriginal1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // tamaño deseado
        JButton btnMenu = new JButton(new ImageIcon(imagenEscalada1));

        btnMenu.addActionListener(e -> {
            dispose();
            new Menu().setVisible(true);
        });
        botones.add(btnMenu);
        
//        BOTON CARGAR PARTIDA
        JButton cargar = new JButton("Cargar Última Partida");
        cargar.addActionListener(e -> {
            Partida partida = ConexionBD.cargarUltimaPartida();
            if (partida != null) {
                partida.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No hay partidas guardadas.");
            }
        });

        add(cargar, BorderLayout.CENTER);
    }
    
}
//public class CargarPartida extends JFrame {
//	private static final long serialVersionUID = 1L;
//    private JPanel contentPane;
//    private Component frame;
//
//	
//    CargarPartida() {
//		setTitle("Cargar Partida");
//	    setSize(1000, 768);
//	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    setLocationRelativeTo(null);
//	    setResizable(false);
//	}
//    
//	public class partidas {
//		
//	}
