 package Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CREAR CONEXION CAON LA BASE DE DATOS
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/juego_robo";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

//    public static Connection conectar() {
//        try {
//            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null; 
//        }
//    }
    private static final String NOMBRE_BD = "juego_robo";
    private static final String URL_CON_BD = URL + NOMBRE_BD;

    // Conecta sin base seleccionada (para crearla)
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Conecta directamente a la base de datos
    public static Connection conectarBD() {
        try {
            return DriverManager.getConnection(URL_CON_BD, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Crear base y tablas si no existen
    public static void crearBaseDeDatosYTablas() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + NOMBRE_BD);
            stmt.executeUpdate("USE " + NOMBRE_BD);

            String sqlClasificacion = "CREATE TABLE IF NOT EXISTS clasificacion (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "posicion INT, " +
                    "equipo VARCHAR(255))";

            String sqlJugadores = "CREATE TABLE IF NOT EXISTS jugadores (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "rol VARCHAR(255), " +
                    "personaje VARCHAR(255), " +
                    "equipo VARCHAR(255))";

            String sqlPartida = "CREATE TABLE IF NOT EXISTS partida_guardada (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "ronda INT NOT NULL, " +
                    "equipos TEXT NOT NULL, " +
                    "fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

            stmt.executeUpdate(sqlClasificacion);
            stmt.executeUpdate(sqlJugadores);
            stmt.executeUpdate(sqlPartida);

        } catch (SQLException e) {
            e.printStackTrace();
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

    // OBTENER JUGADORES RECIENTES
    public static ArrayList<String> obtenerJugadoresRecientes() {
        ArrayList<String> jugadores = new ArrayList<>();
        String sql = "SELECT nombre, rol, personaje, equipo FROM jugadores ORDER BY id DESC LIMIT 30";

        try (Connection conn = conectarBD(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                jugadores.add(rs.getString("nombre") + " - " + rs.getString("rol") + " - " +
                              rs.getString("personaje") + " - " + rs.getString("equipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugadores;
    }
    
//  CARGAR ÚLTIMA PARTIDA
  public static Partida cargarUltimaPartida() {
      String sql = "SELECT ronda, equipos FROM partida_guardada ORDER BY fecha DESC LIMIT 1";
      try (Connection conn = conectarBD(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
          if (rs.next()) {
              int ronda = rs.getInt("ronda");
              List<String> equipos = Arrays.asList(rs.getString("equipos").split(","));
              return new Partida(new ArrayList<>(equipos));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return null;
  }

}
