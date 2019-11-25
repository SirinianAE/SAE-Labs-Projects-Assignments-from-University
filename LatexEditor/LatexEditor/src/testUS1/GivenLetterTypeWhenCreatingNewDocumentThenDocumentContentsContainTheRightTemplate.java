/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenLetterTypeWhenCreatingNewDocumentThenDocumentContentsContainTheRightTemplate {
	private LatexEditorController controller;
	private static String expectedTemplate;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedTemplate = "\\documentclass{letter}\n" +
				"\\usepackage{hyperref}\n" +
				"\\signature{Sender's Name}\n" +
				"\\address{Sender's address...}\n" +
				"\\begin{document}\n\n" +
				"\\begin{letter}{Destination address....}\n" +
				"\\opening{Dear Sir or Madam:}\n\n" +
				"I am writing to you .......\n\n\n" +
				"\\closing{Yours Faithfully,}\n\n" +
				"\\ps\n\n" +
				"P.S. text .....\n\n" +
				"\\encl{Copyright permission form}\n\n" +
				"\\end{letter}\n" +
				"\\end{document}";
	}

	@Test
	public void test() {
		String contents;
		controller.setTemplateID("letter");
		controller.enact("CreateNewDocument");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, expectedTemplate);
	}
}
