/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package model;

import java.io.Serializable;

public abstract class Document implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	private String author = null;
	private String date = null;
	private String copyright = null;
	private String versionID = null;
	private String contents = null;
	private String templateID = null;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;

	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void updateAllVariablesBasedByContents() {
		author = updateVariableBasedByContents("author");
		date = updateVariableBasedByContents("date");
	}

	private String updateVariableBasedByContents(String variableName){
		String tempFields[] = contents.split("\n");
		String tempFieldsOfFields[];
		for (int i = 0; i < tempFields.length; i++) {
			tempFieldsOfFields = tempFields[i].split("\\{");
			if(tempFieldsOfFields[0].equals("\\" + variableName) &&
					tempFieldsOfFields.length > 1) {
				String result = tempFieldsOfFields[1]
						.replace("}", "").trim();
				if(!result.equals("")) {
					return result;
				}
			}
		}
		return null;
	}

	public String toString() {
		return "Author: " + author + "\nDate: " + date +
				"\nCopyright: " + copyright + "\nVersionID: " +
				versionID + "\nTemplateID: " + templateID +
				"\nContents:" + contents;
	}

	public abstract Document clone() throws
	CloneNotSupportedException;

	protected Document createDeepClone(Document report,
			Document original) {
		report.setAuthor(original.getAuthor());
		report.setTemplateID(original.getTemplateID());
		report.setCopyright(original.getCopyright());
		report.setDate(original.getDate());
		report.setVersionID(original.getVersionID());
		report.setContents(original.getContents());

		return report;
	}
}
