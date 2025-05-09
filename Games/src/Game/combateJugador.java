package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class combateJugador extends JFrame{
	private ArrayList<String> equipos;
    private Map<String, Integer> vidaEquipos;
    private int ronda;
    private JTextArea combateArea;
    private JPanel fondo;

    public combateJugador(ArrayList<String> equiposOriginales) {
        this.equipos = new ArrayList<>(equiposOriginales);
        setTitle("Simulación de Combate");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Fondo personalizado
        Background fondo = new Background("imagenes/arena2.png"); // Imagen visible todo el combate
        fondo.setLayout(null); // <- importante para ubicar manualmente los elementos encima sin reemplazar el fondo
        setContentPane(fondo);

        // Área de combate transparente para que no tape el fondo
        combateArea = new JTextArea();
        combateArea.setEditable(false);
        combateArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        combateArea.setOpaque(true); // <-- transparencia para ver el fondo
        combateArea.setForeground(Color.BLACK); // texto visible sobre fondo oscuro

        JScrollPane scroll = new JScrollPane(combateArea);
        scroll.setBounds(100, 100, 800, 500); // Posición personalizada
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        // Botón de simular
        JButton botonSimular = new JButton("Simular Combate");
        botonSimular.setFont(new Font("Arial", Font.BOLD, 16));
        botonSimular.setBackground(Color.BLACK);
        botonSimular.setForeground(Color.BLACK);
        botonSimular.setBounds(100, 620, 200, 40); // Coordenadas manuales
        botonSimular.addActionListener(e -> {
            ArrayList<String> podio = hacerBatalla(combateArea);
            Clasificacion clasificacion = new Clasificacion(podio);
            clasificacion.setVisible(true);
            dispose();
        });
        // ==== BOTONES ====
        JPanel botones = new JPanel();
        botones.setOpaque(false);

        ImageIcon iconoOriginal = new ImageIcon("imagenes/save.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton btnGuardar = new JButton(new ImageIcon(imagenEscalada));
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(e -> ConexionBD.guardarClasificacion(equipos));
        // Agrega al panel de fondo (que mantiene la imagen visible)
        fondo.add(btnGuardar);
        fondo.add(scroll);
        fondo.add(botonSimular);
    }
	
	private ArrayList<String> hacerBatalla(JTextArea area) {    	
    	Random rand = new Random();
        ArrayList<String> participantes = new ArrayList<>(equipos);
        Map<String, Integer> vidaEquipos = new HashMap<>();
        
        elegirJugador(participantes);

        for (String eq : participantes)
            vidaEquipos.put(eq, 100 + rand.nextInt(101));

        while (participantes.size() > 1) {
            Collections.shuffle(participantes);

            for (int i = 0; i < participantes.size() - 1; i += 2) {
                String eq1 = participantes.get(i), eq2 = participantes.get(i + 1);
                int atk1 = 20 + rand.nextInt(30), atk2 = 20 + rand.nextInt(30);

                vidaEquipos.put(eq1, vidaEquipos.get(eq1) - atk2);
                vidaEquipos.put(eq2, vidaEquipos.get(eq2) - atk1);

                area.append(eq1 + " ataca a " + eq2 + " con " + atk1 + "\n");
                area.append(eq2 + " contraataca con " + atk2 + "\n");

                mostrarImagenPersonaje(fondo, eq1);
                try { Thread.sleep(700); } catch (Exception ignored) {}

                if (vidaEquipos.get(eq1) <= 0) area.append("❌ " + eq1 + " eliminado.\n");
                if (vidaEquipos.get(eq2) <= 0) area.append("❌ " + eq2 + " eliminado.\n");
            }

            participantes.removeIf(eq -> vidaEquipos.get(eq) <= 0);
            area.append("----------\n");
        }
        ArrayList<Map.Entry<String, Integer>> ordenados = new ArrayList<>(vidaEquipos.entrySet());
        ordenados.removeIf(e -> e.getValue() <= 0);
        ordenados.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        ArrayList<String> podio = new ArrayList<>();
        for (int i = 0; i < Math.min(3, ordenados.size()); i++)
            podio.add(ordenados.get(i).getKey());

        return podio;
    }

    private void elegirJugador(ArrayList<String> participantes) { //G: Intente crear un metodo que permita elegir el equipo con el que se quiere jugar
    	String [] contenido = new String[participantes.size()];
    	for (int i =0; i< participantes.size(); i++)
    		contenido[i] = participantes.get(i);
    	
    	JDialog ventana = new JDialog(this, "Elegir equipo", true);
    	JPanel contenedor = new JPanel();
    	
        ventana.setLayout(new BorderLayout());
        
        
        //Esto añade los equipos, el elegido es con el cual se juega
        JComboBox<String> options = new JComboBox<String>(contenido);
        contenedor.add(options);
        
        JTextArea space = new JTextArea();
        space.setEditable(false);
        space.setFont(new Font("Monospaced", Font.PLAIN, 14));
        space.contains(200, 200);
        space.append("Elija con que equipo quieres luchar;\n el resto seran todos máquinas");
        contenedor.add(space);
        ventana.add(contenedor);
        
        ventana.setSize(250, 200);
        
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
        
        
            
//        verEquipos(infoEquipos);
	}
	
    private void mostrarImagenPersonaje(JPanel fondo, String nombreEquipo) {
        String personaje = nombreEquipo.split("-")[2].trim().toLowerCase().replace(" ", "");
        ImageIcon icon = new ImageIcon("personajes/" + personaje + ".png");
        JLabel imagen = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));

        JDialog ventana = new JDialog(this, "Ataque", true);
        ventana.setLayout(new BorderLayout());
        ventana.add(imagen, BorderLayout.CENTER);
        ventana.setSize(200, 250);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }
}
