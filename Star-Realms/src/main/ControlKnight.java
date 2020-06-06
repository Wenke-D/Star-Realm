package main;

/**
 * The controller of the game.
 * 
 * @author Matth
 *
 */
public class ControlKnight {
	/**
	 * the game mode 1 => PVP 2 => PVE this attribute affect the data initialization
	 * in begin phrase and end phrase of the game
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
	 * Create a view
	 */
	public ControlKnight() {
		view = new ViewKnight();
	}

	/**
	 * Main body of game
	 */
	public void game() {
		view.drawMenu();
		mode = setMode();

		data = new DataKnight(mode);
		view.updateView(data);

		if (mode == 1) {
			mainLoopPVP();
		}

		view.finishGame();
		
		/**
		 * Release sources
		 */
		view.free();
		data.free();
		this.free();
	}


	/**
	 * 
	 */
	private int setMode() {
		String choix = "";
		while (true) {
			choix = view.readInput();
			if (choix.equals("1") || choix.equals("2")) {
				return Integer.valueOf(choix);
			}
			view.badInput();
		}
	}

	/**
	 * Center loop of the game
	 */
	private void mainLoopPVP() {
		while (true) {

			String order = view.readInput();
			if (order.equals("quit")) {
				break;
			}
			data.execute(order);

			// update view
			view.updateView(data);

			// if game ends
			if (data.isEnd()) {
				view.displayResult(data);
				if (view.askRestart()) {
					data = new DataKnight(mode);
					view.updateView(data);
				} else {
					break;
				}
			}

		}
	}
	
	/**
	 * Nothing to free
	 */
	private void free() {
		;
		
	}

}
