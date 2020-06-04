package main;

import java.util.Scanner;

import fr.umlv.zen5.*;

public class ControlKnight {
	static void game(ApplicationContext context) {		
	}
	
	static void termalGame() {
		
		ViewKnight view = new ViewKnight();
		view.drawMenu();
		Scanner scanner = new Scanner(System.in);
		
		String choix = "";
		while(true) {
			if(scanner.hasNextLine())
				choix = scanner.nextLine();
			if(choix.equals("1") || choix.equals("2"))
				break;
			System.out.println("Invalide command!");
		}
		int mode = Integer.valueOf(choix);
		DataKnight data = new DataKnight(mode);
		view.prepare();	
		view.drawTerminal(data);
		
		
		
		/**
		 * Loop of update view
		 */
		while(true) {
			
			data.playing();
			view.drawTerminal(data);
			
			if(data.isEnd()) {
				
				System.out.println(data.resultMessage());
				System.out.print("Restart? yes or no: ");
				if(scanner.hasNextLine()) {
					String cmd = scanner.nextLine();
					if(cmd.equals("yes")) {
						data = new DataKnight(mode);
						view.drawTerminal(data);
					}
					else {
						break;
					}
				}
				
				
			}
		}
		scanner.close();
		System.out.println("Game finish");
		System.out.println("-----------------");
	}
	
	public static void main(String[] args) {
		//Application.run(Color.white, ControlKnight::game);
		termalGame();
	}
	
}
