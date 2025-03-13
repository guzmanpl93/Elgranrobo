package game;

public class Timer {
	public static int turno;
	public static int noche;
	//getters y setters
	public void avanzarTurno() {
		turno++;
	}
	public void avanzarNoche() {
		noche++;
	}
	//metodos
	public int getTurno() {
		return turno;
	}
		
	public void setTurno(int turno) {
		Timer.turno = turno;
	}
	
	public int getNoche() {
		return turno;
	}
		
	public void setNoche(int noche) {
		Timer.noche = noche;
	}
}
