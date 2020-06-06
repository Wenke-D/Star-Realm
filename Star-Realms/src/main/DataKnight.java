package main;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import model.Log;
import model.Store;
import model.card.GameCard;
import model.card.ability.Ability;
import model.comp.Graphic;
import model.comp.GraphicPackage;
import model.io.ResourceReader;
import model.player.AbstractPlayer;
import model.player.AiPlayer;
import model.player.Player;
import model.player.RealPlayer;

public class DataKnight implements GraphicPackage {
	private int roundsNumber;
	private final Player player1;
	private final Player player2;
	private final Store store;
	private Log log = new Log();

	private boolean attacked = false;

	public DataKnight(int mode) {
		Path configPath = Path.of("res", "config.xml");

		String paths = "/config/paths/";
		String CoreSetXPath = paths + "CoreSet";
		String StartSetXPath = paths + "StartSet";
		String ExplorerXPath = paths + "Explorer";
		ResourceReader reader = new ResourceReader();

		String CoreSetPath = reader.getAttributeValue(configPath.toFile(), CoreSetXPath, "path");
		String StartSetPath = reader.getAttributeValue(configPath.toFile(), StartSetXPath, "path");
		String ExplorerPath = reader.getAttributeValue(configPath.toFile(), ExplorerXPath, "path");

		GameCard explorer = reader.makeGameCardFromFile(new File(ExplorerPath));

		roundsNumber = 1;
		store = new Store(reader.makeCardListFromFile(new File(CoreSetPath)), explorer);
		player1 = new RealPlayer(51, 51, 50, reader.makeCardListFromFile(new File(StartSetPath)));

		if (mode == 1)
			player2 = new AiPlayer(0, 0, 50, reader.makeCardListFromFile(new File(StartSetPath)));
		else
			player2 = new RealPlayer(0, 0, 50, reader.makeCardListFromFile(new File(StartSetPath)));

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
	 * Flag is 1 means that this player end his turn.
	 */
	public void execute(String order) {
		update = true;
		Player curPlayer = currentGamePlayer();
		Player opponent = opponent();
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
		
		case "play":{
			int index = Integer.valueOf(cmds[1]);
			GameCard card = curPlayer.put(index);
			Ability ability = card.getBasicAbility();
			ability.affect(curPlayer, opponent, store);
			break;
		}
		
		case "buy":
			int index = Integer.valueOf(cmds[1]);
			GameCard card = store.get(index);
			if(curPlayer.afford(card.getCost())) {
				store.remove(index);
				curPlayer.get(card);
			}

		}

	}

	public boolean isEnd() {
		return player1.isDead() || player2.isDead();
	}

	private void switchPlayer() {
		roundsNumber +=1;
	}

	

}
