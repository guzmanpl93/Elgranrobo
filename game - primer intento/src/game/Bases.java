package game;

public class Bases {
	
	//atributos
	private int Id_base;
	private int Id_equipo;
	private int[] ubicacion;
	
	//Constructores
		public Bases(int Id_base, int Id_equipo, int[] Ubicacion) {
			this.Id_base = Id_base;
			this.Id_equipo = Id_equipo;
			this.ubicacion = ubicacion;
		}
	
	//Metodos 
	public int[] lugarInicioBase() {
		return ubicacion;
	}
	
	public void mostrarUbicacion() {
		if (ubicacion != null && ubicacion.length == 2) {
			System.out.println("La base " + Id_base + " del equipo " + Id_equipo + " se encuentra en la posicion: (" + ubicacion[0] + ", " + ubicacion[1] + ")");
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
		return ubicacion;
	}
	public void setUbicacion(int[] Ubicacion) {
		this.ubicacion = Ubicacion;
	}
	
}
