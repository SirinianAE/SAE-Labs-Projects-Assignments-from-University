/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS6;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenVersionsManagerWhenDisableVersionsManagerThenVersionsHistoryRemainsUnchanged {
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
		expectedContents3 = "His name is Zarras!";
		testRunningRight = true;
	}

	@Test
	public void test() {
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");

		controller.enact("EnableVersionsManagement");
		controller.enact("SetStableStrategy");
		controller.enact("DisableVersionsManagement");
		testVersionManagerIsEnabled();
		editDocumentContentsWithTheExpectedContents();
		testRollbackResults();

		controller.enact("EnableVersionsManagement");
		controller.enact("SetVolatileStrategy");
		controller.enact("DisableVersionsManagement");
		testVersionManagerIsEnabled();
		editDocumentContentsWithTheExpectedContents();
		testRollbackResults();

		assertTrue(testRunningRight);
	}

	private void editDocumentContentsWithTheExpectedContents(){
		controller.setDocumentContents(expectedContents1);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents2);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents3);
		controller.enact("EditDocument");
	}

	private void testRollbackResults(){
		String actualContents = "";

		actualContents = LatexEditorController.getDocument()
				.getContents();
		if(!actualContents.equals(expectedContents3)){
			testRunningRight = false;
		}
		controller.enact("RollbackToPreviousVersion");
		if(!actualContents.equals(expectedContents3)){
			testRunningRight = false;
		}
		controller.enact("RollbackToPreviousVersion");
		if(!actualContents.equals(expectedContents3)){
			testRunningRight = false;
		}
	}

	private void testVersionManagerIsEnabled(){
		if(LatexEditorController.getVersionManager().isEnabled()){
			testRunningRight = false;
		}
	}
}
