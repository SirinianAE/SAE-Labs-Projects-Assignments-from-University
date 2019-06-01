/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

public class DisableVersionsManagementCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager.disable();
	}
}
