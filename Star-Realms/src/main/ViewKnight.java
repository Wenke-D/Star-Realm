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
		System.out.println("##############");
		System.out.println("# Star Realm #");
		System.out.println("##############");
		System.out.println("[1] Player VS Computer");
		System.out.println("[2] Player VS Player");
		System.out.print("Votre choix :");
	}

	/**
	 * Print text in the screen
	 * 
	 * @param message
	 */
	public void drawMessage(String message) {
		painter.drawMessage(message);
	}

	/**
	 * Display the game result by getting winner info from data
	 * 
	 * @param data Interface between view and data.
	 */
	public void displayResult(GraphicPackage data) {
		System.out.println("***********");
		System.out.println(data.getWiner());
		System.out.println("***********");

	}

	/**
	 * Asking user weather his wants to begin the game again
	 * 
	 * @return {@code true} if he want, {@code false} if not
	 */
	public boolean askRestart() {
		System.out.println("Restart? yes or no: ");
		String answer = "";
		if (scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer.equals("yes");

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

}
