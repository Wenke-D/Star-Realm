package main;

/**
 * The controller of the game.
 * 
 * @author Matth
 *
 */
public class ControlKnight {
	/**
	 * the game mode 1 => PVP 2 => PVE
	 * this attribute affect the data initialization in begin phrase
	 * and end phrase of the game
	 */
	private int mode;

	/**
	 * The date of game
	 */
	private DataKnight data;

	/**
	 * The view
	 */
	private ViewKnight view;

	/**
	 * Create a view while create a Controller
	 */
	public ControlKnight() {
		view = new ViewKnight();
	}

	public void game() {
		view.drawMenu();
		setMode();
		
		data = new DataKnight(mode);
		view.draw(data);
		mainLoop();
		
		view.drawMessage("Game finish");
		view.drawMessage("-----------------");
	}
	
	
	/**
	 * 
	 */
	private void setMode() {
		String choix = "";
		while (true) {
			choix = view.readInput();
			if (choix.equals("1") || choix.equals("2")) {
				mode = Integer.valueOf(choix);
				break;
			}
			view.drawMessage("Invalide command!");
		}
	}
	
	/**
	 * Center loop of the game
	 */
	private void mainLoop() {
		while (true) {
			
			/**
			 * Let data decide weather need input
			 */
			String order = null;
			if (data.needInput()) {
				order = view.readInput();
				if (order.equals("quit"))
					break;
			}
			data.playing(order);

			// update view
			view.draw(data);

			// if game ends
			if (data.isEnd()) {
				view.displayResult(data);
				if (view.askRestart()) {
					data = new DataKnight(mode);
					view.draw(data);
				} else {
					break;
				}
			}

		}
	}

}
