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

public class GivenArticleTypeWhenCreatingNewDocumentThenDocumentContentsContainTheRightTemplate {
	private LatexEditorController controller;
	private static String expectedTemplate;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedTemplate =
				"\\documentclass[11pt,twocolumn,a4paper]{article}\n" +
				"\\usepackage{graphicx}\n\n" +
				"\\begin{document}\n\n" +
				"\\title{Article: How to Structure a LaTeX Document}"+
				"\n" +
				"\\author{}\n" +
				"\\date{}\n\n" +
				"\\maketitle\n\n" +
				"\\section{Section Title 1}\n\n" +
				"\\section{Section Title 2}\n\n" +
				"\\section{Section Title.....}\n\n" +
				"\\section{Conclusion}\n\n" +
				"\\section*{References}\n\n" +
				"\\end{document}";
	}

	@Test
	public void test() {
		String contents;
		controller.setTemplateID("article");
		controller.enact("CreateNewDocument");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, expectedTemplate);
	}
}
