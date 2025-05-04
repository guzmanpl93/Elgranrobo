package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

public class Combate extends JFrame {
    private ArrayList<String> equipos;

    public Combate(ArrayList<String> equiposOriginales) {
        this.equipos = new ArrayList<>(equiposOriginales);
        setTitle("Simulación de Combate");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel fondo = new JPanel() {
            protected void paintComponent(Graphics g) {
                Image img = new ImageIcon("imagenes/mazmorara2.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setLayout(new BorderLayout());

        JTextArea combateArea = new JTextArea();
        combateArea.setEditable(false);
        combateArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(combateArea);

        JButton botonSimular = new JButton("Simular Combate");
        botonSimular.addActionListener(e -> {
            ArrayList<String> podio = simularBatalla(combateArea, fondo);
            Clasificacion clasificacion = new Clasificacion(podio);
            clasificacion.setVisible(true);
            dispose();
        });
        
        JButton botonParticipar = new JButton("Jugar en Solitario");
        botonParticipar.addActionListener(e -> {
            ArrayList<String> podio = hacerBatalla(combateArea, fondo);
            Clasificacion clasificacion = new Clasificacion(podio);
            clasificacion.setVisible(true);
            dispose();
        });

        fondo.add(scroll, BorderLayout.CENTER);
        fondo.add(botonParticipar, BorderLayout.NORTH);
        fondo.add(botonSimular, BorderLayout.SOUTH);
        setContentPane(fondo);
    }

    private ArrayList<String> hacerBatalla(JTextArea area, JPanel fondo) {
    	elegirJugador();
    	
    	Random rand = new Random();
        ArrayList<String> participantes = new ArrayList<>(equipos);
        Map<String, Integer> vidaEquipos = new HashMap<>();

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
    
    private void elegirJugador() { //G: Intente crear un metodo que permita elegir el equipo con el que se quiere jugar
    	JTextArea areaT = new JTextArea();
    	JDialog ventana = new JDialog(this, "Elegir equipo", true);
        ventana.setLayout(new BorderLayout());
        ventana.add(areaT);
        ventana.setSize(200, 250);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);

        areaT.append("Elija su equipo\n");	
        JTextArea infoEquipos = null;
            
        verEquipos(infoEquipos);
	}

    private void verEquipos(JTextArea infoEquipos) {
        StringBuilder lista = new StringBuilder("Equipos añadidos:\n");
        for (String equipo : equipos) {
            lista.append(equipo).append("\n");
        }
        infoEquipos.setText(lista.toString());
    }
    
	private ArrayList<String> simularBatalla(JTextArea area, JPanel fondo) {
        Random rand = new Random();
        ArrayList<String> participantes = new ArrayList<>(equipos);
        Map<String, Integer> vidaEquipos = new HashMap<>();

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

    private void mostrarImagenPersonaje(JPanel fondo, String nombreEquipo) {
        String personaje = nombreEquipo.split("-")[2].trim().toLowerCase().replace(" ", "");
        ImageIcon icon = new ImageIcon("espias/", "guardias/" + personaje + ".png");
        JLabel imagen = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));

        JDialog ventana = new JDialog(this, "Ataque", true);
        ventana.setLayout(new BorderLayout());
        ventana.add(imagen, BorderLayout.CENTER);
        ventana.setSize(200, 250);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }
}
