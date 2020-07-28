package model.comp;

import model.card.Card;

import java.util.List;

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
	 * @return int number of blob played in this round
	 */
	public int nbBlob();

	/**
	 * Scrap cards in trade row specified by index
	 *
	 * Only store support this function
	 *
	 * @param indexes card's index in trade row, begin from 1.
	 */
    public void scrapCards(List<Integer> indexes);

	public void destroyBases(List<Integer> inputs);

    public int numberOfBlobPlayed();
}
