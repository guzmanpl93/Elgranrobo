package Game;

import java.util.Random;

public enum tipoRol {
	/*"El Fantasma", "El Hacker", "La Sombra", "El Maestro del Disfraz", "El Acróbata", "El Saboteador", "El Corredor"
	"El Centinela", "El Rastreador", "El Perro Guardián", "El Cazador", "El Experto en Seguridad", "El Francotirador", "El Patrullero"
	*/
	//Atributos
	fantasma("El Fantasma"), hacker("El Hacker"), sombra("La Sombra"), disfraz("El Maestro del Disfraz"),
	acrobata("El Acróbata"), saboteador("El Saboteador"), corredor("El Corredor"), centinela( "El Centinela") ,
	rastreador( "El Rastreador"), perro("El Perro Guardián"), cazador( "El Cazador"), seguridad( "El Experto en Seguridad"),
	francotirador( "El Francotirador"), patrullero( "El Patrullero");
	private String valor;
	private Random rand= new Random();

	public String getValor() {
		return valor;
	}

	tipoRol(String valor) {
		this.valor = valor;
	}
	
	public int recibirDaño(int valor, tipoRol atacante, tipoRol victima) {
		return victima == corredor ? esquivar(valor) : valor * rolMejor(atacante, victima);
		/*
		Este metodo ya se encarga de comprobar si el que está siendo atacado es un corredor (50% de esquivar daño),
		además, incorpora el metodo de rolmejor, para ver si el ataque es más efectivo
		
		Debes tomar el valor devuelto como el daño a recibir -> Vida nueva = vida vieja - recibirDaño()
		*/
	}
	/*
	Nota sobre colores
	rojo = sombra
	azul = disfraz
	verde = acrobata
	*/
	//Metodos de daño y demas
	
	public int rolMejor(tipoRol atacante, tipoRol victima) { //Este metodo es para la mejora de daño de los colores -> Rojo hace mas daño a verde, menos a azul y lo mismo a otro rojo...
		switch (atacante) {
		case sombra: 
			if (victima == disfraz) {
				return 1/2;
			} else if (victima == acrobata) {
				return 2;
			} else
				return 1;
		case disfraz: 
			if (victima == acrobata) {
				return 1/2;
			} else if (victima == sombra) {
				return 2;
			} else
				return 1;
		case acrobata:
			if (victima == acrobata) {
				return 1/2;
			} else if (victima == disfraz) {
				return 2;
			} else
				return 1;
		default: return 1;
		}
	}
		
	public int esquivar(int recibido) { //el numero que recibe es el numero de misiles con el que es atacado, hace un bucle for para aplicar el 50%
		int total =0;
		for (int i = 0; i < recibido; i++) {
			if (rand.nextBoolean())
				total++;
		}
		return total;
	}
}
