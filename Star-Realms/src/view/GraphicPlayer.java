package view;

import java.util.List;

public interface GraphicPlayer {
	public int getAuhtority();
	public int getTrade();
	public int getCombat();
	public List<GraphicCard> getHand();
	public List<GraphicCard> getField();
	public List<GraphicCard> getDiscardPile();
	public List<GraphicCard> getDeck();
	
}
