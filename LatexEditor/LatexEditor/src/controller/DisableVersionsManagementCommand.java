/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package controller;

public class DisableVersionsManagementCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager.disable();
	}
}
