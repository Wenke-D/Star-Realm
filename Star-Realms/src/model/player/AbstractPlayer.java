package model.player;

import java.util.List;
import java.util.Objects;
import model.card.GameCard;
import model.comp.Buyer;
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
public abstract class AbstractPlayer implements Player, Buyer, Target {

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
	 * Mark if player has attacked. Reset while {@code endTurn()} is called.
	 */
	private boolean attacked;

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
		attacked = false;
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
	public void draw(int cardsNumber) {
		for (int i = 0; i < cardsNumber; i++) {

			/*
			 * If deck does not have enough cards refill it from discard pile and shuffle it
			 */
			if (deck.isEmpty()) {
				deck.refill(discardPile.clear());
			}

			draw();

		}
	}

	/**
	 * <p>
	 * Play a card from his hand by indicating it's index, meanwhile active it's
	 * ability.
	 * <p>
	 * <p>
	 * The card played will be removed from hand and add to field
	 * <p>
	 * 
	 * 
	 * @param card
	 * @return {@code true} if used, {@code false} if this index out of bound.
	 */
	public void use(int cardIndex) {
		GameCard card;
		card = hand.use(cardIndex);

		card.activeAbility(this, "basic");
		field.add(card);
	}

	public void useAll() {
		while (hand.size() != 0) {
			use(0);
		}
	}

	/**
	 * <p>
	 * This function will throw out player's hands, clear ships in the field.
	 * Besides, all the player's attribute needed to be reset at the end of turn can
	 * put it here.
	 * </p>
	 */
	public void endTurn() {

		// clear hands to discard pile
		discardPile.addAll(hand.clear());

		// clear ship of field to discard pile
		discardPile.addAll(field.clearShips());

		field.resetState();

		combatPoint = 0;
		tradePoint = 0;
		attacked = false;
		needActive = true;

	}

	/**
	 * <p>
	 * If this is the begin of turn, this function will active all the base left in
	 * the field and return true. if not, nothing will be done and return false.
	 * </p>
	 * 
	 * @return return {@code true} if some bases are activated, return {@code flase}
	 *         if not.
	 * 
	 */
	public boolean activeField() {
		if (needActive) {
			field.active(this);
			needActive = false;
			return true;
		}
		return false;
	}

	public void attack(Player other) {
		if (!attacked) {
			other.sufferDamage(combatPoint);
			attacked = true;
		}
	}

	public void attack(int baseIndex, Player owner) {
		owner.baseAttacked(baseIndex, combatPoint);
		attacked = true;
	}

	public void active(int cardIndex, String type, Player player) {
		player.affected(cardIndex, type);
	}

	/**
	 * Interface Buyer
	 */
	@Override
	public void pay(int price) {
		tradePoint -= price;
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
	public void drawCard(int number) {
		draw(number);
	}

	@Override
	public void changeTrade(int number) {
		tradePoint += number;

	}

	/**
	 * Interface GamePlayer
	 */
	@Override
	public void sufferDamage(int damagePoint) {
		if (!field.hasOutpostBase()) {
			authorityPoint -= damagePoint;
		}

	}

	@Override
	public void baseAttacked(int baseIndex, int damagePoint) {
		if (field.isDestoryed(baseIndex, damagePoint)) {
			discardPile.add(field.remove(baseIndex));
		}

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

	@Override
	public Graphic getHands() {
		return hand;
	}

	@Override
	public Graphic getField() {
		return field;
	}

	@Override
	public Graphic getDeck() {
		return deck;
	}

	@Override
	public Graphic getDisCardPile() {
		return discardPile;
	}

	@Override
	public void affected(int cardIndex, String type) {
		field.active(cardIndex, type, this);
	}
	
	@Override
	public void prepare() {
		draw(5);
		field.active(this);
		
	}
	
	@Override
	public void draw3() {
		draw(3);
	}
	
	
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				String.format("->Authority: %d\n->Trade: %d\n->Combat: %d\n", authorityPoint, tradePoint, combatPoint));

		sb.append("Hands:");
		if (hand.size() == 0) {
			sb.append("None\n");
		} else {
			sb.append("\n" + hand.toString());
		}
		sb.append("Field:");
		if (field.size() == 0) {
			sb.append("None\n");
		} else {
			sb.append("\n" + field.toString());
		}
		sb.append("Deck:").append(deck.size()).append('\n');
		sb.append("Discard Pile:").append(discardPile.size()).append('\n');
		return sb.toString();
	}

	public boolean isDead() {
		return authorityPoint <= 0;
	};

}
