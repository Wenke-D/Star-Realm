package model.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.card.GameCard;
import model.card.Ship;
import model.io.ResourceReader;

class ResourceReaderTest {
	private static ResourceReader reader = new ResourceReader();

	@Test
	void makeCardListFromFileTest() {
		Path core = Path.of("res", "cards", "CoreSet.xml");
		List<GameCard> list = reader.makeCardListFromFile(core.toFile());
		System.out.println(list.size());
		System.out.println(list);
	}
	
	@Test
	void test() {
		SAXReader in = new SAXReader();
		try {
			Path p = Path.of("res", "config.xml");
			Document doc = in.read(p.toFile());
			Element config = doc.getRootElement();
			Element paths = config.element("paths");
			for(Element e: paths.elements()) {
				System.out.println(e.getQualifiedName());
			}
		} catch (DocumentException e) {
			throw new RuntimeException();
		}
	}
	
	@Test
	void makeGameCardFromFileTest() {
		
		Path configPath = Path.of("res", "config.xml");

		String paths = "/config/paths/";

		String ExplorerXPath = paths + "Explorer";

		String ExplorerPath = reader.getAttributeValue(configPath.toFile(), ExplorerXPath, "path");

		GameCard explorer = reader.makeGameCardFromFile(new File(ExplorerPath));
		assertNotNull(explorer);
	}
}
