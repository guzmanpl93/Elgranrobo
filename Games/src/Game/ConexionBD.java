package Game;

import java.sql.*;
import java.util.ArrayList;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/juego_robo";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // GUARDAR CLASIFICACIÓN
    public static void guardarClasificacion(ArrayList<String> podio) {
        String sql = "INSERT INTO clasificacion (posicion, equipo) VALUES (?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < podio.size(); i++) {
                stmt.setInt(1, i + 1);
                stmt.setString(2, podio.get(i));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // OBTENER HISTORIAL
    public static ArrayList<String> obtenerHistorial() {
        ArrayList<String> historial = new ArrayList<>();
        String sql = "SELECT posicion, equipo FROM clasificacion ORDER BY id DESC LIMIT 30";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                historial.add("Posición " + rs.getInt("posicion") + ": " + rs.getString("equipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historial;
    }

    public static ArrayList<String> obtenerJugadoresRecientes() {
        ArrayList<String> jugadores = new ArrayList<>();
        String sql = "SELECT nombre, rol, personaje, equipo FROM jugadores ORDER BY id DESC LIMIT 30";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                jugadores.add(rs.getString("nombre") + " - " + rs.getString("rol") + " - " + rs.getString("personaje") + " - " + rs.getString("equipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugadores;
    }

}
