package main;

import java.util.Scanner;

import model.comp.GraphicPackage;
import view.Painter;

public class ViewKnight {
	private final Scanner scanner;
	private final Painter painter;

	public ViewKnight() {
		 scanner = new Scanner(System.in);
		 painter = new Painter();
	}

	/**
	 * Interface during game
	 * 
	 * @param data game data source
	 */
	public void updateView(GraphicPackage data) {
		painter.updateView(data);
	}

	/**
	 * Print the game menu in the screen
	 */
	public void drawMenu() {
		painter.menu();
	}

	/**
	 * Display the game result by getting winner info from data
	 * 
	 * @param data Interface between view and data.
	 */
	public void displayResult(GraphicPackage data) {
		painter.result(data.getWiner());
	}

	/**
	 * Asking user weather his wants to begin the game again
	 * 
	 * @return {@code true} if he want, {@code false} if not
	 */
	public void askRestart() {
		painter.systemMessage("Restart? yes or no: ");
		
	}
	
	public boolean check() {
		String answer = "";
		if (scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer.equals("yes");
	}
	
	/**
	 * Display game is end
	 */
	public void finishGame() {
		painter.systemMessage("Game End!");
	}

	/**
	 * Read input of a line from keyboard
	 * 
	 * @return {@code String} without '\n' in the end.
	 */
	public String readInput() {
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return null;
	}
	
	/**
	 * Alert user a bad input.
	 */
	public void badInput() {
		painter.systemMessage("Bad Input");
	}

	public void free() {
		// TODO Auto-generated method stub
		
	}

}
