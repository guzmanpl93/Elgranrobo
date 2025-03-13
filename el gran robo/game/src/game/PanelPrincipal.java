package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class PanelPrincipal extends JPanel{
	
	JButton jugar;
	JButton info;
	JButton personajes;
	JButton save;
	JButton exit;
	PanelPrincipal(){
		setLayout(new BorderLayout());
		
		jugar = new JButton("JUGAR");
		info = new JButton("INFO");
		personajes = new JButton("PERSONAJES");
		save = new JButton("SAVE");
		exit = new JButton("EXIT");
		
		add(jugar, BorderLayout.CENTER);
		add(info, BorderLayout.LINE_START);
		add(personajes, BorderLayout.AFTER_LINE_ENDS);
		add(save, BorderLayout.BEFORE_FIRST_LINE);
		add(exit, BorderLayout.AFTER_LAST_LINE);
	}
}
