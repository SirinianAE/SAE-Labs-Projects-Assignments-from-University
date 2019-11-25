/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package controller;

import model.VersionsStrategyFactory;

public class SetStableStrategyCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager
			.changeStrategy(VersionsStrategyFactory
			.createStrategy("StableVersions"));
	}
}
