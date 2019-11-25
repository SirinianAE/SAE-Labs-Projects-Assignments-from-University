/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS8;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenContentsOfAnyDocumentWhenSaveDocumentThenContentsInDocumentAndInFileAreTheSame {
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
		contentsFromFile = loadFileContents();

		assertEquals(contentsFromDocument.trim(),
				contentsFromFile.trim());
	}

	@After
	public void cleanUpTestEnvironment(){
		tempFile.delete();
	}

	private String loadFileContents() {
		Scanner scanner = null;
		try {
			scanner = new Scanner( new File(filePath), "UTF-8" );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}
}
