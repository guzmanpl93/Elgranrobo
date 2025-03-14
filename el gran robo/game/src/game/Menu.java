package game;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
//    	Marco
        setTitle("El Gran Robo");
        setSize(1500,1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
//        Imagen de fondo
        JPanel panelFondo = new JPanel() {
            private Image fondo = new ImageIcon("imagenes/ElGranRobo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
//      panelFondo.setLayout(new BorderLayout());
        panelFondo.setLayout(new GridBagLayout());
        
//      Botones (panel)
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);
//        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        
//      Diseño botones
//        Dimension botonTamano = new Dimension(120, 40);
//        Font botonFuente = new Font("Arial", Font.BOLD, 16);
        
        JButton jugar = crearBoton("JUGAR");
        JButton info = crearBoton("INFO");
        JButton personajes = crearBoton("PERSONAJES");
        JButton save = crearBoton("SAVE");
        JButton exit = crearBoton("EXIT");
//      Boton para salir
        exit.addActionListener(e -> System.exit(0));
        
     // Configuración del GridBagLayout
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.fill = GridBagConstraints.CENTER; 
//        gbcBotones.weightx = 1; // Permite que crezcan horizontalmente
//        gbcBotones.weighty = 0.5; // Permite que crezcan verticalmente
        gbcBotones.insets = new Insets(15, 0, 15, 0); // Espaciado

        // Agregar botones al panel
        gbcBotones.gridy = 0;
        panelBotones.add(jugar, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(info, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(personajes, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(save, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(exit, gbcBotones);
        
//        Agregar al fondo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(-10, 700, 150, -50);
        
        panelFondo.add(panelBotones, gbc);

        setContentPane(panelFondo);
        
        }
    
//    Crear botones
    private JButton crearBoton(String texto) {
    	JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.RED);
                    g2.fillRoundRect(2, 2, getWidth() -4, getHeight() -4, 25, 25); // Redondez
              
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK); // Color del borde
                g2.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 25, 25); 
            }
        };
        
    	Dimension botonTamano = new Dimension(280, 70);
        boton.setPreferredSize(botonTamano);
        boton.setMaximumSize(botonTamano);
        boton.setMinimumSize(botonTamano);
    	boton.setFont(new Font("Arial", Font.BOLD, 34));
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setOpaque(false);
        return boton;
        
//        panelFondo.add(panelBotones, BorderLayout.EAST);
        
//        setContentPane(panelFondo); // Establecer el panel como contenido principal
//        
//        panelFondo.revalidate();
//        panelFondo.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }
}
