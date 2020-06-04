package model.comp.cardSquence;

import model.card.GameCard;
import model.comp.Graphic;

public class Hand extends CardContainer implements Graphic{


	public GameCard use(int index) {
		return remove(index);
	}

	@Override
	public void paint() {
		if(size()!=0) {
			System.out.println("Hands:");
			super.paint();
		}
			
		else {
			System.out.println("Hands: None");
		}
		
	}
	
	
	
	
}
