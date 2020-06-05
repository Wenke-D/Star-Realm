package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.card.GameCard;
import model.comp.Graphic;

public class Hand {
	private final List<GameCard> cardList;
	
	public Hand() {
		cardList = new ArrayList<GameCard>();
	}
	
	
	public List<GameCard> getAll() {
		return Collections.unmodifiableList(cardList);
	}
	
	public void clear() {
		cardList.clear();
	}


	public GameCard remove(int index) {
		return cardList.remove(index);
	}
	
	public void add(GameCard card) {
		cardList.add(card);
	}
	
	
	
	
}
