package model;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.card.Card;
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
	ResourceReader reader = new ResourceReader();
	
	

	private boolean attacked = false;

	public DataKnight(int mode) {
		Path configPath = Path.of("res", "config.xml");

		String rootPath = "/config/paths/";
		String CoreSetXPath = rootPath + "CoreSet";
		String startDeckSetXPath = rootPath + "StartSet";
		String ExplorerXPath = rootPath + "Explorer";

		List<Card> storeDeck = makeCardListFromFile(configPath.toFile(), CoreSetXPath);
		List<Card> deck1 = makeCardListFromFile(configPath.toFile(), startDeckSetXPath);
		List<Card> deck2 = makeCardListFromFile(configPath.toFile(), startDeckSetXPath);

		String ExplorerPath = reader.getAttributeValue(configPath.toFile(), ExplorerXPath, "path");
		Card explorer = reader.makeGameCardFromFile(new File(ExplorerPath));

		roundsNumber = 1;
		store = new Store(storeDeck, explorer);
		player1 = new RealPlayer(0, 0, 50, deck1);

		if (mode == 1)
			player2 = new AiPlayer(0, 0, 50, deck2);
		else
			player2 = new RealPlayer(0, 0, 50, deck2);

		player1.drawCard(3);
		player2.drawCard(5);
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
			opponent.beginTurn(curPlayer, store);
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
			Card card = curPlayer.put(index);
			curPlayer.active(card, "basic", opponent, store);
			break;
		}

		case "buy": {
			int index = Integer.valueOf(cmds[1]);
			Card card = store.get(index);
			if (curPlayer.canAfford(card.getCost())) {
				curPlayer.pay(card.getCost());
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
		
		case "attackBase":{
			int cardIndex = Integer.valueOf(cmds[1]);
			int combatPoint = curPlayer.getCombat();
			if(opponent.baseIsDestory(cardIndex, combatPoint)) {
				opponent.destoryCard(cardIndex);
			}
		}
		}
	}

	public boolean isEnd() {
		return player1.isDead() || player2.isDead();
	}

	private void switchPlayer() {
		roundsNumber += 1;
	}

	/**
	 * Return a random player's deck
	 * 
	 * @param f,    configure file
	 * @param XPath Attribute of card resource file's path
	 * @return
	 */
	private List<Card> makeCardListFromFile(File f, String XPath) {
		String FilePath = reader.getAttributeValue(f, XPath, "path");
		List<Card> cardList = reader.makeCardListFromFile(new File(FilePath));
		Collections.shuffle(cardList);
		return cardList;
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
