package game;

public class Accion {
	//El metodo de Jugador "HacerAlgo()" debería afectar a esta clase.
	/*
	Acciones comunes: Moverse
		-Cooldown para todo? Las cosas unicas si, pero no se si dar cooldown al movimiento o limitarlo
		-La partida en si podria medirse en noches, cada noche un equipo roba a otro distinto.
		-Al fin de turno, podria alternar los polis con los ladrones
	Acciones ladron: robar, usar objetos y marchar
		-El ladron podra robar, quitando puntos e incrementando "robado".
		-Marchar acaba el turno temprano y el valor de "robado" se añade a la puntuación del equipo 
		-Objetos: usos limitados o recarga?
	Acciones polis: detectar, poner trampa?
		-El policia, al detener el ladron, recupera los puntos robados del ladron (si hay) + un 
		extra, por pillarlo. Estos puntos son otrgados al fin de turno
		-Darle trampas a la poli (Podrian alertar al policia al ser activadas o realentizar al ladron)
		o darle objetos como los ladrones?
			-Si se crean trampas, Ha de haber un limite de ellas y tener en cuenta donde están
	*/
	/* Estructura opciones:
	 * 
	 * 1. Moverse
	 * 2. Objeto
	 * 3. Robar (si es posible)
	 * 4. Marcharse (Finalizar noche)
	 */
	
	public void usar(Objetos o) {
		if (!(o.getUsos()==0)) {
			o.setUsos(o.getUsos()-1);
			activar(o);
		}
	}
	
	public Objetos elegirObjeto(Jugadores j, int i) {
		return j.getRol() == Roles.CACO ? new ObjetosC(i) : new ObjetosP(i);
	}
	
	public void activar(Objetos o) {
		o.getNombre();
	}
	
	//Estas clases internas están de factorias de objetos
	public class ObjetosP extends Objetos {
		//Objetos de Poli: linterna (mejor visibilidad?), sistema de aviso (el ladron solo puede robar 1 vez)
		//radar (indica en que direccion esta el ladron, pero no la distancia entre ellos)...
		public ObjetosP(int i) {
				switch (i) {
					default: {
						this.nombre = "Linterna";
						this.recarga= 2;
						this.usos = 2;
					} break;
					case 2: {
						this.nombre = "Sistema de aviso";
						this.recarga= 4;
						this.usos = 1;
					} break;
					case 3 : {
						this.nombre = "Radar";
						this.recarga=3;
						this.usos = 1;
					} break;
				}
			
		}
	}
	
	public class ObjetosC extends Objetos {
		//Objetos de ladrón: Bebida energetica (movimiento mejorada), scanner(dice la proximidad del guardia, pero no su ubicacion),
		//Ganzua (roba más puntos)...
		public ObjetosC(int i) {
			switch (i) {
				default: {
					this.nombre = "Refresco";
					this.recarga= 3;
					this.usos = 2;
				} break;
				case 2: {
					this.nombre = "Scanner";
					this.recarga= 5;
					this.usos = 1;
				} break;
				case 3 : {
					this.nombre = "Ganzua";
					this.recarga= 5;
					this.usos = 1;
				} break;
		}
		}
	
	}
}
