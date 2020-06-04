package model.comp;

public interface GraphicPackage {
	
	/**
	 * Determiner si on a besoin de mettre ид jour toute l'affiachage,
	 * sinon, seulement afficher le log.
	 * @return
	 */
	public boolean needUpdate();
	
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
}
