/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

public class AddLatexCommand implements Command {
	private String textToBeReplaced;
	private String contents;
	private String latexCommandID;
	private String templateID = LatexEditorController.document
			.getTemplateID();

	public AddLatexCommand(String textToBeReplaced, String contents,
			String latexCommandID) {
		this.textToBeReplaced = textToBeReplaced;
		this.contents = contents;
		this.latexCommandID = latexCommandID;
	}

	public void execute() {
		try {
			if(latexCommandID.equalsIgnoreCase("AddChapter")) {
				replaceTextWithLatexCommandAddChapter();
			}else if(latexCommandID.equalsIgnoreCase("AddSection")) {
				replaceTextWithLatexCommandAddSection();
			}else if(latexCommandID.equalsIgnoreCase(
					"AddSubsection")) {
				replaceTextWithLatexCommandAddSubsection();
			}else if(latexCommandID.equalsIgnoreCase(
					"AddSubsubsection")) {
				replaceTextWithLatexCommandAddSubsubsection();
			}else if(latexCommandID.equalsIgnoreCase(
					"AddEnumerationListItemize")) {
				replaceTextWithLatexCommandAddEnumerationListItemize
				();
			}else if(latexCommandID.equalsIgnoreCase(
					"AddEnumerationListEnumerate")) {
				replaceTextWithLatexCommandAddEnumerationListEnumerate
				();
			}else if(latexCommandID.equalsIgnoreCase("AddTable")) {
				replaceTextWithLatexCommandAddTable();
			}else if(latexCommandID.equalsIgnoreCase("AddFigure")) {
				replaceTextWithLatexCommandAddFigure();
			}else {
				throw new Exception("Latex command type: " +
			"'" + latexCommandID + "'" + " is not supported");
			}
		}catch(Exception E) {
			E.printStackTrace();
			System.exit(-1);
		}

		LatexEditorController.document.setContents(contents);
		LatexEditorController.document
		.updateAllVariablesBasedByContents();
		saveChangesToVersionsManager();
	}

	private void replaceTextWithLatexCommandAddChapter() {
		String newChapter = "\\chapter{}\n";

		if(templateID.equals("letter") ||
				templateID.equals("article")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced, newChapter);
		}
	}

	private void replaceTextWithLatexCommandAddSection() {
		String newSection = "\\section{}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced, newSection);
		}
	}

	private void replaceTextWithLatexCommandAddSubsection() {
		String newSubsection = "\\subsection{}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced,
					newSubsection);
		}
	}

	private void replaceTextWithLatexCommandAddSubsubsection() {
		String newSubsubsection = "\\subsubsection{}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced,
					newSubsubsection);
		}
	}

	private void replaceTextWithLatexCommandAddEnumerationListItemize(){
		String newEnumerationListItemize = "\\begin{itemize}\n" +
					"\\item\n" + "\\item\n" + "\\end{itemize}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced,
					newEnumerationListItemize);
		}
	}

	private void replaceTextWithLatexCommandAddEnumerationListEnumerate(){
		String newEnumerationListEnumerate = "\\begin{enumerate}\n" +
				"\\item\n" + "\\item\n" + "\\end{enumerate}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced,
					newEnumerationListEnumerate);
		}
	}

	private void replaceTextWithLatexCommandAddTable() {
		String newTable = "\\begin{table}\n" +
				"\\caption{}\\label{}\n" +
				"\\begin{tabular}{|c|c|c|)\n" + "\\hline\n" +
				"...&...&...\\" + "\n" + "...&...&...\\" + "\n" +
				"...&...&...\\" + "\n" +
				"\\hline\n" + "\\end{tabular}\n" + "\\end{table}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced, newTable);
		}
	}

	private void replaceTextWithLatexCommandAddFigure() {
		String newFigure = "\\begin{figure}\n" +
				"\\includegraphics[width=..., height=...]{..}\n" +
				"\\caption{...}\\label{...}\n" + "\\end{figure}\n";

		if(templateID.equals("letter")) {
			contents = contents.replace(textToBeReplaced, "");
		}else {
			contents = contents.replace(textToBeReplaced, newFigure);
		}
	}

	private void saveChangesToVersionsManager() {
		try {
			LatexEditorController.versionsManager.setCurrentVersion(
					LatexEditorController.document.clone());

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
