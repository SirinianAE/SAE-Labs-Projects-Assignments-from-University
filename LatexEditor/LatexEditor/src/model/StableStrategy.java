/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 */

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class StableStrategy implements VersionsStrategy {
	private String filePath = "stable_strategy_cache.tex";
	private String documentInFileSeparator = "<++>";
	private ArrayList<Document> bunchOfVersions =
			new ArrayList<Document>();

	public StableStrategy() {
		cleanOldStableStrategysFiles();
	}

	public void putVersion(Document newVersion) {
		writeToFile(newVersion.toString());
		writeToFile(documentInFileSeparator);
	}

	public Document getVersion() {
		Document lastVersion = null;
		parseDocumentsFromFile();
		if (!bunchOfVersions.isEmpty()) {
			try {
				lastVersion =  bunchOfVersions
						.get(bunchOfVersions.size() - 1).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return lastVersion;
	}

	public void removeVersion() {
		parseDocumentsFromFile();
		if(bunchOfVersions.size() > 0) {
			bunchOfVersions.remove(bunchOfVersions.size() - 1);
		}
		cleanOldStableStrategysFiles();
		for(Document x: bunchOfVersions) {
			putVersion(x);
		}
		if(bunchOfVersions.size() == 0){
			writeToFile("");
		}
	}

	public void setBunchOfVersions(
			ArrayList<Document> bunchOfVersions){
		for(int i = 0; i < bunchOfVersions.size(); i++){
			putVersion(bunchOfVersions.get(i));
		}
	}

	public ArrayList<Document> getBunchOfVersions(){
		return bunchOfVersions;
	}

	private void writeToFile(String text){
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		PrintWriter pr = null;
		try {
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			pr.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pr.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void parseDocumentsFromFile() {
		Scanner inputReader = null;
		String author = null;
		String date = null;
		String copyright = null;
		String versionID = null;
		String contents = null;
		String templateID = null;
		bunchOfVersions = new ArrayList<Document>();

		try {
			inputReader = new Scanner(new FileInputStream(filePath));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		while(inputReader.hasNextLine()) {
			String fields[] = inputReader.nextLine().split(":");

			if(fields[0].equals("Author")) {
				author = "";
				for(int i = 1; i < fields.length; i++) {
					author += fields[i];
				}
				author = author.trim();
				if (author.equals("null")) {
					author = null;
				}
			}else if(fields[0].equals("Date")) {
				date = "";
				for(int i = 1; i < fields.length; i++) {
					date += fields[i];
				}
				date = date.trim();
				if (date.equals("null")) {
					date = null;
				}
			}else if(fields[0].equals("Copyright")) {
				copyright = "";
				for(int i = 1; i < fields.length; i++) {
					copyright += fields[i];
				}
				copyright = copyright.trim();
				if (copyright.equals("null")) {
					copyright = null;
				}
			}else if(fields[0].equals("VersionID")) {
				versionID = "";
				for(int i = 1; i < fields.length; i++) {
					versionID += fields[i];
				}
				versionID = versionID.trim();
				if(versionID.equals("null")) {
					versionID = null;
				}
			}else if(fields[0].equals("TemplateID")) {
				templateID = "";
				for(int i = 1; i < fields.length; i++) {
					templateID += fields[i];
				}
				templateID = templateID.trim();
				if(templateID.equals("null")) {
					templateID = null;
				}
			}else if(fields[0].equals("Contents")) {
				contents = "";
				for(int i = 1; i < fields.length; i++) {
					contents += fields[i];
				}
			}else if(fields[0].equals(documentInFileSeparator)) {
				templateID = templateID.trim().toLowerCase();
				if(templateID.equals("article")) {
					ArticleDocument newDocument=new ArticleDocument();
					newDocument.setAuthor(author);
					newDocument.setContents(contents);
					newDocument.setCopyright(copyright);
					newDocument.setDate(date);
					newDocument.setTemplateID(templateID);
					newDocument.setVersionID(versionID);
					bunchOfVersions.add(newDocument);
				}else if(templateID.equals("book")) {
					BookDocument newDocument = new BookDocument();
					newDocument.setAuthor(author);
					newDocument.setContents(contents);
					newDocument.setCopyright(copyright);
					newDocument.setDate(date);
					newDocument.setTemplateID(templateID);
					newDocument.setVersionID(versionID);
					bunchOfVersions.add(newDocument);
				}else if(templateID.equals("letter")) {
					LetterDocument newDocument = new LetterDocument();
					newDocument.setAuthor(author);
					newDocument.setContents(contents);
					newDocument.setCopyright(copyright);
					newDocument.setDate(date);
					newDocument.setTemplateID(templateID);
					newDocument.setVersionID(versionID);
					bunchOfVersions.add(newDocument);
				}else if(templateID.equals("report")) {
					ReportDocument newDocument = new ReportDocument();
					newDocument.setAuthor(author);
					newDocument.setContents(contents);
					newDocument.setCopyright(copyright);
					newDocument.setDate(date);
					newDocument.setTemplateID(templateID);
					newDocument.setVersionID(versionID);
					bunchOfVersions.add(newDocument);
				}else if(templateID.equals("blank")){
					ReportDocument newDocument = new ReportDocument();
					newDocument.setAuthor(author);
					newDocument.setContents(contents);
					newDocument.setCopyright(copyright);
					newDocument.setDate(date);
					newDocument.setTemplateID(templateID);
					newDocument.setVersionID(versionID);
					bunchOfVersions.add(newDocument);
				}
			}else {
				contents += "\n";
				for(int i = 0; i < fields.length; i++) {
					contents += fields[i];
				}
			}
		}

		inputReader.close();
	}

	private void cleanOldStableStrategysFiles() {
		Path fileToDeletePath = Paths.get(filePath);
		try {
			if(new File(filePath).exists()) {
				Files.delete(fileToDeletePath);
			}
		}catch (IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
