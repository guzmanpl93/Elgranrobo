package game;

import javax.swing.JFrame;

public class Marco extends JFrame{
	
	PanelPrincipal panel;
	
	Marco(){
		setBounds(400,500,1000,1000); //tamaño del marco
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para cerar la pantalla
		setTitle("El gran robo"); 
		
		panel = new PanelPrincipal();
		add(panel);
		
		setVisible(true); //hacer visible el nombre 
	}

}
