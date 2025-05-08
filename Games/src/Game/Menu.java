package Game;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Component frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }

    public Menu() {
//    	marco del menu
        setTitle("El Gran Robo");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
//        imagen del marco de menu
        JPanel panelFondo = new JPanel() {
            private Image fondo = new ImageIcon("imagenes/ElGranRobo.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new GridBagLayout());

        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);
        
        //Lleva a la seleccion de equipos
        JButton jugar = crearBoton("JUGAR");
        jugar.addActionListener(e -> {
            dispose();
            new Jugar().setVisible(true);
        });
        
        //Ver las reglas
        JButton reglas = crearBoton("REGLAS");
        reglas.addActionListener(e -> abrirReglas());

        //Ver las partidas anteriores y cargarlas
        JButton save = crearBoton("SAVE");
        save.addActionListener(e -> {
	        new CargarPartida().setVisible(true);
	    });
        
        //Salir del juego
        JButton exit = crearBoton("EXIT");
        exit.addActionListener(e -> System.exit(0));

        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.fill = GridBagConstraints.CENTER;
        gbcBotones.insets = new Insets(15, 0, 15, 0);

        gbcBotones.gridy = 0;
        panelBotones.add(jugar, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(reglas, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(save, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(exit, gbcBotones);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(-10, 700, 150, -50);

        panelFondo.add(panelBotones, gbc);
        setContentPane(panelFondo);
    }

    //Constructor automatico de los botones
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.RED);
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 25, 25);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.black);
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

    //Para abrir y ver las reglas
    private void abrirReglas() {
        new Reglas(this).setVisible(true);
        setVisible(false); // Oculta la ventana principal mientras las reglas están abiertas
    }
}

class Reglas extends JFrame {

    public Reglas(JFrame parent) {
        setTitle("Reglas del Juego");
        setSize(1500, 900);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelFondo = new JPanel(new BorderLayout(0, 0)); // Sin espacio adicional
        panelFondo.setBackground(Color.decode("#d69afb"));

        // Panel para el contenido sin relleno adicional
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setOpaque(false);

        JLabel reglasLabel = new JLabel("<html>"
              + "<div style='text-align:center;'>"
              + "<h1 style='font-size:30px; color:blue; margin-bottom:10px;'>El Gran Robo - Reglas y Mecánicas del Juego</h1>"
              + "<p style='font-size:24px; text-align:justify; color:0272fa;'>"
              + "📌 <strong>Descripción del Proyecto</strong><br></p>"
              + "<<p style='font-size:20px; text-align:justify;'>El Gran Robo es un juego por equipos donde cada equipo debe infiltrarse en la base rival para robar información,<br>"
              + "mientras protege su propia base de los espías enemigos. La estrategia y el sigilo son clave para la victoria."
              + "</p>"
              + "<h2 style='font-size:24px; color:0272fa;'>🔹 Cómo Funciona</h2>"
              + "<p style='font-size:20px; text-align:justify;'>"
              + "👉 <strong>Equipos:</strong> Cada equipo tiene seis roles principales:<br>"
              + "🔹 <strong>Espías:</strong> Intentan infiltrarse en la base enemiga sin ser descubiertos.<br>"
              + "🔹 <strong>Guardias:</strong> Defienden la base y tratan de detectar a los espías rivales.<br>"
              + "📍 <strong>Turnos y roles:</strong> Los roles cambian cada turno, lo que obliga a los jugadores a planear tanto ofensiva como defensivamente."
              + "</p>"
              + "<h2 style='font-size:24px; color:0272fa;'>🎲 Movimientos en el Tablero</h2>"
              + "<p style='font-size:20px; text-align:justify;'>"
              + "📌 <strong>Estructura:</strong> El tablero es un laberinto o mazmorra con caminos, obstáculos y áreas seguras.<br>"
              + "🔹 <strong>Espías:</strong> Se mueven estratégicamente para llegar a la base enemiga sin ser detectados.<br>"
              + "🔹 <strong>Guardias:</strong> Tienen visión limitada y solo detectan espías en su rango cercano."
              + "</p>"
              + "<h2 style='font-size:24px; color:0272fa;'>🎭 Acciones Especiales</h2>"
              + "<p style='font-size:20px; text-align:justify;'>"
              + "🕵️ <strong>Espías:</strong><br>"
              + "✅ Pueden usar gadgets como disfraces, humo o llaves maestras para evitar ser descubiertos.<br>"
              + "🚔 <strong>Guardias:</strong><br>"
              + "✅ Pueden colocar trampas para retrasar o detener a los espías."
              + "</p>"
              + "<h2 style='font-size:24px; color:0272fa;'>🏆 Cómo Se Gana</h2>"
              + "<p style='font-size:20px; text-align:justify;'>"
              + "🥇 <strong>Gana el equipo</strong> que robe más información en un número limitado de turnos.<br>"
              + "❌ También pueden ganar si logran impedir que el equipo rival robe información."
              + "</p>"
              + "</div></html>");
        reglasLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelCentral.add(reglasLabel, BorderLayout.NORTH);

        // Botón Volver optimizado
        ImageIcon iconoVolver = new ImageIcon("imagenes/atras.png");
        Image img = iconoVolver.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton botonVolver = new JButton(new ImageIcon(img));
        botonVolver.setPreferredSize(new Dimension(50, 50));
        botonVolver.setBorderPainted(false);
        botonVolver.setContentAreaFilled(false);
        botonVolver.setFocusPainted(false);
        botonVolver.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });

        // Ajustar margen y evitar espacio adicional
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBoton.add(botonVolver);
        panelBoton.setOpaque(false);

        panelFondo.add(panelBoton, BorderLayout.NORTH);
        panelFondo.add(panelCentral, BorderLayout.CENTER);

        setContentPane(panelFondo);
    }
}
