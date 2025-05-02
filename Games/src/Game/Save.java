package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Save extends JFrame {
    public Save() {
        setTitle("Jugadores Registrados");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea("🧍 Lista de Jugadores Recientes:\n\n");
        area.setFont(new Font("Arial", Font.PLAIN, 16));

        ArrayList<String> jugadores = ConexionBD.obtenerJugadoresRecientes();
        for (String jugador : jugadores) {
            area.append("• " + jugador + "\n");
        }

        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
