package main;

import java.util.Scanner;

import model.comp.GraphicPackage;
import view.Painter;

public class ViewKnight {
	private final int pause = 1000;
	private final Scanner scanner = new Scanner(System.in);
	Painter painter;

	public ViewKnight() {
	}

	public void prepare() {
		try {
			System.out.println("===================");
			System.out.println("CHARGING GAME...");
			Thread.sleep(pause);
			System.out.println("DATA YES");
			Thread.sleep(pause);
			System.out.println("VIEW YES");
			Thread.sleep(pause);
			System.out.println("===================");
			System.out.println("GAME Start !!!");
			System.out.println("===================");
			Thread.sleep(pause);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void drawTerminal(GraphicPackage data) {
		if (data.needUpdate()) {
			painter = new Painter(data);
			painter.paintRoundNumber();
			System.out.println("-----------------");
			System.out.println("Opponent");
			painter.paintOpponent();

			System.out.println("-----------------");
			System.out.println("Trade Row");
			painter.paintStore();

			System.out.println("-----------------");
			System.out.println("Current Player");
			painter.paintCurPlayer();
			System.out.print(">>>");
		}
		data.log().paint();
		;
	}

	public void drawMenu() {
		System.out.println("##############");
		System.out.println("# Star Realm #");
		System.out.println("##############");
		System.out.println("[1] Player VS Computer");
		System.out.println("[2] Player VS Player");
		System.out.print("Votre choix :");
	}

	public void drawMessage(String message) {
		System.out.println(message);

	}

	public void displayResult(GraphicPackage data) {
		System.out.println("***********");
		System.out.println(data.getWiner());
		System.out.println("***********");

	}
	
	/**
	 * Asking user weather his wants to begin the game again
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
	
	
	public String readInput() {
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return null;
	}

}
