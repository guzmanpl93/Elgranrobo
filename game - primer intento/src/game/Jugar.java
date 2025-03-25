package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Jugar extends JFrame{
	public Jugar() {
//	Configuracion de la ventana de juego
	setTitle("Juego en progreso");
    setSize(1500, 1024);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Agregar un panel de juego o cualquier componente
    JLabel label = new JLabel("¡GAME STARTS!");
    label.setFont(label.getFont().deriveFont(50f));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    add(label);

    // Mostrar la ventana
    setVisible(true);
  }
}
