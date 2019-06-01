/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

public class LetterDocument extends Document{
	private static final long serialVersionUID = 1L;

	public LetterDocument() {
		String contents = "\\documentclass{letter}\n" +
				"\\usepackage{hyperref}\n" +
				"\\signature{Sender's Name}\n" +
				"\\address{Sender's address...}\n" +
				"\\begin{document}\n\n" +
				"\\begin{letter}{Destination address....}\n" +
				"\\opening{Dear Sir or Madam:}\n\n" +
				"I am writing to you .......\n\n\n" +
				"\\closing{Yours Faithfully,}\n\n" +
				"\\ps\n\n" +
				"P.S. text .....\n\n" +
				"\\encl{Copyright permission form}\n\n" +
				"\\end{letter}\n" +
				"\\end{document}";
		setContents(contents);
	}

	public Document clone() throws CloneNotSupportedException{
		LetterDocument newDocument = new LetterDocument();
		LetterDocument report = (LetterDocument) super
				.createDeepClone(newDocument, this);
		return report;
	}
}
