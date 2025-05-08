package Game;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Clasificacion extends JFrame {
    public Clasificacion(ArrayList<String> podio) {
        setTitle("Clasificación Final");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelFondo = new JPanel() {
        	private Image fondo = new ImageIcon("imagenes/podio.png").getImage();
        	@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setBackground(null);
        panelFondo.setLayout(null);
//        JPanel fondo = new JPanel() {
//            protected void paintComponent(Graphics g) {
//                Image img = new ImageIcon("imagenes/podio.png").getImage();
//                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        fondo.setLayout(new BorderLayout());
 

        JTextArea area = new JTextArea("🏆 Podio:\n\n");
        String[] medallas = {"🥇", "🥈", "🥉"};
        for (int i = 0; i < podio.size(); i++) {
            area.append(medallas[i] + " " + podio.get(i) + "\n");
        }

        area.setFont(new Font("Arial", Font.BOLD, 20));
        area.setOpaque(false);
        area.setEditable(false);

        JPanel botones = new JPanel();
        botones.setOpaque(false);

//        JButton btnGuardar = new JButton(new ImageIcon("imagenes/save.png"));
        ImageIcon iconoOriginal = new ImageIcon("imagenes/save.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton btnGuardar = new JButton(new ImageIcon(imagenEscalada));
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(e -> ConexionBD.guardarClasificacion(podio));

//        JButton btnMenu = new JButton(new ImageIcon("imagenes/menu.png"));
//        btnMenu.setToolTipText("Volver al Menú");
        ImageIcon iconoOriginal1 = new ImageIcon("imagenes/menu.png");
        Image imagenEscalada1 = iconoOriginal1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // tamaño deseado
        JButton btnMenu = new JButton(new ImageIcon(imagenEscalada1));

        btnMenu.addActionListener(e -> {
            dispose();
            new Menu().setVisible(true);
        });

        botones.add(btnGuardar);
        botones.add(btnMenu);

        panelFondo.add(new JScrollPane(area), BorderLayout.CENTER);
        panelFondo.add(botones, BorderLayout.SOUTH);

        setContentPane(panelFondo);
    }
}
