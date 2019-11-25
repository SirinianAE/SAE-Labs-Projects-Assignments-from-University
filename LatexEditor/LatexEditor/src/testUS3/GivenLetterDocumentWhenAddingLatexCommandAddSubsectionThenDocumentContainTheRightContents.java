/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.LatexEditorController;

public class GivenLetterDocumentWhenAddingLatexCommandAddSubsectionThenDocumentContainTheRightContents {
	private LatexEditorController controller;
	private String textToBeReplaced;
	private String newContents;
	private String latexCommandID;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		textToBeReplaced = "<-->";
		newContents = "";
		latexCommandID = "AddSubsection";

		controller.setTextToBeReplaced(textToBeReplaced);
		controller.setLatexCommandID(latexCommandID);
		controller.setTemplateID("letter");
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
