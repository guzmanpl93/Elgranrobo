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
	
	//Estas clases internas están de factorias de objetos
	public class objetosP extends Objetos {
		//Objetos de Poli: linterna (mejor visibilidad?)...
		public objetosP() {
			
		}
	}
	
	public class objetosL extends Objetos {
		//Objetos de ladrón: Bebida energetica (movimiento mejorada), scanner(dice donde esta el guardia)...
		public objetosL() {
			
		}
	
	}
}
