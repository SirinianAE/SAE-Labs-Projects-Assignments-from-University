/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */


package controller;

public class CommandFactory {

	public static Command getCommand(String commandType) {
		commandType = commandType.replaceAll("\\s", "");
		try {
			if(commandType.equalsIgnoreCase("AddLatex")) {
				String contents = LatexEditorController.contents;
				String textToBeReplaced = LatexEditorController
						.textToBeReplaced;
				String latexCommandID = LatexEditorController
						.latexCommandID;
				return new AddLatexCommand(textToBeReplaced, contents,
						latexCommandID);
			}else if(commandType.equalsIgnoreCase("EditDocument")) {
				String documentContents = LatexEditorController
						.document.getContents();
				return new EditDocumentCommand(documentContents);
			}else if(commandType.equalsIgnoreCase("SaveDocument")) {
				String filePath = LatexEditorController.filePath;
				return new SaveDocumentCommand(filePath);
			}else if(commandType.equalsIgnoreCase("LoadDocument")) {
				String filePath = LatexEditorController.filePath;
				return new LoadDocumentCommand(filePath);
			}else if(commandType.equalsIgnoreCase(
					"EnableVersionsManagement")) {
				return new EnableVersionsManagementCommand();
			}else if(commandType.equalsIgnoreCase(
					"SetStableStrategy")) {
				return new SetStableStrategyCommand();
			}else if(commandType.equalsIgnoreCase(
					"SetVolatileStrategy")) {
				return new SetVolatileStrategyCommand();
			}else if(commandType.equalsIgnoreCase(
					"CreateNewDocument")) {
				String templateID = LatexEditorController.templateID;
				return new CreateNewDocumentCommand(templateID);
			}else if(commandType.equalsIgnoreCase(
					"DisableVersionsManagement")) {
				return new DisableVersionsManagementCommand();
			}else if(commandType.equalsIgnoreCase(
					"RollbackToPreviousVersion")) {
				return new RollbackToPreviousVersionCommand();
			}else {
				throw new Exception("Command type: " + "'" +
			commandType + "'" + " is not supported");
			}
		}catch(Exception E) {
			E.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
}
