package model.comp.cardSquence;

import java.util.Collections;
import java.util.List;

import model.card.GameCard;
import model.comp.Graphic;

public class Deck extends CardContainer implements Graphic{
	
	public Deck(List<GameCard> newCards) {
		refill(newCards);
	}

	public boolean isEmpty() {
		return size()==0;
	}
	
	public void refill(List<GameCard> newCards) {
		Collections.shuffle(newCards);
		addAll(newCards);
		
	}


	@Override
	public void paint() {
		System.out.println("Deck: "+size());
	}
}
