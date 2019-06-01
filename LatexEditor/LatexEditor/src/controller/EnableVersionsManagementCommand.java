/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

public class EnableVersionsManagementCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager.enable();
	}
}
