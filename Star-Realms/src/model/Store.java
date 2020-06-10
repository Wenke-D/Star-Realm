package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.card.GameCard;
import view.GraphicStore;

/**
 * <p>
 * This class allows us to manage the shop of the game.
 * </p>
 * <p>
 * The three items we need are:
 * </p>
 * <ul>
 * <li>the pile of remaining cards : Attribute "cardsToSell".</li>
 * <li>the 6 cards that players can buy : Method "availableCards".</li>
 * <li>the infinite deck of Explorer cards : Attribute Explorer, every time the
 * available cards showed, we add a explorer in it.</li>
 * </ul>
 *
 * @author Vincent
 *
 */
public class Store implements GraphicStore{
	
	/**
	 * The last 5 cards of this array is the 5 cards of six cards that player can see.
	 */
	private final ArrayList<GameCard> cards;
	private final GameCard explorer;

	/**
	 * <p>
	 * This constructor allow us to create a store by giving a qualified card deck
	 * and a card Explorer.
	 * </p>
	 * 
	 * @param storeCards A qualified card deck, which means
	 * @param explorer   A ship object who's name is Explorer
	 */
	public Store(List<GameCard> storeCards, GameCard explorer) {
		cards = new ArrayList<GameCard>(storeCards);
		this.explorer = Objects.requireNonNull(explorer);
	}
	
	/**
	 * Get the card specific by number.
	 * 
	 * @param i the number of card in the view of player. begin with 1.
	 * @return the card.
	 */
	public GameCard get(int i) {
		int last = cards.size();
		return cards.get(last-i);
	}
	
	/**
	 * Remove the card specific by number.
	 * @param i the number of card in the view of player. begin with 1.
	 * @return the card removed.
	 */
	public GameCard remove(int i) {
		int last = cards.size();
		return cards.remove(last-i);
	}

	@Override
	public List<GameCard> cards() {
		ArrayList<GameCard> cards = new ArrayList<GameCard>();
		for(int i =1; i<=5;i++) {
			cards.add(get(i));
		}
		cards.add(explorer);
		return Collections.unmodifiableList(cards);
		
	}


}
