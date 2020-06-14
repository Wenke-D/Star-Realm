package model.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Store;
import model.card.Base;
import model.card.Card;
import model.comp.Target;
import model.comp.cardSquence.Deck;
import model.comp.cardSquence.DiscardPile;
import model.comp.cardSquence.Field;
import model.comp.cardSquence.Hand;
import view.GraphicCard;

/**
 * 
 * @author Matth
 *
 */
abstract class AbstractPlayer implements Player {

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
	private final Deck deck; // �ƿ�

	/**
	 * Player's discard pile.
	 */
	private final DiscardPile discardPile; // ������

	/**
	 * Player's hands.
	 */
	private final Hand hand; // ����

	/**
	 * Field where player puts his card.
	 */
	private final Field field; // ������
	
	private int nbBlob;

	AbstractPlayer(int tradePoint, int combatPoint, int authorityPoint, List<Card> list) {
		this.tradePoint = tradePoint;
		this.combatPoint = combatPoint;
		this.authorityPoint = authorityPoint;
		this.deck = new Deck(Objects.requireNonNull(list));
		this.discardPile = new DiscardPile();
		this.hand = new Hand();
		this.field = new Field();
		
		this.nbBlob = 0;
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

		List<Card> cardList = new ArrayList<Card>(hand.getAll());
		hand.clear();

		cardList.addAll(field.clearShips());

		discardPile.addAll(cardList);

		combatPoint = 0;
		tradePoint = 0;
		nbBlob = 0;
	}

	/**
	 * On suppose que tous les capacit��s de base n'affecte que joueur lui m��me.
	 */
	@Override
	public void beginTurn(Target opponent, Target store) {
		for (Card card : field) {
			card.affect("basic", this, opponent, store, null);
		}
		field.clearAcvitiedCard();
	}

	public void attack(Player other) {

		other.changeAuthority(-combatPoint);

	}

	@Override
	public void active(int cardIndex, String type, Player opponent, Store store, List<String> extraInfos) {
		Card card = field.get(cardIndex);
		active(card, type, opponent, store, extraInfos);

	}

	@Override
	public void active(Card card, String type, Player opponent, Store store, List<String> extraInfos) {

		if (type.equals("ally")) {
			if (field.hasAlly(card) && field.notAcvitied(card)) {
				card.affect(type, this, opponent, store, extraInfos);
				field.addAcvitiedCard(card);
			}
		} else if (type.equals("scrap")) {
			card.affect(type, this, opponent, store, extraInfos);
			field.remove(card);
		} else {
			card.affect(type, this, opponent, store, extraInfos);
		}

	}

	@Override
	public Card put(int index) {
		index--;
		Card card = hand.remove(index);
		field.add(card);
		if(card.isFaction("Blob"))
			nbBlob ++;
		return card;
	}

	@Override
	public void addToDiscardPile(Card c) {
		discardPile.add(c);
	}

	@Override
	public boolean canAfford(int price) {
		return tradePoint >= price;
	}

	@Override
	public void pay(int cost) {
		changeTrade(-cost);
	}

	@Override
	public boolean isDead() {
		return authorityPoint <= 0;
	}

	@Override
	public boolean baseIsDestory(int cardIndex, int damage) {
		Card card = field.get(cardIndex);
		if (!(card instanceof Base))
			return false;
		Base base = (Base) card;
		return base.idDestroyed(damage);
	}

	@Override
	public void destoryCard(int cardIndex) {
		Card card = field.remove(cardIndex);
		discardPile.add(card);
	}

	public Card getCardFromField(int i) {
		return field.get(i);
	}

	public Card getCardFromHand(int cardIndex) {

		return hand.get(cardIndex);
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
	
	@Override
	public Card remove(int i) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addToDeck(Card card) {
		deck.add(card);
	}
	
	@Override
	public int nbBlob() {
		return nbBlob;
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
