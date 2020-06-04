package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.GameCard;
import model.comp.Graphic;

abstract class CardContainer implements Graphic{
	private final ArrayList<GameCard> cards; //  ÷≈∆
	
	public CardContainer() {
		cards = new ArrayList<GameCard>();
	}
	
	public void add(GameCard newCard) {
		cards.add(newCard);
	}
	
	public List<GameCard> clear(){
		ArrayList<GameCard> cs = new ArrayList<GameCard>(cards);
		cards.clear();
		return cs;
	}
	
	public GameCard get(int index) {
		return cards.get(index);
	}
	
	public GameCard remove(int index) {
		return cards.remove(index);
	}
	
	public int size() {
		return cards.size();
	}
	
	public void addAll(List<GameCard> cards) {
		for(GameCard c: cards) {
			add(c);
		}
	}
	
	public GameCard pop() {
		return cards.remove(size()-1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(GameCard c: cards) {
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
