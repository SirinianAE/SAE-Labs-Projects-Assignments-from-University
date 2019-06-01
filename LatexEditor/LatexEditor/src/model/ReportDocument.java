/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

public class ReportDocument extends Document{
	private static final long serialVersionUID = 1L;

	public ReportDocument() {
		String contents = "\\documentclass[11pt,a4paper]{report}\n\n"+
				"\\usepackage{graphicx}\n\n" +
				"\\begin{document}\n\n" +
				"\\title{Report Template: How to Structure a LaTeX" +
				" Document}\n" +
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
		setContents(contents);
	}

	public Document clone() throws CloneNotSupportedException{
		ReportDocument newDocument = new ReportDocument();
		ReportDocument report = (ReportDocument) super
				.createDeepClone(newDocument, this);
		return report;
	}

}
