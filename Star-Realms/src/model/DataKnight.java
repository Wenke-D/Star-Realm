package model;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import model.card.GameCard;
import model.comp.GraphicPackage;
import model.io.ResourceReader;
import model.player.AiPlayer;
import model.player.Player;
import model.player.RealPlayer;

public class DataKnight {
	private int roundsNumber;
	private final Player player1;
	private final Player player2;
	private final Store store;

	private boolean attacked = false;

	public DataKnight(int mode) {
		Path configPath = Path.of("res", "config.xml");

		String paths = "/config/paths/";
		String CoreSetXPath = paths + "CoreSet";
		String startDeckSetXPath = paths + "StartSet";
		String ExplorerXPath = paths + "Explorer";
		ResourceReader reader = new ResourceReader();

		// Read resource file location
		String CoreSetPath = reader.getAttributeValue(configPath.toFile(), CoreSetXPath, "path");
		String StartSetPath = reader.getAttributeValue(configPath.toFile(), startDeckSetXPath, "path");
		String ExplorerPath = reader.getAttributeValue(configPath.toFile(), ExplorerXPath, "path");

		GameCard explorer = reader.makeGameCardFromFile(new File(ExplorerPath));
		List<GameCard> storeDeck = reader.makeCardListFromFile(new File(CoreSetPath));
		List<GameCard> deck1 = reader.makeCardListFromFile(new File(StartSetPath));
		List<GameCard> deck2 = reader.makeCardListFromFile(new File(StartSetPath));

		roundsNumber = 1;
		store = new Store(storeDeck, explorer);
		player1 = new RealPlayer(51, 51, 50, deck1);

		if (mode == 1)
			player2 = new AiPlayer(0, 0, 50, deck2);
		else
			player2 = new RealPlayer(0, 0, 50, deck2);

		player1.drawCard(3);
	}

	/**
	 * get current player
	 * 
	 * @return a {@code GamePlayer}
	 */
	private Player currentGamePlayer() {
		return roundsNumber % 2 == 0 ? player2 : player1;
	}

	/**
	 * get the opponent of current player
	 * 
	 * @return a {@code GamePlayer}
	 */
	private Player opponent() {
		return roundsNumber % 2 == 1 ? player2 : player1;
	}

	/**
	 * DataKnight control player to execute player's order.
	 */
	public void execute(String order) {
		Player curPlayer = currentGamePlayer();
		Player opponent = opponent();

		/**
		 * Split order and it's parameters
		 */
		String[] cmds = order.split(" ");
		switch (cmds[0]) {

		case "end": {
			curPlayer.endTurn();
			curPlayer.drawCard(5);

			// ResetState()
			attacked = false;
			opponent.beginTurn();
			switchPlayer();
			break;
		}

		case "attack": {
			if (!attacked) {
				curPlayer.attack(opponent);
				attacked = true;
			}
			break;
		}

		case "play": {
			int index = Integer.valueOf(cmds[1]);
			GameCard card = curPlayer.put(index);
			curPlayer.active(card, "basic", opponent, store);
			break;
		}

		case "buy": {
			int index = Integer.valueOf(cmds[1]);
			GameCard card = store.get(index);
			if (curPlayer.canAfford(card.getCost())) {
				store.remove(index);
				curPlayer.get(card);
			}
			break;

		}

		case "active": {
			int cardIndex = Integer.valueOf(cmds[1]);
			String type = cmds[2];
			curPlayer.active(cardIndex, type, opponent, store);
			break;
		}
		}
	}

	public boolean isEnd() {
		return player1.isDead() || player2.isDead();
	}

	private void switchPlayer() {
		roundsNumber += 1;
	}

	public GraphicPackage getGraphicData() {
		String winner;
		if (isEnd()) {
			winner = player1.isDead() ? "Player2" : "Player1";
		} else {
			winner = null;
		}
		GraphicPackage p = new GraphicPackage(currentGamePlayer(), opponent(), store, winner);
		return p;
	}

	public void free() {
		;
	}

}
