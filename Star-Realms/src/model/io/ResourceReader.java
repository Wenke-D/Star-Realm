package model.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.card.Base;
import model.card.Card;
import model.card.Ship;
import model.card.ability.Ability;
import model.card.ability.AndAbility;
import model.card.ability.ConnectedAbility;
import model.card.ability.OrAbility;
import model.card.ability.effect.Effect;
import model.card.ability.effect.EffectFactory;

/**
 * <p>
 * This class can read resources from file to initialize game
 * </p>
 * 
 * <p>
 * Read XML file where stock game cards who's structure is fixed, after read, we
 * can get player's deck and store deck, be aware of that, all the cards
 * duplicated in the ArrayList they are the same object, which means, here we
 * just add it's reference multiple times.
 * </p>
 * 
 * <p>
 * Read images for View (wait for implement)
 * </p>
 * 
 * @author Matth
 *
 */
public class ResourceReader {
	private final String attrQuantity = "qty";

	/**
	 * <p>
	 * Acquire attribute value from the attribute specific with file name and node
	 * XPath in a XML file.
	 * </p>
	 * 
	 * @param file
	 * @param xPath
	 * @param attribute
	 * @return
	 */
	public String getAttributeValue(File file, String xPath, String attribute) {
		SAXReader in = new SAXReader();
		try {
			Document doc = in.read(file);
			Element root = doc.getRootElement();
			Element e = (Element) root.selectSingleNode(xPath);
			return e.attributeValue(attribute);
		} catch (DocumentException e) {
			throw new RuntimeException("Error during parsing file");
		}
	}

	/**
	 * <p>
	 * Make a {@code List} of {@code GameCard} from a specific file, and return it.
	 * </p>
	 * <p>
	 * This require that the element of the root node are cards
	 * </p>
	 * 
	 * @param file
	 * @return
	 */
	public List<Card> makeCardListFromFile(File file) {
		SAXReader in = new SAXReader();
		try {
			Document doc = in.read(file);
			Element root = doc.getRootElement();
			return makeCardListFromElement(root);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <p>
	 * Make a GameCard object from a specific file.
	 * </p>
	 * <p>
	 * This demand that the root node of this file is the card node.
	 * </p>
	 * 
	 * @param file
	 * @return
	 */
	public Card makeGameCardFromFile(File file) {
		SAXReader in = new SAXReader();
		try {
			Document doc = in.read(file);
			Element root = doc.getRootElement();
			return makeCardFromElement(root);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Make a {@code List} of GameCard from a XML node.
	 * 
	 * @param e
	 * @return
	 */
	private List<Card> makeCardListFromElement(Element e) {
		ArrayList<Card> deck = new ArrayList<Card>();
		for (Element card : e.elements()) {
			int qty = Integer.parseInt(card.attributeValue(attrQuantity));
			for (int i = 0; i < qty; i++) {
				deck.add(makeCardFromElement(card));
			}

		}
		return deck;
	}

	/**
	 * Create a card object based on a XML tag "card".
	 * 
	 * @param e
	 * @return
	 */
	private Card makeCardFromElement(Element e) {
		String name = e.attributeValue("name");
		String faction = e.attributeValue("faction");
		int cost = Integer.valueOf(e.attributeValue("cost"));

		List<Element> ability = e.elements("Ability");
		if(ability.size()!=3)
			throw new RuntimeException("Ability number not correcte "+name);
		Ability basic = makeAbilityFromElement(ability.get(0));
		Ability ally = makeAbilityFromElement(ability.get(1));
		Ability scrap = makeAbilityFromElement(ability.get(2));

		if (e.attributeValue("type").equals("ship")) {
			return new Ship(name, faction, cost, basic, ally, scrap);
		}
		
		try {
			int defense = Integer.parseInt(e.attributeValue("defense"));
			boolean outpost = (e.attributeValue("outpost").equals("yes"));
			
			return new Base(name, faction, cost, basic, ally, scrap, defense, outpost);
		} catch (Exception e2) {
			throw new RuntimeException("Card info wrong "+name);
		}
		

	}

	/**
	 * Create a {@code ArrayList<Ability>} based on a XML tag "Ability". if there is
	 * no item under this element, it will return a List empty.
	 * 
	 * @param e
	 * @return
	 */
	private Ability makeAbilityFromElement(Element e) {
		String relation = e.attributeValue("relation");
		if (relation == null)
			throw new RuntimeException("Relation missing in card:" + e.getParent().attributeValue("name"));
		ArrayList<Effect> array = new ArrayList<Effect>();
		for (Element item : e.elements()) {
			Effect effect = EffectFactory.makeEffect(item);
			array.add(effect);
		}
		switch (relation) {
		case "and":
			return new AndAbility(array);
		case "connected":
			if(array.size()!=2)
				throw new RuntimeException("Effect number wrong "+e.getParent().attributeValue("name"));
			return new ConnectedAbility(array);
		case "or":
			return new OrAbility(array);
		default:
			String info = String.format("Unknown ability relation:%s in Card %s", relation,
					e.getParent().attributeValue("name"));
			throw new RuntimeException(info);
		}

	}

}
