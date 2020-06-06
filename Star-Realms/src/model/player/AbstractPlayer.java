package model.player;

import java.util.List;
import java.util.Objects;
import model.card.GameCard;
import model.card.ability.Ability;
import model.comp.Graphic;
import model.comp.Target;
import model.comp.cardSquence.Deck;
import model.comp.cardSquence.DiscardPile;
import model.comp.cardSquence.Field;
import model.comp.cardSquence.Hand;

/**
 * 
 * @author Matth
 *
 */
public abstract class AbstractPlayer implements Player, Target {

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

	/**
	 * Mark if player's field's base need to be activated. Reset to true while
	 * {@code endTurn()} is called.
	 */
	private boolean needActive;

	AbstractPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		this.tradePoint = tradePoint;
		this.combatPoint = combatPoint;
		this.authorityPoint = authorityPoint;
		this.deck = new Deck(Objects.requireNonNull(list));
		this.discardPile = new DiscardPile();
		this.hand = new Hand();
		this.field = new Field();
		needActive = true;
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

	@Override
	public void beginTurn() {
		for (GameCard card : field) {
			Ability ability = card.getBasicAbility();
			ability.affect(this);
		}
	}

	public void attack(Player other) {

		other.changeAuthority(-combatPoint);

	}


	public void active(int cardIndex, String type, Player player) {
		player.affected(cardIndex, type);
	}
	
	@Override
	public GameCard put(int index) {
		GameCard card = hand.remove(index);
		field.add(card);
		return card;
	}


	@Override
	public void get(GameCard c) {
		discardPile.add(c);
	}

	@Override
	public boolean afford(int price) {
		return tradePoint >= price;
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
	public int getAuthority() {
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

	public boolean isDead() {
		return authorityPoint <= 0;
	}

}
