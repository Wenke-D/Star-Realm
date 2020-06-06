package view;

import java.util.List;

import model.card.GameCard;

public interface GraphicPlayer {
	public int getAuhtority();
	public int getTrade();
	public int getCombat();
	public List<GameCard> getHand();
	public List<GameCard> getField();
	public List<GameCard> getDiscardPile();
	public List<GameCard> getDeck();
	public int roundNumber();
	
}
