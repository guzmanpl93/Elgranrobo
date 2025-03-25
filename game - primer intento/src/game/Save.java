package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Save extends JFrame {
    private static final String SAVE_FILE = "game_save.dat";
    private GameState gameState;

    public Save(GameState gameState) {
        this.gameState = gameState;
    }

    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(gameState);
            JOptionPane.showMessageDialog(null, "Juego guardado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            gameState = (GameState) in.readObject();
            JOptionPane.showMessageDialog(null, "Juego cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo cargar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}

class GameState implements Serializable{
	private static final long serialVersionUID = 1L;
    private int playerScore;
    private int level;
    
    public GameState() {
        this.playerScore = 0;
        this.level = 1;
    }
    
    public int getPlayerScore() { return playerScore; 
    }
    public void setPlayerScore(int score) { 
    	this.playerScore = score; }
    
    public int getLevel() { return level;
    }
    public void setLevel(int level) { 
    	this.level = level; 
    	}
}
