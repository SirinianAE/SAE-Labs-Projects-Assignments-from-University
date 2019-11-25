/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package controller;

public class EditDocumentCommand implements Command {
	private String contents = "";

	public EditDocumentCommand(String newContents) {
		this.contents = newContents;
	}

	public void execute() {
		LatexEditorController.document.setContents(contents);
		LatexEditorController.document
			.updateAllVariablesBasedByContents();
		saveChangesToVersionsManager();
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
