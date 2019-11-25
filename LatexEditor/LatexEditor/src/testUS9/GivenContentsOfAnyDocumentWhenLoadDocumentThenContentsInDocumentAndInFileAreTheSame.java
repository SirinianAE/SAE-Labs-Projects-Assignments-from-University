/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS9;

import static org.junit.Assert.assertEquals;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenContentsOfAnyDocumentWhenLoadDocumentThenContentsInDocumentAndInFileAreTheSame {
	private LatexEditorController controller;
	private File tempFile;
	private String filePath;
	private String contentsFromDocument;
	private String contentsFromFile;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		filePath = "tempFile.tex";
		tempFile = new File(filePath);
		contentsFromDocument = "";
		contentsFromFile = "";
	}

	@Test
	public void test() {
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
		contentsFromDocument = LatexEditorController.getDocument()
				.getContents();
		controller.setFilePath(filePath);
		controller.enact("SaveDocument");
		controller.enact("LoadDocument");
		contentsFromFile = LatexEditorController.getDocument()
				.getContents();

		assertEquals(contentsFromDocument.trim(),
				contentsFromFile.trim());
	}

	@After
	public void cleanUpTestEnvironment(){
		tempFile.delete();
	}
}
