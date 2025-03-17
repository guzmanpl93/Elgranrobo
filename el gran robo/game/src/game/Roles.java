package game;

public enum Roles {
	POLI("poli"),CACO("caco");
	private String valor;
	
	//Metodos
	Roles(String s){
		this.valor = s;
	}
	
	public String getValor() {
		return valor;
	}
}
