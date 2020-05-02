import java.net.MalformedURLException;
import java.net.URL;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.Properties;
import java.io.*;
import java.net.*;


public class SnakeRunner {

	/**
	 * Runner for a PokeBoard PokemonCode demo
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		// The board knows how big it is -- not necessary to set rows/columns
		Board board = new SnakeBoard();
		
		String songName = "Wild_Pogo.mp3";
		String pathToMp3 = System.getProperty("..\\Snake") + songName;
		BasicPlayer player = new BasicPlayer();
		try {
			Class currClass = Class.forName("SnakeRunner");
			URL classURL = currClass.getResource("SnakeRunner.class");
			URL fileURL = new URL(classURL,"../music/" + songName);
			player.open(fileURL);
		      
			player.play();
		} 
		catch (BasicPlayerException | MalformedURLException e) {
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
				
		// Create the gui and use it to run the game.
		Gui gui = new Gui(board, board.getRows(), board.getColumns(), "SSSSSSSSSSnake");
		gui.setTextWindow("Welcome to AP Snake");
		gui.displayGame();
	}
}
