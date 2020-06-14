package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.card.Card;
import model.comp.Graphic;
import view.GraphicCard;

public class Hand {
	private final List<Card> cardList;
	
	public Hand() {
		cardList = new ArrayList<Card>();
	}
	
	
	public List<Card> getAll() {
		return Collections.unmodifiableList(cardList);
	}
	
	public void clear() {
		cardList.clear();
	}

	/**
	 * 
	 * @param index begin with 0
	 * @return
	 */
	public Card remove(int index) {
		return cardList.remove(index);
	}
	
	public void add(Card card) {
		cardList.add(card);
	}

	
	/**
	 * 
	 * @param cardIndex index begin from 1
	 * @return
	 */
	public Card get(int cardIndex) {
		cardIndex --;
		return cardList.get(cardIndex);
	}
	
	
	
	
}
