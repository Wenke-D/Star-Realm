package view;


import model.comp.GraphicPackage;


/**
 * Use a graphics2D object to draw game element in the place specific
 * 
 * @author Matth
 *
 */

public class Painter {
	private final GraphicPackage g;
	
	public Painter(GraphicPackage g) {
		this.g = g;
	}
	
	public void paintCurPlayer() {
		System.out.println("->Authority: "+g.getCurAuthority());
		System.out.println("->Trade: "+g.getCurTrade());
		System.out.println("->Combat: "+g.getCurCombat());
		
		g.getCurhands().paint();
		g.getCurField().paint();
		g.getCurDeck().paint();
		
		g.getCurDisCardPile().paint();
	}

	public void paintStore() {
		g.getStore().paint();
	}
	
	public void paintOpponent() {
		System.out.println("->Authority: "+g.getOppAuthority());
		System.out.println("->Trade: "+g.getOppTrade());
		System.out.println("->Combat: "+g.getOppCombat());

		g.getOppField().paint();
	}
	public void paintRoundNumber() {
		int i = g.getRound();
		System.out.println("########");
		System.out.println("#Round "+i+"#");
		System.out.println("########");

	}

}
