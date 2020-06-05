package model.comp;

/**
 * The interface between view and data, where view get info to print.
 * @author Matth
 *
 */
public interface GraphicPackage {
		
	public Graphic log();
	
	public int getCurAuthority();

	public int getCurTrade();

	public int getCurCombat();
	
	public Graphic getCurhands();
	
	public Graphic getCurField();

	public Graphic getCurDeck();

	public Graphic getCurDisCardPile();

	public Graphic getStore();

	public int getOppAuthority();

	public int getOppTrade();

	public int getOppCombat();

	public Graphic getOppField();

	public int getRound();
	
	public String getWiner();
}
