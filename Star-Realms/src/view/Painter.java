package view;


import java.util.List;

import model.card.GameCard;
import model.comp.GraphicPackage;


/**
 * Use a graphics2D object to draw game element in the place specific
 * 
 * @author Matth
 *
 */

public class Painter {
		
	public void paintCurPlayer(GraphicPlayer p) {
		System.out.println("->Authority: "+p.getAuhtority());
		System.out.println("->Trade: "+p.getTrade());
		System.out.println("->Combat: "+p.getCombat());
		
		List<GameCard> field = p.getField();
		paintField(field);
		
		List<GameCard> hand = p.getHand();
		paintHand(hand);
		
		List<GameCard> deck = p.getDeck();
		List<GameCard> discardPile = p.getDiscardPile();
		paintDeckAndDiscardPile(deck, discardPile);
		
	}
	
	private void paintDeckAndDiscardPile(List<GameCard> deck, List<GameCard> discardPile) {
				
	}

	private void paintField(List<GameCard> list) {
		
	}
	
	private void paintHand(List<GameCard> list) {
		
	}
	
	private void paintStore(GraphicStore store) {
		
	}
	
	private void paintOpponent(GraphicPlayer p) {
		System.out.println("->Authority: "+p.getAuhtority());
		System.out.println("->Trade: "+p.getTrade());
		System.out.println("->Combat: "+p.getCombat());
		
		List<GameCard> field = p.getField();
		paintField(field);
		
		List<GameCard> hand = p.getHand();
		paintOpponHand(hand);
		
		List<GameCard> deck = p.getDeck();
		List<GameCard> discardPile = p.getDiscardPile();
		paintDeckAndDiscardPile(deck, discardPile);
		
	}
	private void paintOpponHand(List<GameCard> hand) {
		// TODO Auto-generated method stub
		
	}

	private void paintRoundNumber(int i) {
		System.out.println("########");
		System.out.println("#Round "+i+"#");
		System.out.println("########");

	}

}
