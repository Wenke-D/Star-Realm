package model.comp.cardSquence;

import java.util.Collections;
import java.util.List;

import model.card.Card;
import view.GraphicCard;

public class Deck{
	private final List<Card> cardList;
	
	public Deck(List<Card> cardList) {
		this.cardList = cardList; 
	}
	

	public boolean isEmpty() {
		return cardList.size()==0;
	}
	
	public void refill(List<Card> newCards) {
		cardList.addAll(newCards);
		Collections.shuffle(cardList);
		
	}


	public Card pop() {
		int size = cardList.size();
		return cardList.remove(size-1);
	}


	public List<Card> getAll() {
		return Collections.unmodifiableList(cardList);
	}
}
