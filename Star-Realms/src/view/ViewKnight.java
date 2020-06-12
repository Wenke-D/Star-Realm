package view;

import java.util.Scanner;

import model.comp.GraphicPackage;

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
	public void displayOrUpdateContent(GraphicPackage data) {
		painter.playingInterface(data);
	}

	/**
	 * Print the game menu in the screen
	 */
	public void displayGameMenu() {
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
	public void displayRestartQuestion() {
		painter.importantMessage("Restart? yes or no: ");
		
	}
	
	public boolean readBoolean() {
		String answer = "";
		if (scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer.equals("yes");
	}
	
	/**
	 * Display game is end
	 */
	public void displayGameFinish() {
		painter.importantMessage("Game End!");
	}

	/**
	 * Read input of a line from keyboard
	 * 
	 * @return {@code String} without '\n' in the end.
	 */
	public String readInput() {
		System.out.print(">>> ");
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return null;
	}
	
	/**
	 * Alert user a bad input.
	 */
	public void displayBadInputAlert() {
		painter.importantMessage("Bad Input");
	}

	public void free() {
		scanner.close();
		
	}

	public void displayHelpMessage() {
		painter.help();
		
	}

}
