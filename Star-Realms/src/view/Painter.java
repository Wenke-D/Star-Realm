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
	
	/**
	 * Display of game view
	 * @param graphicPackage
	 */
	public void updateView(GraphicPackage graphicPackage) {
		
		GraphicPlayer opponent = graphicPackage.getOpponent();
		paintOpponent(opponent);
		
		GraphicStore store = graphicPackage.getStore();
		paintStore(store);
		
		GraphicPlayer curPlayer = graphicPackage.getCurPlayer();
		paintCurPlayer(curPlayer);
		
		normalMessage(graphicPackage.getMessage());
	
	}
	
	/**
	 * Print the game menu in the screen
	 */
	public void menu() {
		System.out.println("##############");
		System.out.println("# Star Realm #");
		System.out.println("##############");
		System.out.println("[1] Player VS Computer");
		System.out.println("[2] Player VS Player");
		System.out.print("Votre choix :");
	}
	
	
	public void result(String winner) {
		System.out.println("***********");
		System.out.println("Winner: "+winner);
		System.out.println("***********");
	}
	
	/**
	 * Paint player's part in the screen
	 * @param p
	 */
	private void paintCurPlayer(GraphicPlayer p) {
		paintRoundNumber(p.roundNumber());
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
	
	/**
	 * Paint player's deck and discardPile
	 * @param deck
	 * @param discardPile
	 */
	private void paintDeckAndDiscardPile(List<GameCard> deck, List<GameCard> discardPile) {
		String s = String.format("Deck:%D\tDiscardPile: %d", deck.size(), discardPile.size());
		System.out.println(s);
	}

	/**
	 * Paint player's Field
	 * @param list
	 */
	private void paintField(List<GameCard> list) {
		System.out.println(list);
	}
	
	/**
	 * Paint player's Hand
	 * @param list
	 */
	private void paintHand(List<GameCard> list) {
		System.out.println(list);
	}
	
	/**
	 * Paint game's store
	 * @param store
	 */
	private void paintStore(GraphicStore store) {
		System.out.println(store.cards());
	}
	
	/**
	 * Paint opponent of current player
	 * @param p
	 */
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
	
	/**
	 * Paint hand of player's opponent
	 * @param hand
	 */
	private void paintOpponHand(List<GameCard> hand) {
		String s = String.format("Hands: %d", hand.size());
		System.out.println(s);
		
	}

	/**
	 * Paint game round number
	 * @param i
	 */
	private void paintRoundNumber(int i) {
		System.out.println("########");
		System.out.println("#Round "+i+"#");
		System.out.println("########");

	}
	
	/**
	 * Paint a normal message in the screen, for example a player's operation
	 * @param message
	 */
	private void normalMessage(String message) {
		System.out.println(message);
	}

	/**
	 * paint a system message in the screen
	 * @param message
	 */
	public void systemMessage(String message) {
		System.out.println("**"+message+"**");
	}

}
