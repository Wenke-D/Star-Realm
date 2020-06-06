package model.comp;

import view.GraphicPlayer;
import view.GraphicStore;

/**
 * The interface between view and data, where view get info to print.
 * @author Matth
 *
 */
public interface GraphicPackage {
		
	public int getRound();
	
	public String getWiner();

	public GraphicPlayer getCurPlayer();

	public GraphicPlayer getOpponent();

	public GraphicStore getStore();
}
