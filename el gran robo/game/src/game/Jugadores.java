package game;

public class Jugadores {
	
	//atributos
	
	private int Id_Jugador;
	private int Id_Equipo;
	private String Nombre;
	private String Rol;

	//getters y setters
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getRol() {
		return Rol;
	}
	
	public void setRol(String rol) {
		Rol = rol;
	}
	
	public int getId_Jugador() {
		return Id_Jugador;
	}
	
	public int getId_Equipo() {
		return Id_Equipo;
	}
	
	//Metodos
	
	//CambiarEquipo() es un metodo que cambia el equipo de un jugador

	public void CambiarEquipo(int Id_Equipo) {
		this.Id_Equipo = Id_Equipo;
	}

	//detectarYPillar() es un metodo que detecta a un jugador y pilla al otro jugador

	public void detectarYPillar(Jugadores jugador) {
		System.out.println("El jugador " + getNombre() + " ha detectado a " + jugador.getNombre() + " y lo ha pillado");
	}
	
	//hacerAlgo() es un metodo que ataca a otro jugador

	public void hacerAlgo(Jugadores jugador) {
		System.out.println("El jugador " + getNombre() + " ha atacado a " + jugador.getNombre());
	}

}
