package view;

public interface GraphicCard {
	public String getName();
	public String getFaction();
	public int getCost();
	public GraphicAbility getBasicAbility();
	public GraphicAbility getAllyAbility();
	public GraphicAbility getScrapAbility();
	public boolean isBase();
	public boolean isOutpost();
	public int getDefense();
	
	
}
