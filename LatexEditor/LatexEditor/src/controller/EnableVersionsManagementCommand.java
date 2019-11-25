/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package controller;

public class EnableVersionsManagementCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager.enable();
	}
}
