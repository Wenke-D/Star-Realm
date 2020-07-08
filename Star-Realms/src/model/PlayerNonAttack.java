package model;

import model.player.Player;

public class PlayerNonAttack implements State {
	private Player currentPlayer;
	private Player opponent;
	private Store store;
	private DataKnight data;
	
	public PlayerNonAttack(Player currentPlayer, Player opponent, Store store, DataKnight data) {
		super();
		this.currentPlayer = currentPlayer;
		this.opponent = opponent;
		this.store = store;
		this.data = data;
	}
	/**
	 * play this card and active the basic functions
	 */
	@Override
	public void use(int i) {
		currentPlayer.put(i);
		currentPlayer.active(i, "basic", opponent, store, extraInfos);
		
	}

	/**
	 * active special functions (allay or scrape)
	 */
	@Override
	public void active(int i) {
		currentPlayer.active(i, type, opponent, store, extraInfos);
		
	}

	@Override
	public void buy(int i) {
		int price = store.get(i).getCost();
		if(currentPlayer.canAfford(price)) {
			currentPlayer.pay(price);
		}
	}

	@Override
	public void attackPlayer() {
		currentPlayer.attack(opponent);
		
	}

	@Override
	public void attackBase(int i) {
		int damage = currentPlayer.getCombat();
		if(opponent.baseIsDestory(i, damage)) {
			opponent.destoryCard(i);
		}	
	}

	@Override
	public void end() {
		currentPlayer.endTurn();
		currentPlayer.drawCard(5);

		// ResetState()
		opponent.beginTurn(currentPlayer, store);
		//switchPlayer();
		
	}
	
	
	
	

}
