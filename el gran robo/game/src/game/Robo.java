package game;

import java.util.Random;

public class Robo extends Equipo {
	public int puntos;
	
	/*
	- Robo extends equipo -> Esto es por los puntos; al robar, se quitan puntos del equipo y 
	  se le suman al jugador que roba (explicado mejor en accion)
	- Se podría robar varias vezes
	- Los puntos no bajan de 0, el factor de robo base es de 1/1 (1 punto ganado por 1 robado)
	- Podria hacer que el factor de robo incremente por cada ronda, y hacer que la final sea 
	  muy efectivo (de 1/1 a 5/1, por ejemplo)
	*/
	
	public Robo(int id_equipo, String nombre) {
		super(id_equipo, nombre);
		// TODO Auto-generated constructor stub
	}
	
	//Getters y Setters
	public int getPuntos() {
		return puntos;
	}
		
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	//metodos
//	public static void adquirir(Jugadores player) {
//		accion.setPuntosP(robado());
//	}
		
	public static boolean roboExito() {
		Random r = new Random();
		return r.nextInt(0, 101) >50 ? true : false;
	}
	
	public static int robado() {
		if (roboExito()) {
		Random r = new Random();
		return r.nextInt(3, 11) * mejoraRobo();
		}
		return 0;
	}
	
	public static int mejoraRobo() {
		switch (Timer.noche) {
		case 1,2: return 1;
		case 3,4: return 2;
		case 5,6: return 3;
		case 7,8: return 4;
		default: return 5;
		}
	}
	
}
