package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.card.GameCard;

public class DiscardPile {
	private final List<GameCard> cardList;
	
	public DiscardPile() {
		cardList = new ArrayList<GameCard>();
	}
	
	public void addAll(List<GameCard> list) {
		cardList.addAll(cardList);
	}
	
	public List<GameCard> getAll() {
		return Collections.unmodifiableList(cardList);
	}
	
	public void clear() {
		cardList.clear();
	}

	public void add(GameCard c) {
		cardList.add(c);
		
	}
	


}
