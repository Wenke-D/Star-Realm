package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.GameCard;
import model.card.Ship;

public class Field implements Iterable<GameCard> {
	private final List<GameCard> cardList;

	public Field() {
		cardList = new ArrayList<GameCard>();
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
	public Iterator<GameCard> iterator() {
		return cardList.iterator();
	}

	public void add(GameCard card) {
		cardList.add(card);
		
	}
	
	
	

}
