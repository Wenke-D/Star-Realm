package model;

public interface State {
	
	/**
	 * 
	 * @param i begin from 1
	 */
	public void use(int i);
	
	/**
	 * 
	 * @param i begin from 1
	 */
	public void active(int i);
	
	/**
	 * 
	 * @param i begin from 1
	 */
	public void buy(int i);
	
	public void attackPlayer();
	
	/**
	 * 
	 * @param i begin from 1
	 */
	public void attackBase(int i);
	
	/**
	 * change player
	 */
	public void end();

}
