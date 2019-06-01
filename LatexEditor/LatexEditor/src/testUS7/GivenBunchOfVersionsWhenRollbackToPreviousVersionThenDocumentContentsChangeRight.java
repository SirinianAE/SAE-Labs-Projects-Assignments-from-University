/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package testUS7;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.LatexEditorController;
import model.Document;

public class GivenBunchOfVersionsWhenRollbackToPreviousVersionThenDocumentContentsChangeRight {
	private LatexEditorController controller;
	private String expectedContents1;
	private String expectedContents2;
	private String expectedContents3;
	private boolean isTestRunningRight;
	ArrayList<Document> bunchOfVersions;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedContents1 = " I love this LatexEditor!!!";
		expectedContents2 =
				"But there's someone that i love even more...";
		expectedContents3 = "His name is Zarras!";
		isTestRunningRight = true;
	}

	@Test
	public void test() {
		createNewDocument();
		controller.enact("EnableVersionsManagement");
		controller.enact("SetStableStrategy");
		editDocumentContentsWithTheExpectedContents();
		bunchOfVersions = LatexEditorController.getVersionManager()
				.getStrategy().getBunchOfVersions();

		compareWithDocumentContents(expectedContents3);
		controller.enact("RollbackToPreviousVersion");
		compareWithDocumentContents(expectedContents2);
		controller.enact("RollbackToPreviousVersion");
		compareWithDocumentContents(expectedContents1);

		assertTrue(isTestRunningRight);
	}

	private void compareWithDocumentContents(String expectedContents){
		String newContents;
		newContents = bunchOfVersions.remove(bunchOfVersions.size()-1)
				.getContents();
		if(!newContents.equals(expectedContents)){
			isTestRunningRight = false;
		}
	}

	private void editDocumentContentsWithTheExpectedContents(){
		controller.setDocumentContents(expectedContents1);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents2);
		controller.enact("EditDocument");
		controller.setDocumentContents(expectedContents3);
		controller.enact("EditDocument");
	}

	private void createNewDocument(){
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
	}
}
