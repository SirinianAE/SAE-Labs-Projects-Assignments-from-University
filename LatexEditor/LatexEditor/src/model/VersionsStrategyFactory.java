/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package model;

public class VersionsStrategyFactory {
	public static VersionsStrategy createStrategy(
			String versionsStrategyID){
		versionsStrategyID = versionsStrategyID.replaceAll("\\s", "");
		try {
			if(versionsStrategyID
					.equalsIgnoreCase("VolatileVersions") ||
					versionsStrategyID.equalsIgnoreCase("Volatile")) {
				return new VolatileStrategy();
			}else if(versionsStrategyID
					.equalsIgnoreCase("StableVersions") ||
					versionsStrategyID.equalsIgnoreCase("Stable")) {
				return new StableStrategy();
			}else {
				throw new Exception("VersionsStrategyFactory: " +
			"Versions Strategy ID " + "'" + versionsStrategyID +
			"'" + " is not supported");
			}
		}catch(Exception E) {
			E.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
}
