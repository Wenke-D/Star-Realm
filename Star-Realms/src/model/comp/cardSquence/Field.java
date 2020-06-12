package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.card.Card;
import model.card.Ship;
import view.GraphicCard;

public class Field implements Iterable<Card> {
	private final List<Card> cardList;

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

	public Card get(int cardIndex) {
		
		return cardList.get(cardIndex);
	}

	public List<Card> getAll() {
		return Collections.unmodifiableList(cardList);
	}
	
	
	

}
