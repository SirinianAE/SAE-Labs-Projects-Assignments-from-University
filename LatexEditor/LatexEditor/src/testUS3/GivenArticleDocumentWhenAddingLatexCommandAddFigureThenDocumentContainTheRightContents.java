/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package testUS3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenArticleDocumentWhenAddingLatexCommandAddFigureThenDocumentContainTheRightContents {
	private LatexEditorController controller;
	private String textToBeReplaced;
	private String newContents;
	private String latexCommandID;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		textToBeReplaced = "<-->";
		newContents = "\\begin{figure}\n" +
				"\\includegraphics[width=..., height=...]{..}\n" +
				"\\caption{...}\\label{...}\n" + "\\end{figure}\n";
		latexCommandID = "AddFigure";

		controller.setTextToBeReplaced(textToBeReplaced);
		controller.setLatexCommandID(latexCommandID);
		controller.setTemplateID("article");
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
