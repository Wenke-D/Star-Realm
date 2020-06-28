package model;

import projet.model.Player;

public class PlayerNonAttack implements State {
	private Player currentPlayer;
	private Player opponent;
	private Store store;
	private Data data;
	
	public PlayerNonAttack(Player currentPlayer, Player opponent, Store store, Data data) {
		super();
		this.currentPlayer = currentPlayer;
		this.opponent = opponent;
		this.store = store;
		this.data = data;
	}

	@Override
	public void use(int i) {
		currentPlayer.use();
		
	}

	@Override
	public void active(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buy(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackBase(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
