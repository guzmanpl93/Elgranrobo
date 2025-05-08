package Game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class Jugar extends JFrame {

    private ArrayList<String> equipos;
    private JTextArea infoEquipos;

    public Jugar() {
        setTitle("El Gran Robo - Jugar");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); 
        getContentPane().setLayout(null);

        equipos = new ArrayList<>();

        JPanel panelFondo = new JPanel() {
            private Image fondo = new ImageIcon("imagenes/mazmorra3.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setBackground(null);
        panelFondo.setLayout(null);

        //Empieza el juego
        JButton empezarJuego = crearBotonRedondeado("Empezar Juego", 112, 230, 258, 75);
        empezarJuego.addActionListener(e -> empezarJuego());
        panelFondo.add(empezarJuego);

        //Escribir los nombres de equipo
        JTextField cuadroTexto = new JTextField();
        cuadroTexto.setBounds(621, 520, 223, 30);
        panelFondo.add(cuadroTexto);

        //Elegir si quieres ser un espia o un guardian
        JComboBox<String> comboRoles = new JComboBox<>(new String[]{"Espía", "Guardián"});
        comboRoles.setBounds(611, 448, 245, 51);
        comboRoles.setMaximumRowCount(10);
        comboRoles.setFont(new Font("Arial Black", Font.BOLD, 20));
        panelFondo.add(comboRoles);

        //Seleccion de subrol
        JComboBox<String> comboPersonajes = new JComboBox<>();
        comboPersonajes.setBounds(611, 400, 245, 38);
        comboPersonajes.setFont(new Font("Arial Black", Font.BOLD, 16));
        panelFondo.add(comboPersonajes);

        //Añade los roles de los guardianes y espias
        comboRoles.addActionListener(e -> actualizarComboPersonajes(comboRoles, comboPersonajes));

        //Añade al juego el equipo
        JButton botonAñadirEquipo = crearBotonRedondeado("Añadir Equipo", 614, 310, 245, 62);
        botonAñadirEquipo.addActionListener(e -> añadirEquipo(comboRoles, comboPersonajes, cuadroTexto));
        panelFondo.add(botonAñadirEquipo);

        //Boton de informacion de cada rol
        JButton botonMostrarInfo = crearBotonRedondeado("Info Personajes", 112, 385, 258, 75);
        botonMostrarInfo.addActionListener(e -> abrirInfoPersonajes());
        panelFondo.add(botonMostrarInfo);

        //Boton de regreso al menu
        JButton botonVolverMenu = crearBotonRedondeado("Volver al Menú", 112, 540, 258, 75);
        botonVolverMenu.addActionListener(e -> volverAlMenu());
        panelFondo.add(botonVolverMenu);

        infoEquipos = new JTextArea();
        infoEquipos.setBounds(1030, 374, 400, 200);
        infoEquipos.setFont(new Font("Arial Black", Font.PLAIN, 20));
        infoEquipos.setEditable(false);
        infoEquipos.setBackground(Color.WHITE);
        infoEquipos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelFondo.add(infoEquipos);

        setContentPane(panelFondo);
    }

    //Constructor de lista de roles, para espias y guardianes
    private void actualizarComboPersonajes(JComboBox<String> comboRoles, JComboBox<String> comboPersonajes) {
        String rolSeleccionado = (String) comboRoles.getSelectedItem();
        if (rolSeleccionado != null) {
            if (rolSeleccionado.equals("Espía")) {
                comboPersonajes.setModel(new DefaultComboBoxModel<>(new String[]{
                    "El Fantasma", "El Hacker", "La Sombra", "El Maestro del Disfraz", "El Acróbata", "El Saboteador", "El Corredor"
                }));
            } else if (rolSeleccionado.equals("Guardián")) {
                comboPersonajes.setModel(new DefaultComboBoxModel<>(new String[]{
                    "El Centinela", "El Rastreador", "El Perro Guardián", "El Cazador", "El Experto en Seguridad", "El Francotirador", "El Patrullero"
                }));
            }
        }
    }

    //Añade equipos
    private void añadirEquipo(JComboBox<String> comboRoles, JComboBox<String> comboPersonajes, JTextField cuadroTexto) {
        String rolSeleccionado = (String) comboRoles.getSelectedItem();
        String personajeSeleccionado = (String) comboPersonajes.getSelectedItem();
        String nombreJugador = cuadroTexto.getText();

        if (nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el nombre del jugador.");
        } else if (equipos.size() >= 10) {
            JOptionPane.showMessageDialog(this, "No se pueden añadir más de 10 equipos.");
        } else if (personajeSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un personaje.");
        } else {
            equipos.add(nombreJugador + " - " + rolSeleccionado + " - " + personajeSeleccionado);
            JOptionPane.showMessageDialog(this, "Se ha añadido a " + nombreJugador + " como " + rolSeleccionado + " y personaje " + personajeSeleccionado + ".");
            cuadroTexto.setText("");
            actualizarListaEquipos();
        }
    }

    private void actualizarListaEquipos() {
        StringBuilder lista = new StringBuilder("Equipos añadidos:\n");
        for (String equipo : equipos) {
            lista.append(equipo).append("\n");
        }
        infoEquipos.setText(lista.toString());
    }

    private void empezarJuego() {
        if (equipos.size() < 3) {
            JOptionPane.showMessageDialog(this, "Debe haber al menos 3 equipos para comenzar.");
//            return;
        } else {
        
        // Crear una nueva instancia de Partida con los equipos seleccionados
        new Partida(equipos).setVisible(true);
        dispose(); // Cerrar la ventana actual después de abrir la partida
        }
    }

//    private void empezarJuego() {
//        if (equipos.size() < 3) {
//            JOptionPane.showMessageDialog(this, "Debe haber al menos 3 equipos para comenzar.");
//            return;
//        }
//
//        JOptionPane.showMessageDialog(this, "El juego ha comenzado con " + equipos.size() + " equipos.");
//        
//        // Abrir la nueva ventana Partida con los equipos
//        new Partida(equipos).setVisible(true);
//        dispose(); // Cerrar la ventana actual después de abrir la partida
//    }

    private void volverAlMenu() {
        dispose();
        new Menu().setVisible(true);
    }

    private void abrirInfoPersonajes() {
        InfoPersonajes ventanaInfo = new InfoPersonajes(this);
        ventanaInfo.setVisible(true);
        setVisible(false);
    }
    
    
    //Añade botones de interaccion para diversos objetivos
    private JButton crearBotonRedondeado(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40));
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Arial Black", Font.BOLD, 25));
        boton.setBackground(Color.LIGHT_GRAY);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Jugar().setVisible(true));
    }
}

