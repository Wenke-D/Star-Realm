package view;

import java.util.List;
import java.util.Objects;

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
	 * Print the game menu in the screen
	 */
	public void menu() {
		paintTitle("Star Realm");
		System.out.println("[1] Player VS Computer");
		System.out.println("[2] Player VS Player");
		System.out.print("Votre choix :");
	}

	/**
	 * Display of game view
	 * 
	 * @param graphicPackage
	 */
	public void updateView(GraphicPackage graphicPackage) {
		paintRoundNumber(graphicPackage.getRoundNumber());

		paintTitle("Your Opponant");
		GraphicPlayer opponent = graphicPackage.getOpponent();
		paintOpponent(opponent);

		paintTitle("Trade Row");
		GraphicStore store = graphicPackage.getStore();
		paintStore(store);

		paintTitle("Your Self");
		GraphicPlayer curPlayer = graphicPackage.getCurPlayer();
		paintCurPlayer(curPlayer);

		// normalMessage(graphicPackage.getMessage());

	}

	public void result(String winner) {
		Objects.requireNonNull(winner);
		paintTitle("Winner: " + winner);
	}

	/**
	 * paint a system message in the screen
	 * 
	 * @param message
	 */
	public void importantMessage(String message) {
		System.out.println("**" + message + "**");
	}
	
	public void help() {
		System.out.println("Entre <<attack>> to attack your opponant. ");
	}

	/**
	 * Paint player's part in the screen
	 * 
	 * @param p
	 */
	private void paintCurPlayer(GraphicPlayer p) {

		System.out.println("->Authority: " + p.getAuhtority());
		System.out.println("->Trade: " + p.getTrade());
		System.out.println("->Combat: " + p.getCombat());

		List<GraphicCard> field = p.getField();
		Objects.requireNonNull(field);
		paintField(field);

		List<GraphicCard> hand = p.getHand();
		Objects.requireNonNull(field);
		paintHand(hand);

		List<GraphicCard> deck = p.getDeck();
		List<GraphicCard> discardPile = p.getDiscardPile();
		paintDeckAndDiscardPile(deck, discardPile);

	}

	/**
	 * Paint game's store
	 * 
	 * @param store
	 */
	private void paintStore(GraphicStore store) {
		for (GraphicCard c : store.cards()) {
			paintCard(c, 0);
		}
	}

	/**
	 * Paint opponent of current player
	 * 
	 * @param p
	 */
	private void paintOpponent(GraphicPlayer p) {
		System.out.println("->Authority: " + p.getAuhtority());
		System.out.println("->Trade: " + p.getTrade());
		System.out.println("->Combat: " + p.getCombat());

		List<GraphicCard> field = p.getField();
		paintField(field);

		List<GraphicCard> hand = p.getHand();
		paintOpponHand(hand);

		List<GraphicCard> deck = p.getDeck();
		List<GraphicCard> discardPile = p.getDiscardPile();
		paintDeckAndDiscardPile(deck, discardPile);
	}

	/**
	 * Paint player's deck and discardPile
	 * 
	 * @param deck
	 * @param discardPile
	 */
	private void paintDeckAndDiscardPile(List<GraphicCard> deck, List<GraphicCard> discardPile) {
		System.out.printf("Deck:%d\tDiscardPile: %d\n", deck.size(), discardPile.size());
	}

	/**
	 * Paint player's Field
	 * 
	 * @param list
	 */
	private void paintField(List<GraphicCard> list) {
		System.out.println("Field");
		for (GraphicCard c : list) {
			paintCard(c, 1);
		}
	}

	/**
	 * Paint player's Hand
	 * 
	 * @param list
	 */
	private void paintHand(List<GraphicCard> list) {
		System.out.println("Hand:");
		for (int i = 0; i < list.size(); i++) {
			GraphicCard c = list.get(i);
			System.out.printf("[%d]", i+1);
			paintCard(c, 1);
		}
	}

	/**
	 * Paint hand of player's opponent
	 * 
	 * @param hand
	 */
	private void paintOpponHand(List<GraphicCard> hand) {
		System.out.printf("Hands: %d\n", hand.size());
	}

	/**
	 * Paint game round number
	 * 
	 * @param i
	 */
	private void paintRoundNumber(int i) {
		paintTitle("Round " + i);
	}

	/**
	 * Afficher une card et retourner ¨¤ la nouvelle ligne.
	 * 
	 * @param card
	 * @param nbTab
	 */
	private void paintCard(GraphicCard card, int nbTab) {
		System.out.printf("%s**%s :%d %s\n", tabs(nbTab), card.getName(), card.getCost(), card.getFaction());

		System.out.printf("%sBasic Ability\n", tabs(nbTab + 1));
		paintAbility(card.getBasicAbility(), nbTab + 1);

		System.out.printf("%sAlly Ability\n", tabs(nbTab + 1));
		paintAbility(card.getAllyAbility(), nbTab + 1);

		System.out.printf("%sScrap Ability\n", tabs(nbTab + 1));
		paintAbility(card.getScrapAbility(), nbTab + 1);
	}

	/**
	 * Afficher une capacit¨¦ et retourner ¨¤ la nouvelle ligne
	 * 
	 * @param ability
	 * @param nbTab
	 */
	private void paintAbility(GraphicAbility ability, int nbTab) {
		List<GraphicEffect> list = ability.getEffects();
		if (list.size() == 0) {
			System.out.printf("%sNone\n", tabs(nbTab+1));
			return;
		}
		String type = ability.getAbilityType();
		for (GraphicEffect e : list) {
			paintEffect(e, nbTab + 1);
		}
	}

	/**
	 * Afficher un effect dans 3 lignes
	 * 
	 * @param e
	 * @param nbTab
	 */
	private void paintEffect(GraphicEffect e, int nbTab) {
		System.out.printf("%s%s %s, ", tabs(nbTab), "Target", e.getTarget());
		System.out.printf("%s %s, ", "Type", e.getType());
		System.out.printf("%s %d\n", "Value", e.getValue());
	}

	/**
	 * Paint a normal message in the screen, for example a player's operation
	 * 
	 * @param message
	 */
	private void normalMessage(String message) {
		System.out.println(message);
	}

	private void paintTitle(String title) {
		int length = title.length();
		System.out.println(strMulti("#", length + 4));
		System.out.printf("# %s #\n", title);
		System.out.println(strMulti("#", length + 4));
	}

	private String tabs(int n) {
		return strMulti("\t", n);
	}

	/**
	 * Simulation of multiplication of String like Python
	 * 
	 * @param string a string
	 * @param nomber a number
	 * @return string*number
	 */
	private String strMulti(String string, int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < number; i++) {
			sb.append(string);
		}
		return sb.toString();
	}

}
