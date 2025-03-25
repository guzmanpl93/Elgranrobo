package Game;

import java.util.ArrayList;

public class Equipo {

    private String nombre;
    private ArrayList<Rol> personajes; // Lista de personajes del equipo (puede incluir Espías o Guardias)

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    // Añadir un personaje al equipo
    public void añadirPersonaje(Rol personaje) {
        this.personajes.add(personaje);
    }

    // Crear y añadir espías al equipo
    public void agregarEspia(String nombre, String rol, int vida, int ataque, String[] ataques) {
        personajes.add(new Rol(nombre, rol, vida, ataque, ataques));
    }

    // Crear y añadir guardias al equipo
    public void agregarGuardia(String nombre, String rol, int vida, int ataque, String[] ataques) {
        personajes.add(new Rol(nombre, rol, vida, ataque, ataques));
    }

    // Obtener información detallada del equipo y sus personajes
    public String obtenerInformacion() {
        StringBuilder info = new StringBuilder("Equipo: ").append(nombre).append("\n");
        for (Rol rol : personajes) {
            info.append("- ").append(rol.obtenerInformacion()).append("\n");
        }
        return info.toString();
    }

    public ArrayList<Rol> getPersonajes() {
        return personajes;
    }

    public String getNombre() {
        return nombre;
    }
}
