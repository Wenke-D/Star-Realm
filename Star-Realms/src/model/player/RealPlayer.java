package model.player;

import java.util.List;
import java.util.Scanner;

import model.Log;
import model.Store;
import model.card.GameCard;

public class RealPlayer extends AbstractPlayer {

	private Scanner cmdScanner;

	public RealPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
		cmdScanner = new Scanner(System.in);

	}

	@Override
	public int playGame(Store store, Player opponent, Log log) {
		String cmd = cmdScanner.nextLine();
		return excute(cmd, store, opponent, log);
	}

	/**
	 * parse and execute player's orders from terminal TODO not finished
	 * 
	 * @param cmd
	 * @param store
	 * @param opponent
	 * @return -1 means no need to update view.
	 */
	private int excute(String commande, Store store, Player opponent, Log log) {

		String[] cmds = commande.split(" ");
		String cmd = cmds[0];

		try {

			switch (cmd) {

			// end turn
			case "end":
				endTurn();
				return 1;

			// Play a card from hand
			case "use": {
				if (cmds[1].equals("*")) {
					useAll();
					return 2;
				} else {
					int index = Integer.parseInt(cmds[1]) - 1;
					use(index);
					return 3;
				}
			}

			// Buy a card
			case "buy": {
				int index = Integer.parseInt(cmds[1]) - 1;
				store.sell(index, this);
				return 4;
			}

			// Attack a player or a base.
			case "attack": {
				if (cmds[1].equals("player")) {
					attack(opponent);
					return 5;
				} else {
					int index = Integer.parseInt(cmds[1]) - 1;
					attack(index, opponent);
					return 6;
				}

			}

			// active ally/scrap cardIndex
			case "active": {
				String type = cmds[1];
				int index = Integer.parseInt(cmds[2]) - 1;
				active(index, type, this);
				return 7;
			}

			// print help info
			case "help":
				log.add("<<use index/*>> to use a/all card(s)\n"
						+ "<<attack player/index>> to attack player/a base\n"
						+ "<<active ally/scrap index>> to active a ability"
						+ "<<buy index>> to buy a card\n"
						+ "<<end>> to end your turn");
				return -1;

			default: {
				log.add("Unknown command");
				return -1;
			}

			}

		} catch (NumberFormatException e) {
			log.add("Invalide Command");
			return -1;
		} catch (IndexOutOfBoundsException e) {
			log.add("Invalide Command");
			return -1;
		}

	}

}
