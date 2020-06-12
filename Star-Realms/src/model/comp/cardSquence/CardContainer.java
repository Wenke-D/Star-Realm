package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.Card;
import model.comp.Graphic;

abstract class CardContainer implements Graphic{
	private final ArrayList<Card> cards; //  ÷≈∆
	
	public CardContainer() {
		cards = new ArrayList<Card>();
	}
	
	public void add(Card newCard) {
		cards.add(newCard);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(Card c: cards) {
			sb.append("______________________________________\n");
			sb.append("["+i+"]").append(c.toString()).append('\n');
			i++;
		}
		return sb.toString();
		
	}
	
	@Override
	public void paint() {
		System.out.println(this);		
	}
}
