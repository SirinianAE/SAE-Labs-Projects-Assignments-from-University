/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

import model.VersionsStrategyFactory;

public class SetVolatileStrategyCommand implements Command {

	public void execute() {
		LatexEditorController.versionsManager
			.changeStrategy(VersionsStrategyFactory
			.createStrategy("VolatileVersions"));
	}
}
