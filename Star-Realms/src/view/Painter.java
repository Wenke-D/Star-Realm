package view;

import java.util.List;
import java.util.Objects;

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
		title("Star Realm");
		System.out.println("[1] Player VS Computer");
		System.out.println("[2] Player VS Player");
		System.out.print("Votre choix :");
	}

	/**
	 * Display of game view
	 * 
	 * @param graphicPackage
	 */
	public void playingInterface(GraphicPackage graphicPackage) {
		roundNumber(graphicPackage.getRoundNumber());

		title("Your Opponant");
		GraphicPlayer opponent = graphicPackage.getOpponent();
		opponent(opponent);

		title("Trade Row");
		GraphicStore store = graphicPackage.getStore();
		store(store);

		title("Your Self");
		GraphicPlayer curPlayer = graphicPackage.getCurPlayer();
		curPlayer(curPlayer);

		// normalMessage(graphicPackage.getMessage());

	}

	public void result(String winner) {
		Objects.requireNonNull(winner);
		title("Winner: " + winner);
	}

	/**
	 * paint a system message in the screen
	 * 
	 * @param message
	 */
	public void importantMessage(String message) {
		System.out.println("**" + message + "**");
	}

	/**
	 * Print help message in the screen
	 */
	public void help() {
		System.out.println("Entre <<attack>> to attack your opponant. ");
		System.out.println("Entre <<play index>> to play a card from your hand. ");
		System.out.println("Entre <<quit>> to end the game directely. ");
		System.out.println("Entre <<buy index>> to buy a card. ");
	}

	/**
	 * Paint player's part in the screen
	 * 
	 * @param p
	 */
	private void curPlayer(GraphicPlayer p) {

		System.out.println("->Authority: " + p.getAuhtority());
		System.out.println("->Trade: " + p.getTrade());
		System.out.println("->Combat: " + p.getCombat());

		List<GraphicCard> field = p.getField();
		Objects.requireNonNull(field);
		field(field);

		List<GraphicCard> hand = p.getHand();
		Objects.requireNonNull(field);
		hand(hand);

		List<GraphicCard> deck = p.getDeck();
		List<GraphicCard> discardPile = p.getDiscardPile();
		deckAndDiscardPile(deck, discardPile);

	}

	/**
	 * Paint game's store
	 * 
	 * @param store
	 */
	private void store(GraphicStore store) {
		List<GraphicCard> list = store.cards();
		cardListWithIndex(list, 0);
	}

	/**
	 * Paint opponent of current player
	 * 
	 * @param p
	 */
	private void opponent(GraphicPlayer p) {
		System.out.println("->Authority: " + p.getAuhtority());
		System.out.println("->Trade: " + p.getTrade());
		System.out.println("->Combat: " + p.getCombat());

		List<GraphicCard> field = p.getField();
		field(field);

		List<GraphicCard> hand = p.getHand();
		opponantHand(hand);

		List<GraphicCard> deck = p.getDeck();
		List<GraphicCard> discardPile = p.getDiscardPile();
		deckAndDiscardPile(deck, discardPile);
	}

	/**
	 * Paint player's deck and discardPile
	 * 
	 * @param deck
	 * @param discardPile
	 */
	private void deckAndDiscardPile(List<GraphicCard> deck, List<GraphicCard> discardPile) {
		System.out.printf("Deck:%d\tDiscardPile: %d\n", deck.size(), discardPile.size());
	}

	/**
	 * Paint player's Field
	 * 
	 * @param list
	 */
	private void field(List<GraphicCard> list) {
		System.out.println("Field");
		cardListWithIndex(list, 0);
	}

	/**
	 * Paint player's Hand
	 * 
	 * @param list
	 */
	private void hand(List<GraphicCard> list) {
		System.out.println("Hand:");
		cardListWithIndex(list, 0);
	}

	/**
	 * Paint hand of player's opponent
	 * 
	 * @param hand
	 */
	private void opponantHand(List<GraphicCard> hand) {
		System.out.printf("Hands: %d\n", hand.size());
	}

	/**
	 * Paint game round number
	 * 
	 * @param i
	 */
	private void roundNumber(int i) {
		title("Round " + i);
	}

	private void cardListWithIndex(List<GraphicCard> list, int nbTab) {
		for (int i = 0; i < list.size(); i++) {
			GraphicCard c = list.get(i);
			System.out.printf("[%d]", i + 1);
			card(c, 1);
		}
	}

	/**
	 * Afficher une card et retourner ¨¤ la nouvelle ligne.
	 * 
	 * @param card
	 * @param nbTab
	 */
	private void card(GraphicCard card, int nbTab) {
		System.out.printf("%s**%s :%d %s ", tabs(nbTab), card.getName(), card.getCost(), card.getFaction());

		if (card.isBase()) {
			System.out.printf("Defense:%d %s\n", card.getDefense(), card.isOutpost() ? "Outpost" : "");
		} else {
			System.out.println();
		}

		System.out.printf("%sBasic Ability\n", tabs(nbTab + 1));
		ability(card.getBasicAbility(), nbTab + 1);

		System.out.printf("%sAlly Ability\n", tabs(nbTab + 1));
		ability(card.getAllyAbility(), nbTab + 1);

		System.out.printf("%sScrap Ability\n", tabs(nbTab + 1));
		ability(card.getScrapAbility(), nbTab + 1);
	}

	/**
	 * Afficher une capacit¨¦ et retourner ¨¤ la nouvelle ligne
	 * 
	 * @param ability
	 * @param nbTab
	 */
	private void ability(GraphicAbility ability, int nbTab) {
		List<GraphicEffect> list = ability.getEffects();
		if (list.size() == 0) {
			System.out.printf("%sNone\n", tabs(nbTab + 1));
			return;
		}
		String type = ability.getAbilityType();
		for (GraphicEffect e : list) {
			effect(e, nbTab + 1);
		}
	}

	/**
	 * Afficher un effect dans 3 lignes
	 * 
	 * @param e
	 * @param nbTab
	 */
	private void effect(GraphicEffect e, int nbTab) {
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

	private void title(String title) {
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
