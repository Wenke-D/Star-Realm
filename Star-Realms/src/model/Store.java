package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.card.GameCard;
import model.comp.Buyer;
import model.comp.Graphic;
import model.comp.cardSquence.Deck;

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
public class Store implements Graphic {

	private final Deck cards;
	private final List<GameCard> availables;
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
		cards = new Deck(Objects.requireNonNull(storeCards));
		this.explorer = Objects.requireNonNull(explorer);
		availables = new ArrayList<GameCard>(5);

		/*
		 * Take out 5 cards from deck "cardsToSell" and then add them to deck
		 * "available". this is the first 5 cards which are ready to sell
		 */
		for (int i = 0; i < 5; i++) {
			availables.add(cards.pop());
		}

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
		ArrayList<GameCard> cardsVisible = new ArrayList<GameCard>(availables);
		cardsVisible.add(explorer);
		return cardsVisible;
	}

	/**
	 * <p>
	 * Sell the card based on player's choice which is the order of cards
	 * </p>
	 * 
	 * <p>
	 * One thing to pay attention, the Explorer giving back is the reference of this
	 * object, which means if a Explorer is changed from inside, all the others will
	 * be altered too, so if in the future The GameCard can change his attribute,
	 * remember to modified here.
	 * </p>
	 * 
	 * @param index start from 0.
	 */
	public void sell(int index, Buyer buyer) {
		if (index > availables.size())
			return;
		GameCard cardSold;
		
		/*
		 * if buy a expolrer 
		 */
		if (index == availables.size()) {
			cardSold = explorer;
			int price = cardSold.getCost();
			if (buyer.afford(price)) {
				buyer.pay(price);
				buyer.get(cardSold);
			}
		/*
		 * other cards
		 */
		} else {
			cardSold = availables.get(index);
			int price = cardSold.getCost();

			if (buyer.afford(price)) {
				buyer.pay(price);
				buyer.get(cardSold);
				/*
				 * Update available cards.
				 */
				try {
				availables.set(index, cards.pop());
				}catch (IndexOutOfBoundsException e) {
					availables.remove(index);
				}

			}
		}


	}

	@Override
	public void paint() {
		System.out.println(this);

	}

}
