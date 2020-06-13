package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.card.Card;
import model.card.Ship;
import view.GraphicCard;

public class Field implements Iterable<Card> {
	private final List<Card> cardList;
	private Set<Card> acvitiedAllyCard = new HashSet<Card>();

	public Field() {
		cardList = new ArrayList<Card>();
	}

	public List<Ship> clearShips() {
		List<Ship> ships = new ArrayList<Ship>();

		for (int i = 0; i < cardList.size();) {
			if (cardList.get(i) instanceof Ship) {
				Ship ship = (Ship) cardList.remove(i);
				ships.add(ship);
			} else {
				i++;
			}
		}
		return ships;
	}

	@Override
	public Iterator<Card> iterator() {
		return cardList.iterator();
	}

	public void add(Card card) {
		cardList.add(card);

	}

	/**
	 * 
	 * @param cardIndex index begin with 1;
	 * @return
	 */
	public Card get(int cardIndex) {
		cardIndex--;
		return cardList.get(cardIndex);
	}

	public List<Card> getAll() {
		return Collections.unmodifiableList(cardList);
	}

	/**
	 * Remove a card from field
	 * 
	 * @param index index begin with 1;
	 * @return
	 */
	public Card remove(int index) {
		index--;
		return cardList.remove(index);
	}

	/**
	 * 
	 * @param card
	 * @return
	 */
	public boolean remove(Card card) {
		acvitiedAllyCard.remove(card);
		return cardList.remove(card);
	}

	public boolean hasAlly(Card other) {
		int i = 0;
		for (Card c : cardList) {
			if (c.isAlly(other))
				i++;
		}
		return i > 1;
	}

	public boolean notAcvitied(Card card) {
		return !(acvitiedAllyCard.contains(card));
	}

	public void addAcvitiedCard(Card card) {
		acvitiedAllyCard.add(card);
	}

	public void clearAcvitiedCard() {
		acvitiedAllyCard.clear();
	}

}
