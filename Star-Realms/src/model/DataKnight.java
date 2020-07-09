package model;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	public static final State player1NonAttack;
	public static final State player2NonAttack;
	public static final State player1Attack;
	public static final State player2Attack;
	
	ResourceReader reader = new ResourceReader();

	private boolean attacked = false;

	public DataKnight(int mode) {
		Path configPath = Path.of("res", "config.xml");

		String rootPath = "/config/paths/";
		String StoreSetXPath = rootPath + "StoreSet";
		String startDeckSetXPath = rootPath + "StartSet";
		String ExplorerXPath = rootPath + "Explorer";

		List<Card> storeDeck = makeCardListFromFile(configPath.toFile(), StoreSetXPath);
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
		this.player1NonAttack = new ;
		
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

			ArrayList<String> extraInfos = new ArrayList<String>();
			for (int i = 2; i < cmds.length; i++) {
				extraInfos.add(cmds[i]);
			}
			curPlayer.active(card, "basic", opponent, store, extraInfos);
			break;
		}

		case "buy": {
			int index = Integer.valueOf(cmds[1]);
			Card card = store.get(index);
			if (curPlayer.canAfford(card.getCost())) {
				curPlayer.pay(card.getCost());
				store.remove(index);
				curPlayer.addToDiscardPile(card);
			}
			break;

		}

		case "active": {
			int cardIndex = Integer.valueOf(cmds[1]);
			String type = cmds[2];

			ArrayList<String> extraInfos = new ArrayList<String>();
			for (int i = 3; i < cmds.length; i++) {
				extraInfos.add(cmds[i]);
			}

			curPlayer.active(cardIndex, type, opponent, store, extraInfos);
			break;
		}

		case "attackBase": {
			int cardIndex = Integer.valueOf(cmds[1]);
			int combatPoint = curPlayer.getCombat();
			if (opponent.baseIsDestory(cardIndex, combatPoint)) {
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
		GraphicPackage p = new GraphicPackage(currentGamePlayer(), opponent(), store, winner, roundsNumber);
		return p;
	}

	public void free() {
		;
	}

	/**
	 * While orders is play or active, it will need extra information to perform
	 * this order. if not, return null.
	 * 
	 * @param order
	 * @return
	 */
	public List<String> needExtraInput(String order) {

		/**
		 * Where store the target card. play means this card is in the hand, active
		 * means this card is in the field.
		 */
		Map<String, String> needExtraInputCmds = new HashMap<String, String>();
		needExtraInputCmds.put("play", "hand");
		needExtraInputCmds.put("active", "field");

		String[] paras = order.split(" ");
		String command = paras[0];
		if (needExtraInputCmds.containsKey(command)) {
			int cardIndex = Integer.valueOf(paras[1]);

			String abilityType = command.equals("play") ? "basic" : paras[2];
			return currentGamePlayer().needExtraInput(needExtraInputCmds.get(command), cardIndex, abilityType);
		} else {
			return null;
		}
	}

	public boolean needInput() {
		/**
		 * If not PVE mode, always need input
		 */
		if (!(player2 instanceof AiPlayer))
			return true;

		/**
		 * If PVE mode, need input while is not ai player.
		 */
		return currentGamePlayer() != player2;
	}

	public void executeWithoutInput() {
		String order = player2.randomAction();
		execute(order);

	}

}
