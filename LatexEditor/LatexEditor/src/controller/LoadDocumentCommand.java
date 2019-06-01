/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadDocumentCommand implements Command {
	private String filePath = "ILoveThisLatexEditor.tex";

	public LoadDocumentCommand(String filePath) {
		this.filePath = filePath;
	}

	public void execute() {
		String fileContents = loadFileContents();
		LatexEditorController.document.setContents(fileContents);
		LatexEditorController.document
			.updateAllVariablesBasedByContents();
		saveChangesToVersionsManager();
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private String loadFileContents() {
		Scanner scanner = null;
		try {
			scanner = new Scanner( new File(filePath), "UTF-8" );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}

	private void saveChangesToVersionsManager() {
		try {
			LatexEditorController.versionsManager
			.setCurrentVersion(LatexEditorController.document.clone());

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
