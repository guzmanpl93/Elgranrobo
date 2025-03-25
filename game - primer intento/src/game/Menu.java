package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
	private GameState gameState;
	private Save gameManager;
	private static final String SAVE_FILE = "game_save.dat";

    public Menu() {
//    	Inializacion de la ventana
        setTitle("El Gran Robo");
        setSize(1500,1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        gameState = new GameState(); //estado inicial
        gameManager = new Save(gameState); //Crear instancia
        
//        Imagen de fondo
        JPanel panelFondo = new JPanel() {
            private Image fondo = new ImageIcon("imagenes/ElGranRobo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelFondo.setLayout(new GridBagLayout());
        
//      Panel de botones
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);
        
//      Diseño de los botones
        JButton jugar = crearBoton("JUGAR");
        jugar.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
        		//al hacer clic, abrir la ventana del juego
        		new Jugar().setVisible(true);
        		setVisible(false); //Ocultar la ventana de menu
        	}
        });
        JButton info = crearBoton("INFO");
        JButton personajes = crearBoton("PERSONAJES");
        JButton save = crearBoton("SAVE");
        save.addActionListener(e -> gameManager.saveGame());
        JButton load = crearBoton("LOAD");
        load.addActionListener(e -> gameManager.loadGame());
        JButton exit = crearBoton("EXIT");
        exit.addActionListener(e -> System.exit(0));
        
     // Configuración del GridBagLayout para los botones
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.fill = GridBagConstraints.CENTER; 
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
        panelBotones.add(load, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(exit, gbcBotones);
        
//        Agregar el panel de los botones al fondo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(-10, 700, 150, -50);
        
        panelFondo.add(panelBotones, gbc);

        setContentPane(panelFondo);
        
        }
    
//    Crear los botones con diseño personalizado
    private JButton crearBoton(String texto) {
    	JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.RED); //color del boton
                    g2.fillRoundRect(2, 2, getWidth() -4, getHeight() -4, 25, 25); // Redondez
              
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            	super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.black); // Color del borde
                g2.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 25, 25); 
            }
        };
        
    	Dimension botonTamano = new Dimension(280, 55);
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
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }
}
