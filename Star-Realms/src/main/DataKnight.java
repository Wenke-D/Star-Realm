package main;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import model.Log;
import model.Store;
import model.card.GameCard;
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
	private boolean update = true;

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
		
		if(mode == 1)
			player2 = new AiPlayer(0, 0, 50, reader.makeCardListFromFile(new File(StartSetPath)));
		else
			player2 = new RealPlayer(0, 0, 50, reader.makeCardListFromFile(new File(StartSetPath)));

		player1.draw3();
	}

	/**
	 * get current player
	 * 
	 * @return a {@code GamePlayer}
	 */
	public Player currentGamePlayer() {
		return roundsNumber % 2 == 0 ? player2 : player1;
	}

	/**
	 * get the opponent of current player
	 * 
	 * @return a {@code GamePlayer}
	 */
	public Player opponent() {
		return roundsNumber % 2 == 1 ? player2 : player1;
	}
	
	/**
	 * Flag is 1 means that this player end his turn.
	 */
	public void playing() {
		update = true;
		Player curPlayer = currentGamePlayer();
		int flag = curPlayer.playGame(store, opponent(), log);
		if (flag == 1) {
			opponent().prepare();
			roundsNumber++;
		}
		if (flag == -1) {
			update = false;
		}

	}

	public boolean isEnd() {
		return player1.isDead() || player2.isDead();
	}

	public String resultMessage() {
		return String.format("Winner: %s\nLoser: %s", player1.isDead() ? "Player2" : "Player1",
				player1.isDead() ? "Player1" : "Player2");
	}

	/**
	 * Interface Graphic package
	 */
	@Override
	public int getCurAuthority() {
		return currentGamePlayer().getAuthority();
	}

	@Override
	public int getCurTrade() {
		return currentGamePlayer().getTrade();
	}

	@Override
	public int getCurCombat() {
		return currentGamePlayer().getCombat();
	}

	@Override
	public Graphic getCurhands() {
		return currentGamePlayer().getHands();
	}

	@Override
	public Graphic getCurField() {
		return currentGamePlayer().getField();
	}

	@Override
	public Graphic getCurDeck() {
		return currentGamePlayer().getDeck();
	}

	@Override
	public Graphic getCurDisCardPile() {
		return currentGamePlayer().getDisCardPile();
	}

	@Override
	public Graphic getStore() {
		return store;
	}

	@Override
	public int getOppAuthority() {
		return opponent().getAuthority();
	}

	@Override
	public int getOppTrade() {
		return opponent().getTrade();
	}

	@Override
	public int getOppCombat() {
		return opponent().getCombat();
	}

	@Override
	public Graphic getOppField() {
		return opponent().getField();
	}

	@Override
	public Graphic log() {
		return log;
	}

	@Override
	public boolean needUpdate() {
		return update;
	}

	@Override
	public int getRound() {
		return roundsNumber;
	}

}
