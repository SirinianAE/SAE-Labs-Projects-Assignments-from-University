/*
 * Authors
 * Name: Sirinian Aram Emmanouil
*/


package controller;

public class RollbackToPreviousVersionCommand implements Command {

	public void execute(){
		if(!LatexEditorController.versionsManager
			.rollbackToPreviousVersion()) {
			LatexEditorController.rollbackToPreviousVersionPossible =
					false;
		}else {
			LatexEditorController.document = LatexEditorController
				.versionsManager.getCurrentVersion();
			LatexEditorController.rollbackToPreviousVersionPossible =
					true;
		}
	}
}
