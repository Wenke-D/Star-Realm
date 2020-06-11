package model.comp.cardSquence;

import java.util.Collections;
import java.util.List;

import model.card.GameCard;
import model.comp.Graphic;

public class Deck{
	private final List<GameCard> cardList;
	
	public Deck(List<GameCard> cardList) {
		this.cardList = cardList; 
	}
	

	public boolean isEmpty() {
		return cardList.size()==0;
	}
	
	public void refill(List<GameCard> newCards) {
		Collections.shuffle(newCards);
		cardList.addAll(newCards);
	}


	public GameCard pop() {
		int size = cardList.size();
		return cardList.remove(size-1);
	}


	public List<GameCard> getAll() {
		return Collections.unmodifiableList(cardList);
	}
}
