package Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Combate extends JFrame {
    private ArrayList<String> equipos;
    private Map<String, Integer> vidaEquipos;
    private int ronda;
    private JTextArea combateArea;
    private JPanel fondo;

    public Combate(ArrayList<String> equiposOriginales) {
        this.equipos = new ArrayList<>(equiposOriginales);
        setTitle("Simulación de Combate");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFondo = new JPanel() {
        	private Image fondo = new ImageIcon("imagenes/mazmorra2.png").getImage();
        	@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setBackground(null);
        panelFondo.setLayout(null);
        panelFondo.setOpaque(false);
//        JPanel fondo = new JPanel() {
//            protected void paintComponent(Graphics g) {
//                Image img = new ImageIcon("imagenes/mazmorara2.png").getImage();
//                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        panelFondo.setLayout(new BorderLayout());

        JTextArea combateArea = new JTextArea();
        combateArea.setEditable(false);
        combateArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(combateArea);

        JButton botonSimular = new JButton("Simular Combate");
        botonSimular.addActionListener(e -> {
            ArrayList<String> podio = simularBatalla(combateArea, panelFondo);
            Clasificacion clasificacion = new Clasificacion(podio);
            clasificacion.setVisible(true);
            dispose();
        });

        panelFondo.add(scroll, BorderLayout.CENTER);
        panelFondo.add(botonSimular, BorderLayout.SOUTH);
        setContentPane(panelFondo);
    }

    private ArrayList<String> simularBatalla(JTextArea area, JPanel panelFondo) {
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
