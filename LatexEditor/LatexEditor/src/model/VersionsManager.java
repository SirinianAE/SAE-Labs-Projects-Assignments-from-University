/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

import java.util.ArrayList;

public class VersionsManager {
	private VersionsStrategy strategy = new VolatileStrategy();
	private Document currentVersion;
	private boolean enabled = false;

	public void setCurrentVersion(Document newVersion) {
		if (enabled) {
			strategy.putVersion(newVersion);
			currentVersion = strategy.getVersion();
		}
	}

	public boolean rollbackToPreviousVersion() {
		if (enabled) {
			strategy.removeVersion();
			if(strategy.getVersion() != null){
				currentVersion = strategy.getVersion();
			}
			return true;
		}
		return false;
	}

	public void changeStrategy(VersionsStrategy strategy) {
		ArrayList<Document> oldBunchOfVersions;

		oldBunchOfVersions = this.strategy.getBunchOfVersions();
		this.strategy = strategy;
		strategy.setBunchOfVersions(oldBunchOfVersions);
	}

	public VersionsStrategy getStrategy(){
		return strategy;
	}

	public Document getCurrentVersion() {
		return currentVersion;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public boolean isEnabled(){
		return enabled;
	}
}
