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

public class GivenBlankTypeWhenCreatingNewDocumentThenDocumentContentsContainTheRightTemplate {
	private LatexEditorController controller;
	private static String expectedTemplate;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedTemplate = "";
	}

	@Test
	public void test() {
		String contents;
		controller.setTemplateID("blank");
		controller.enact("CreateNewDocument");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, expectedTemplate);
	}
}
