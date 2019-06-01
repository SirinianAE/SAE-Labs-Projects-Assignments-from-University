/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SaveDocumentCommand implements Command {
	private String filePath = "ILoveThisLatexEditor.tex";

	public SaveDocumentCommand(String filePath) {
		this.filePath = filePath;
	}

	public void execute() {
		overwriteFile();
	}

	private void overwriteFile() {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException |
				UnsupportedEncodingException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		writer.println(LatexEditorController.document.getContents());
		writer.close();
	}
}
