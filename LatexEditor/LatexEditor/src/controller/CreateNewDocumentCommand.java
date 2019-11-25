/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package controller;

import model.DocumentManager;

public class CreateNewDocumentCommand implements Command {
	private String templateID = "article";

	public CreateNewDocumentCommand(String templateID) {
		this.templateID = templateID;
	}

	public void execute() {
		LatexEditorController.document = DocumentManager
				.createDocument(templateID);
	}
}
