package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.card.Card;
import view.GraphicCard;

public class DiscardPile {
	private final List<Card> cardList;
	
	public DiscardPile() {
		cardList = new ArrayList<Card>();
	}
	
	public void addAll(List<Card> list) {
		cardList.addAll(list);
	}
	
	public List<Card> getAll() {
		return Collections.unmodifiableList(cardList);
	}
	
	public void clear() {
		cardList.clear();
	}

	public void add(Card c) {
		cardList.add(c);
		
	}
	


}
