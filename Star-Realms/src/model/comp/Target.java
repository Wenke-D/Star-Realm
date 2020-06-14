package model.comp;

public interface Target {

	public void changeAuthority(int number);

	public void changeCombat(int number);

	public void changeTrade(int number);

	public void drawCard(int number);
	
	/**
	 * Remove permanently a card
	 * @param i index begins from 1
	 */
	public void scrap(int i);

}
