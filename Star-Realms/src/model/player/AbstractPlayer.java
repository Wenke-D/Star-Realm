package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.Store;
import model.card.GameCard;
import model.card.ability.Ability;
import model.comp.Graphic;
import model.comp.cardSquence.Deck;
import model.comp.cardSquence.DiscardPile;
import model.comp.cardSquence.Field;
import model.comp.cardSquence.Hand;
import view.GraphicCard;
import view.GraphicPlayer;

/**
 * 
 * @author Matth
 *
 */
public abstract class AbstractPlayer implements Player {

	/**
	 * player's trade point. Reset to 0 while {@code endTurn()} is called.
	 */
	private int tradePoint;

	/**
	 * Player's combat point. Reset to 0 while {@code endTurn()} is called.
	 */
	private int combatPoint;

	/**
	 * Player's authority point.
	 */
	private int authorityPoint;

	/**
	 * Player's deck.
	 */
	private final Deck deck; // 牌库

	/**
	 * Player's discard pile.
	 */
	private final DiscardPile discardPile; // 弃牌区

	/**
	 * Player's hands.
	 */
	private final Hand hand; // 手牌

	/**
	 * Field where player puts his card.
	 */
	private final Field field; // 放置区

	AbstractPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		this.tradePoint = tradePoint;
		this.combatPoint = combatPoint;
		this.authorityPoint = authorityPoint;
		this.deck = new Deck(Objects.requireNonNull(list));
		this.discardPile = new DiscardPile();
		this.hand = new Hand();
		this.field = new Field();
	}

	/**
	 * Basic moves as a player
	 */

	/**
	 * Let player draw a Card from his deck.
	 */
	private void draw() {
		hand.add(deck.pop());
	}
	
	

	/**
	 * Let player draw a certain number of cards
	 * 
	 * @param cardsNumber
	 */
	@Override
	public void drawCard(int cardsNumber) {
		for (int i = 0; i < cardsNumber; i++) {

			/*
			 * If deck does not have enough cards refill it from discard pile and shuffle it
			 */
			if (deck.isEmpty()) {
				deck.refill(discardPile.getAll());
				discardPile.clear();
			}

			draw();

		}
	}


	/**
	 * <p>
	 * This function will throw out player's hands, clear ships in the field.
	 * Besides, all the player's attribute needed to be reset at the end of turn can
	 * put it here.
	 * </p>
	 */
	@Override
	public void endTurn() {

		List<GameCard> cardList = hand.getAll();
		hand.clear();

		cardList.addAll(field.clearShips());

		discardPile.addAll(cardList);

		combatPoint = 0;
		tradePoint = 0;
	}
	
	/**
	 * On suppose que tous les capacités de base n'affecte que joueur lui même.
	 */
	@Override
	public void beginTurn() {
		for (GameCard card : field) {
			card.affect(this);
		}
	}

	public void attack(Player other) {

		other.changeAuthority(-combatPoint);

	}
	
	@Override
	public void active(int cardIndex, String type, Player opponent, Store store) {
		GameCard card = field.get(cardIndex);
		active(card, type, opponent, store);
		
	}

	@Override
	public void active(GameCard card, String type, Player opponent, Store store) {
		card.affect(type, this, opponent, store);
		
	}
	
	@Override
	public GameCard put(int index) {
		index --;
		GameCard card = hand.remove(index);
		field.add(card);
		return card;
	}


	@Override
	public void get(GameCard c) {
		discardPile.add(c);
	}

	@Override
	public boolean canAfford(int price) {
		return tradePoint >= price;
	}
	
	public boolean isDead() {
		return authorityPoint <= 0;
	}

	/**
	 * Interface Target
	 */
	@Override
	public void changeAuthority(int number) {
		authorityPoint += number;
	}

	@Override
	public void changeCombat(int number) {
		combatPoint += number;

	}

	@Override
	public void changeTrade(int number) {
		tradePoint += number;

	}

	/**
	 * Interface graphicPlayer
	 */
	@Override
	public int getAuhtority() {
		return authorityPoint;
	}
	
	@Override
	public int getTrade() {
		return tradePoint;
	}
	
	@Override
	public int getCombat() {
		return combatPoint;
	}

	@Override
	public List<GraphicCard> getHand() {
		List<GraphicCard> list = new ArrayList<GraphicCard>(hand.getAll());
		return list;
	}

	@Override
	public List<GraphicCard> getField() {
		List<GraphicCard> list = new ArrayList<GraphicCard>(field.getAll());
		return list;
	}

	@Override
	public List<GraphicCard> getDiscardPile() {
		List<GraphicCard> list = new ArrayList<GraphicCard>(discardPile.getAll());
		return list;
	}

	@Override
	public List<GraphicCard> getDeck() {
		List<GraphicCard> list = new ArrayList<GraphicCard>(deck.getAll());
		return list;
	}

}
