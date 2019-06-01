/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

import model.Document;
import model.VersionsManager;

public class LatexEditorController {
	protected static Document document;
	protected static VersionsManager versionsManager =
			new VersionsManager();
	protected static String filePath;
	protected static String templateID;
	protected static String latexCommandID;
	protected static String contents;
	protected static String textToBeReplaced = "<-->";
	protected static boolean rollbackToPreviousVersionPossible =
			false;


	public void enact(String commandID) {
		Command command = CommandFactory.getCommand(commandID);
		command.execute();
	}

	public static Document getDocument() {
		return LatexEditorController.document;
	}

	public void setTemplateID(String templateID) {
		LatexEditorController.templateID = templateID;
	}

	public void setDocumentContents(String documentContents) {
		LatexEditorController.document.setContents(documentContents);
	}

	public void setFilePath(String filePath) {
		LatexEditorController.filePath = filePath;
	}

	public void setTextToBeReplaced(String textToBeReplaced){
		LatexEditorController.textToBeReplaced = textToBeReplaced;
	}

	public void setContents(String contents){
		LatexEditorController.contents = contents;
	}

	public void setLatexCommandID(String latexCommandID){
		LatexEditorController.latexCommandID = latexCommandID;
	}

	public static VersionsManager getVersionManager() {
		return LatexEditorController.versionsManager;
	}

	public boolean isRollbackToPreviousVersionPossible(){
		return LatexEditorController
				.rollbackToPreviousVersionPossible;
	}
}
