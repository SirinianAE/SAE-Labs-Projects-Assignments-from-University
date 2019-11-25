/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package model;

public class ArticleDocument extends Document{
	private static final long serialVersionUID = 1L;

	public ArticleDocument() {
		String contents =
				"\\documentclass[11pt,twocolumn,a4paper]{article}\n" +
				"\\usepackage{graphicx}\n\n" +
				"\\begin{document}\n\n" +
				"\\title{Article: How to Structure a LaTeX " +
				"Document}\n" +
				"\\author{}\n" +
				"\\date{}\n\n" +
				"\\maketitle\n\n" +
				"\\section{Section Title 1}\n\n" +
				"\\section{Section Title 2}\n\n" +
				"\\section{Section Title.....}\n\n" +
				"\\section{Conclusion}\n\n" +
				"\\section*{References}\n\n" +
				"\\end{document}";
		setContents(contents);
	}

	public Document clone() throws CloneNotSupportedException{
		ArticleDocument newDocument = new ArticleDocument();
		ArticleDocument report = (ArticleDocument) super
				.createDeepClone(newDocument, this);
		return report;
	}
}