//Pestaña emerjente para mostrar informacion de cada rol
class InfoPersonajes extends JFrame {
    public InfoPersonajes(JFrame parent) {
        setTitle("Información de Personajes");
        setSize(1500, 1024);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Crear panel con color de fondo (morado pálido)
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(230, 220, 250));  // Color morado pálido (RGB)
        panelFondo.setLayout(new BorderLayout());
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setFont(new Font("Arial", Font.PLAIN, 18));
        infoArea.setEditable(false);
        infoArea.setText("Selecciona un personaje para ver su descripción.");

        JPanel panelPersonajes = new JPanel(new GridLayout(0, 2));
        panelPersonajes.setOpaque(false);  // Transparente para que se vea el color de fondo

     // Agregamos personajes con sus descripciones completas
      agregarPersonaje(panelPersonajes, "El Fantasma", "Puede volverse invisible por un turno, eludiendo así la detección de los guardias. \n200 puntos de vida. \n 50 misiles por ronda.", "personajes/espias/fantasma.jpg");
      agregarPersonaje(panelPersonajes, "El Hacker", "Especializado en desactivar trampas y abrir puertas electrónicas, facilitando el paso de los espías. \n200 puntos de vida. \n 50 misiles por ronda.", "personajes/espias/hacker.jpg");
      agregarPersonaje(panelPersonajes, "La Sombra", "Es capaz de moverse dos casillas sin ser detectada, gracias a su sigilo y rapidez. \n200 puntos de vida.\n 50 misiles por ronda. \nAtaca el doble al verde (x2).\n ataca la mitad al azul (/2).", "personajes/espias/sombra.jpg");
      agregarPersonaje(panelPersonajes, "El Maestro del Disfraz", "Puede hacerse pasar por un guardia, engañando a otros sin ser identificado. \n200 puntos de vida.\n 50 misiles por ronda. Ataca el doble al rojo (x2).\n ataca la mitad al verde (/2).", "personajes/espias/disfraz.jpg");
      agregarPersonaje(panelPersonajes, "El Acróbata", "Habilidad para saltar obstáculos sin ser ralentizado, permitiendo moverse rápidamente. \n200 puntos de vida.\n 50 misiles por ronda.\n Ataca el doble al azul (x2).\n ataca la mitad al rojo (/2).", "personajes/espias/acrobata.jpg");
      agregarPersonaje(panelPersonajes, "El Saboteador", "Coloca trampas falsas que pueden confundir a los guardias, alterando su estrategia. \n400 puntos de vida.\n empieza con 10 misiles y aumenta 2 por ronda.", "personajes/espias/saboteador.jpg");
      agregarPersonaje(panelPersonajes, "El Corredor", "Gana un movimiento extra en su turno, permitiendo alcanzar áreas distantes con rapidez. \n100 puntos de vida.\n probabilidad de esquivar del 50%.", "personajes/espias/corredor.jpg");

