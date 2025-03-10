package game;

public class Bases {
	
	//atributos
	private int Id_base;
	private int Id_equipo;
	private int [] Ubicacion;
	
	//Constructores
		public Bases(int Id_base, int Id_equipo, int [] Ubicacion) {
			this.Id_base = Id_base;
			this.Id_equipo = Id_equipo;
			this.Ubicacion = Ubicacion;
		}
	
	//Metodos 
	public int[] lugarInicioBase() {
		return Ubicacion != null ? Ubicacion : null;
	}
	
	public void mostrarUbicacion() {
		if (Ubicacion != null && Ubicacion.length == 2) {
			System.out.println("La base " + Id_base + " del equipo " + Id_equipo + " se encuentra en la posicion: (" + Ubicacion[0] + ", " + Ubicacion[1] + ")");
		} else {
			System.out.println("La ubicacion de la base " + Id_base + " no esta definida correctamente.");
		}
	}
	
	//Getter y Setter
	public int getId_Base() {
		return Id_base;
	}
	public void setId_Base(int Id_base) {
		this.Id_base = Id_base;
	}
	public int getId_equipo() {
		return Id_equipo;
	}
	public void setId_equipo(int id_equipo) {
		this.Id_equipo = id_equipo;
	}
	public int[] getUbicacion() {	
		return Ubicacion;
	}
	public void setUbicacion(int[] Ubicacion) {
		this.Ubicacion = Ubicacion;
	}
	
}
