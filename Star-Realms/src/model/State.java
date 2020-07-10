package model;

public interface State {
	
	/**
	 * let current user play a card from his hand.
	 * @param i card index in his hand, begin from 1
	 */
	public void use(int i);
	
	/**
	 * let current user active a card ability
	 * @param i card's index, begin from 1.
	 * @param type ability type
	 * @throws InvalideOperation if ability active condition doesn't fit, throw.
	 */
	public void active(int i, String type) throws InvalideOperation;
	
	/**
	 * make current user buy a card from store.
	 * @param i the index of card that player wants to buy, begin from 1.
	 * @throws InvalideOperation if player doesn't have enough trade, throw.
	 */
	public void buy(int i) throws InvalideOperation;

	/**
	 * make current player attack his opponent.
	 * @throws InvalideOperation if current player has attacked.
	 */
	public void attackPlayer() throws InvalideOperation;
	
	/**
	 * make current player attack the base
	 * @param i index of base, begin from 1
	 * @throws InvalideOperation if current player has attacked.
	 */
	public void attackBase(int i) throws InvalideOperation;
	
	/**
	 * make current player end his turn.
	 */
	public void end();

}
