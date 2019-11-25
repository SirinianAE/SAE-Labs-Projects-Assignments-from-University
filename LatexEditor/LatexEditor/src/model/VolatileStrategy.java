/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package model;

import java.util.ArrayList;

public class VolatileStrategy implements VersionsStrategy {
	private ArrayList<Document> bunchOfVersions =
			new ArrayList<Document>();

	public void putVersion(Document newVersion) {
		bunchOfVersions.add(newVersion);
	}

	public Document getVersion() {
		Document lastVersion = null;
		if(!bunchOfVersions.isEmpty()) {
			try {
				lastVersion = bunchOfVersions
						.get(bunchOfVersions.size() - 1).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		return lastVersion;
	}

	public void removeVersion() {
		if(bunchOfVersions.size() > 0) {
			bunchOfVersions.remove(bunchOfVersions.size() - 1);
		}
	}

	public void setBunchOfVersions(
			ArrayList<Document> bunchOfVersions){
		this.bunchOfVersions = bunchOfVersions;
	}

	public ArrayList<Document> getBunchOfVersions(){
		return bunchOfVersions;
	}
}
