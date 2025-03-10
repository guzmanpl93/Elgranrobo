package game;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	
	//atributos
	private int id_equipo;
	private String nombre;
	private List<Jugadores> Jugador;
	
	//Constructores
	public Equipo(int id_equipo, String nombre) {
		this.id_equipo = id_equipo;
		this.nombre = nombre;
		this.Jugador = new ArrayList<>();
	}

	//	Metodos
	public void agregarJugadores(Jugadores jugador) {
//		Jugadores.add(Jugador);
	}
	
	//Getters y Setters
	public int getId_equipo() {
		return id_equipo;
	}


	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Jugadores> getJugador() {
		return Jugador;
	}


	public void setJugador(List<Jugadores> jugador) {
		Jugador = jugador;
	}
}

