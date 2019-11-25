/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package model;

public class BlankDocument extends Document{
	private static final long serialVersionUID = 1L;

	public BlankDocument() {
		String contents = "";
		setContents(contents);
	}

	public Document clone() throws CloneNotSupportedException {
		BlankDocument newDocument = new BlankDocument();
		BlankDocument report = (BlankDocument) super
				.createDeepClone(newDocument, this);
		return report;
	}

}
