/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenAnyDocumentWhenEditingDocumentContentsThenDocumentContainTheRightContents {
	private LatexEditorController controller;
	private static String expectedContents;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedContents = "This test is one more example of" + "\n"
				+ "how awesome this LatexEditor really is.";
		controller.setTemplateID("article");
		controller.enact("CreateNewDocument");
	}

	@Test
	public void test() {
		String actualContents;
		controller.setDocumentContents(expectedContents);
		controller.enact("EditDocument");
		actualContents = LatexEditorController.getDocument().getContents();
		assertEquals(actualContents, expectedContents);
	}

}
