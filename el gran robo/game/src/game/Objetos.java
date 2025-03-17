package game;

public class Objetos {
	protected int usos;
	protected int usosMax;
	protected int recarga;
	protected String nombre;
	
	public int getUsos() {
		return usos;
	}

	public void setUsos(int usos) {
		this.usos = usos;
	}

	public int getRecarga() {
		return recarga;
	}

	public void setRecarga(int recarga) {
		this.recarga = recarga;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Objetos [usos=" + usos + ", recarga=" + recarga + ", nombre=" + nombre + "]";
	}
	
}
