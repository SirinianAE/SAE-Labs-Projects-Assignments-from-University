/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

import java.util.ArrayList;

public interface VersionsStrategy {
	public void putVersion(Document newVersion);

	public Document getVersion();

	public void removeVersion();

	public void setBunchOfVersions(
			ArrayList<Document> bunchOfVersions);

	public ArrayList<Document> getBunchOfVersions();
}
