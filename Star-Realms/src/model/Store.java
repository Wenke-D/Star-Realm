package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.card.Card;
import model.comp.Target;
import view.GraphicCard;
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
public class Store implements GraphicStore, Target {

	/**
	 * The last 5 cards of this array is the 5 cards of six cards that player can
	 * see.
	 */
	private final ArrayList<Card> cards;
	private final Card explorer;

	/**
	 * <p>
	 * This constructor allow us to create a store by giving a qualified card deck
	 * and a card Explorer.
	 * </p>
	 * 
	 * @param storeCards A qualified card deck, which means
	 * @param explorer   A ship object who's name is Explorer
	 */
	public Store(List<Card> storeCards, Card explorer) {
		cards = new ArrayList<Card>(storeCards);
		this.explorer = Objects.requireNonNull(explorer);
	}

	/**
	 * Get the card specific by number.
	 * 
	 * @param i the number of card in the view of player. begin with 1.
	 * @return the card.
	 */
	public Card get(int i) {
		int last = cards.size();
		return cards.get(last - i);
	}

	/**
	 * Remove the card specific by number.
	 * 
	 * @param i the number of card in the view of player. begin with 1.
	 * @return the card removed.
	 */
	@Override
	public Card remove(int i) {
		// if remove a explorer
		if (i == cards().size())
			return explorer;

		int last = cards.size();
		return cards.remove(last - i);
	}

	@Override
	public List<GraphicCard> cards() {
		ArrayList<Card> cardsToPrint = new ArrayList<Card>();
		int length = cards.size() < 5 ? cards.size() : 5;

		for (int i = 1; i <= length; i++) {
			cardsToPrint.add(get(i));
		}
		cardsToPrint.add(explorer);
		return Collections.unmodifiableList(cardsToPrint);

	}

	@Override
	public void changeAuthority(int number) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void changeCombat(int number) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void changeTrade(int number) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawCard(int number) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToDeck(Card card) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int nbBlob() {
		throw new UnsupportedOperationException();

	}

}
