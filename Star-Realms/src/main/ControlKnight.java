package main;

import java.util.List;

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
		view.displayGameMenu();
		mode = setMode();

		data = new DataKnight(mode);
		view.displayOrUpdateContent(data.getGraphicData());

		loop();

		view.displayGameFinish();

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
			view.displayBadInputAlert();
		}
	}

	/**
	 * Center loop of the game
	 */
	private void loop() {
		while (true) {

			if (data.needInput()) {
				String order = view.readInput();
				if (order.equals("quit")) {
					break;
				} else if (order.equals("help")) {
					view.displayHelpMessage();
					continue;
				}

				List<String> extraInfoType = data.needExtraInput(order);
				if (extraInfoType != null) {
					String extraInfo = view.readInputWithMessages(extraInfoType);
					order = order + " " + extraInfo;
				}

				data.execute(order);
			} else {
				data.executeWithoutInput();
			}

			// update view
			view.displayOrUpdateContent(data.getGraphicData());

			// if game ends
			if (data.isEnd()) {
				view.displayResult(data.getGraphicData());
				view.displayRestartQuestion();
				if (view.readBoolean()) {
					data.free();
					data = new DataKnight(mode);
					view.displayOrUpdateContent(data.getGraphicData());
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
