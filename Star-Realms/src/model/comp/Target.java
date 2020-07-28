package model.comp;

import model.card.Card;

public interface Target {

	public void changeAuthority(int number);

	public void changeCombat(int number);

	public void changeTrade(int number);

	public void drawCard(int number);
	
	/**
	 * Remove permanently a card
	 * @param i index begins from 1
	 * @return TODO
	 */
	public Card remove(int i);

	public void addToDeck(Card card);
	
	/**
	 * Return number of blob played this round
	 * @return
	 */
	public int nbBlob();

	/**
	 * Scrap a card in trade row specified by index
	 *
	 * Only store support this function
	 *
	 * @param index card's index in trade row, begin from 1.
	 */
    public void scrap(int index);
}
