package Game;

public class Rol {
	private String nombre;
    private tipoRol rol;  // Líder, Miembro, Estratégico
    private int vida;
    private int vidaActual;
    private int ataque;
    private String[] ataques;

    // Constructor
    public Rol(String nombre, tipoRol rol, int vida, int ataque, String[] ataques) {
        this.nombre = nombre;
        this.rol = rol;
        this.vida = vida;
        this.vidaActual = vida;  // Inicializamos vida actual con el valor total de vida
        this.ataque = ataque;
        this.ataques = ataques; // G: Estoy asumiendo de que ataque es el gastado y ataques es el maximp, así que trabajare usando eso.
    }

    // Método para obtener la información del personaje
    public String obtenerInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(nombre).append("\n");
        info.append("Rol: ").append(rol).append("\n");
        info.append("Vida: ").append(vidaActual).append("/").append(vida).append("\n");
        info.append("Ataque: ").append(ataque).append("\n");
        info.append("Ataques disponibles: ");
        
        for (String ataque : ataques) {
            info.append(ataque).append(", ");
        }
        
        // Eliminar la última coma y espacio si hay ataques
        if (ataques.length > 0) {
            info.setLength(info.length() - 2);
        }

        info.append("\n");
        return info.toString();
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tipoRol getRol() {
        return rol;
    }

    public void setRol(tipoRol rol) {
        this.rol = rol;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String[] getAtaques() {
        return ataques;
    }

    public void setAtaques(String[] ataques) {
        this.ataques = ataques;
    }
}
