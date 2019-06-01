/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package testUS1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;

public class GivenBookTypeWhenCreatingNewDocumentThenDocumentContentsContainTheRightTemplate {
	private LatexEditorController controller;
	private static String expectedTemplate;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		expectedTemplate = "\\documentclass[11pt,a4paper]{book}\n" +
				"\\usepackage{graphicx}\n\n" +
				"\\begin{document}\n\n" +
				"\\title{Book: How to Structure a LaTeX Document}\n" +
				"\\author{}\n" +
				"\\date{}\n\n" +
				"\\maketitle\n\n" +
				"\\frontmatter\n\n" +
				"\\chapter{Preface}\n\n" +
				"\\mainmatter\n\n" +
				"\\chapter{First chapter}\n\n" +
				"\\section{Section Title 1}\n" +
				"\\section{Section Title 2}\n" +
				"\\section{Section Title.....}\n\n" +
				"\\chapter{....}\n\n" +
				"\\chapter{Conclusion}\n\n" +
				"\\chapter*{References}\n\n" +
				"\\backmatter\n\n" +
				"\\chapter{Last note}\n\n" +
				"\\end{document}";
	}

	@Test
	public void test() {
		String contents;
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
		contents = LatexEditorController.getDocument().getContents();
		assertEquals(contents, expectedTemplate);
	}
}