      agregarPersonaje(panelPersonajes, "El Centinela", "Posee un rango de visión más amplio, lo que le permite detectar a los espías con mayor facilidad. \n200 puntos de vida.\n 50 misiles por ronda.", "personajes/guardias/centinela.jpg");
      agregarPersonaje(panelPersonajes, "El Rastreador", "Capaz de ver las huellas dejadas por los espías, lo que le ayuda a rastrear su ubicación. \n200 puntos de vida.\n 50 misiles por ronda.\n Ataca el doble al verde (x2).\n Ataca la mitad al azul (/2).", "personajes/guardias/rastreador.jpg");
      agregarPersonaje(panelPersonajes, "El Perro Guardián", "Puede moverse dos veces en un turno, cubriendo una mayor área de forma más eficiente. \n200 puntos de vida.\n 50 misiles por ronda. Ataca el doble al rojo (x2),\nAtaca la mitad al verde (/2).", "personajes/guardias/perro.jpg");
      agregarPersonaje(panelPersonajes, "El Cazador", "Lanza una red para atrapar a los espías, inmovilizándolos temporalmente. 200 puntos de vida, 50 misiles por ronda. Ataca el doble al azul (x2)\n Ataca la mitad al rojo (/2).", "personajes/guardias/cazador.jpg");
      agregarPersonaje(panelPersonajes, "El Experto en Seguridad", "Coloca trampas en el camino de los espías, haciéndoles la vida más difícil. \n400 puntos de vida.\n Empieza con 10 misiles y aumenta 2 por ronda.", "personajes/guardias/seguridad.jpg");
      agregarPersonaje(panelPersonajes, "El Francotirador", "Elimina a un espía a distancia con un disparo certero, sin necesidad de acercarse. \n100 puntos de vida. \nProbabilidad de esquivar del 50%.", "personajes/guardias/francotirador.jpg");
      agregarPersonaje(panelPersonajes, "El Patrullero", "Tiene la capacidad de cambiar de posición con otro guardia, alterando la estrategia enemiga. \n200 puntos de vida.\n 50 misiles por ronda.", "personajes/guardias/patrullero.jpg");

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

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(botonVolver);
        panelBoton.setOpaque(false);

        panelInfo.add(panelBoton, BorderLayout.NORTH);
        panelInfo.add(new JScrollPane(panelPersonajes), BorderLayout.CENTER);

        panelFondo.add(panelInfo, BorderLayout.CENTER);  // Se añade el contenido al panel con fondo
        setContentPane(panelFondo);
        setVisible(true);
    }

    private void agregarPersonaje(JPanel panel, String nombre, String descripcion, String rutaImagen) {
        JPanel panelPersonaje = new JPanel(new GridBagLayout());
        panelPersonaje.setOpaque(false);  // Transparente para respetar el color de fondo del contenedor

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Imagen
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel etiquetaImagen = new JLabel(iconoEscalado);

        // Nombre
        JLabel etiquetaNombre = new JLabel(nombre, JLabel.LEFT);
        etiquetaNombre.setFont(new Font("Arial", Font.BOLD, 20));

        // Descripción
        JTextArea areaDescripcion = new JTextArea(descripcion);
        areaDescripcion.setEditable(false);
        areaDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setPreferredSize(new Dimension(600, 100));

        // Añadir elementos al panel con diseño de cuadrícula flexible
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelPersonaje.add(etiquetaImagen, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPersonaje.add(etiquetaNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPersonaje.add(areaDescripcion, gbc);

        panel.add(panelPersonaje);
    }
}