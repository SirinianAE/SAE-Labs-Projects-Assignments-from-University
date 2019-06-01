/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package testUS3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenBookDocumentWhenAddingLatexCommandAddTableThenDocumentContainTheRightContents {
	private LatexEditorController controller;
	private String textToBeReplaced;
	private String newContents;
	private String latexCommandID;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		textToBeReplaced = "<-->";
		newContents = "\\begin{table}\n" +
				"\\caption{}\\label{}\n" +
				"\\begin{tabular}{|c|c|c|)\n" + "\\hline\n" +
				"...&...&...\\" + "\n" + "...&...&...\\" +
				"\n" + "...&...&...\\" + "\n" +
				"\\hline\n" + "\\end{tabular}\n" + "\\end{table}\n";
		latexCommandID = "AddTable";

		controller.setTextToBeReplaced(textToBeReplaced);
		controller.setLatexCommandID(latexCommandID);
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
	}

	@Test
	public void test() {
		String contents;
		controller.setContents(textToBeReplaced);
		controller.enact("AddLatex");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, newContents);

	}
}
