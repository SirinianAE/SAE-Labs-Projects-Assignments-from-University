/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

import java.util.HashMap;
import java.util.Map;

public class DocumentManager {
	private static Map <String,Document> bunchOfPrototypes =
			new HashMap<String,Document>();

	static {
		ArticleDocument articlePrototype = new ArticleDocument();
		articlePrototype.setTemplateID("article");
		bunchOfPrototypes.put(articlePrototype.getTemplateID(),
				articlePrototype);

		BookDocument bookPrototype = new BookDocument();
		bookPrototype.setTemplateID("book");
		bunchOfPrototypes.put(bookPrototype.getTemplateID(),
				bookPrototype);

		LetterDocument letterPrototype = new LetterDocument();
		letterPrototype.setTemplateID("letter");
		bunchOfPrototypes.put(letterPrototype.getTemplateID(),
				letterPrototype);

		ReportDocument reportPrototype = new ReportDocument();
		reportPrototype.setTemplateID("report");
		bunchOfPrototypes.put(reportPrototype.getTemplateID(),
				reportPrototype);

		BlankDocument blankPrototype =  new BlankDocument();
		blankPrototype.setTemplateID("blank");
		bunchOfPrototypes.put(blankPrototype.getTemplateID(),
				blankPrototype);
	}

	public static Document createDocument(String templateID) {
		Document choosenPrototype;
		Document clone = null;

		if (bunchOfPrototypes.containsKey(templateID)) {
			choosenPrototype = bunchOfPrototypes.get(templateID);
		}else {
			choosenPrototype = bunchOfPrototypes.get("blank");
		}

		try {
			clone = choosenPrototype.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return clone;
	}
}
