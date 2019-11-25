/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenStableStrategyIsEnabledWhenRollbackToPreviouseVersionsThenDocumentContentsChangeRight {
	private LatexEditorController controller;
	private String expectedContents1;
	private String expectedContents2;
	private String expectedContents3;
	private boolean testRunningRight;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedContents1 = " I love this LatexEditor!!!";
		expectedContents2 =
				"But there's someone that i love even more...";
		expectedContents3 = "His name is Zarras! :b ";
		testRunningRight = true;
	}

	@Test
	public void test() {
		createNewDocument();
		controller.enact("EnableVersionsManagement");
		controller.enact("SetStableStrategy");
		editDocumentContentsWithTheExpectedContents();

		compareWithDocumentContents(expectedContents3);
		controller.enact("RollbackToPreviousVersion");
		compareWithDocumentContents(expectedContents2);
		controller.enact("RollbackToPreviousVersion");
		compareWithDocumentContents(expectedContents1);
		controller.enact("RollbackToPreviousVersion");
		compareWithDocumentContents(expectedContents1);

		assertTrue(testRunningRight);
	}

	private void createNewDocument(){
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
	}

	private void editDocumentContentsWithTheExpectedContents(){
		controller.setDocumentContents(expectedContents1);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents2);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents3);
		controller.enact("EditDocument");
	}

	private void compareWithDocumentContents(String expectedContents){
		String newContents;
		newContents = LatexEditorController.getDocument()
				.getContents();
		if(!newContents.equals(expectedContents)){
			testRunningRight = false;
		}
	}
}
