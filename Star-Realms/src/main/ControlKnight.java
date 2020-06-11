package main;

import model.DataKnight;
import view.ViewKnight;

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
		view.updateView(data.getGraphicData());

		if (mode == 2) {
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
	 * Ask the player to set the game mode
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
			view.updateView(data.getGraphicData());

			// if game ends
			if (data.isEnd()) {
				view.displayResult(data.getGraphicData());
				view.askRestart();
				if (view.check()) {
					data = new DataKnight(mode);
					view.updateView(data.getGraphicData());
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
