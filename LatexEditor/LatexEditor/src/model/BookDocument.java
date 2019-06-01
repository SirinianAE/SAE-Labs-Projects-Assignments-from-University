/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

public class BookDocument extends Document{
	private static final long serialVersionUID = 1L;

	public BookDocument() {
		String contents = "\\documentclass[11pt,a4paper]{book}\n" +
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
		setContents(contents);
	}

	public Document clone() throws CloneNotSupportedException{
		BookDocument newDocument = new BookDocument();
		BookDocument report = (BookDocument) super
				.createDeepClone(newDocument, this);
		return report;
	}
}
