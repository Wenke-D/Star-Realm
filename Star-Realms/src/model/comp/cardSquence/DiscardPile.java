package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.GameCard;
import model.comp.Graphic;

public class DiscardPile extends CardContainer implements Graphic {

	@Override
	public void paint() {
		System.out.println("Discard Pile: "+size());
	}


}
