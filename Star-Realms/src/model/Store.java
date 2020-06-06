package model;

import java.util.ArrayList;
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

	private final ArrayList<GameCard> cards;
	private final GameCard explorer;

	/**
	 * <p>
	 * This constructor allow us to create a store by giving a qualified card deck
	 * and a card Explorer.
	 * </p>
	 * 
	 * <p>
	 * Once the store has been created, we can request 6 cards from this store.
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
	 * This method allows us to visualize all the visible cards
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (GameCard c : peek()) {
			sb.append("_____________________________\n");
			sb.append("[" + i + "]" + c.toString() + '\n');
			i++;
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * This method return six available cards, the last one will always be the
	 * Explorer.
	 * </p>
	 * 
	 * <p>
	 * This method selects last five card in "cardsToSell" as cards available.
	 * </p>
	 * 
	 * 
	 * @return {@code ArrayList} contains 6 GameCard
	 */
	public ArrayList<GameCard> peek() {
		ArrayList<GameCard> cardsVisible = new ArrayList<GameCard>();
		cardsVisible.add(explorer);
		return cardsVisible;
	}

	public GameCard get(int i) {
		int last = cards.size();
		return cards.get(last-i);
	}
	
	public GameCard remove(int i) {
		int last = cards.size();
		return cards.remove(last-i);
	}


}
