package model.comp;

import model.card.GameCard;

public interface Buyer {
	
	/**
	 * 
	 * @param c
	 */
	public void get(GameCard c);
	
	public void pay(int price);
	
	public boolean afford(int price);
}
