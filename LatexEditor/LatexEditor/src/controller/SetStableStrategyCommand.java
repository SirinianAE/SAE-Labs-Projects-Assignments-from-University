/*
 * Authors
 * Name: Sirinian Aram Emmanouil
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
