package Game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Clasificacion extends JFrame {
    public Clasificacion(ArrayList<String> podio) {
        setTitle("Clasificación Final");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Background fondo = new Background("imagenes/podio2.png");
        fondo.setLayout(new BorderLayout()); // ✅ usa BorderLayout en lugar de null
        setContentPane(fondo);

        JTextArea area = new JTextArea("🏆 Podio:\n\n");
        String[] medallas = {"🥇", "🥈", "🥉"};
        for (int i = 0; i < podio.size(); i++) {
            area.append(medallas[i] + " " + podio.get(i) + "\n");
        }

        area.setFont(new Font("Arial", Font.BOLD, 20));
        area.setOpaque(false);
        area.setEditable(false);
        area.setForeground(Color.WHITE); // Mejor visibilidad en fondo

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null); // Opcional

        fondo.add(scroll, BorderLayout.CENTER);

        // ==== BOTONES ====
        JPanel botones = new JPanel();
        botones.setOpaque(false);

        ImageIcon iconoOriginal = new ImageIcon("imagenes/save.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton btnGuardar = new JButton(new ImageIcon(imagenEscalada));
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(e -> ConexionBD.guardarClasificacion(podio));

        ImageIcon iconoOriginal1 = new ImageIcon("imagenes/menu.png");
        Image imagenEscalada1 = iconoOriginal1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton btnMenu = new JButton(new ImageIcon(imagenEscalada1));
        btnMenu.setToolTipText("Volver al Menú");
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.addActionListener(e -> {
            dispose();
            new Menu().setVisible(true);
        });

        botones.add(btnGuardar);
        botones.add(btnMenu);
        fondo.add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
