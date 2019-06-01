/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package testUS1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenReportTypeWhenCreatingNewDocumentThenDocumentContentsContainTheRightTemplate {
	private LatexEditorController controller;
	private static String expectedTemplate;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedTemplate = "\\documentclass[11pt,a4paper]{report}" +
				"\n\n" +
				"\\usepackage{graphicx}\n\n" +
				"\\begin{document}\n\n" +
				"\\title{Report Template: How to Structure a LaTeX " +
				"Document}\n" +
				"\\author{}\n" +
				"\\date{}\n" +
				"\\maketitle\n\n" +
				"\\begin{abstract}\n" +
				"Your abstract goes here...\n" +
				"...\n" +
				"\\end{abstract}\n\n" +
				"\\chapter{First Chapter}\n\n" +
				"\\section{Section Title 1}\n" +
				"\\section{Section Title 2}\n" +
				"\\section{Section Title.....}\n\n" +
				"\\chapter{....}\n\n" +
				"\\chapter{Conclusion}\n\n\n" +
				"\\chapter*{References}\n\n" +
				"\\end{document}";
	}

	@Test
	public void test() {
		String contents;
		controller.setTemplateID("report");
		controller.enact("CreateNewDocument");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, expectedTemplate);
	}
}
