package outputManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import taxpayer.Taxpayer;

public class LogFileCreator {
	private String domain;
	private Taxpayer outputManagerTaxpayer = null;
	private ArrayList<String> bunchOfOpeningTagData = 
			new ArrayList<String>();
	private ArrayList<String> bunchOfUsefulData;
	private ArrayList<String> bunchOfDataForTextFile;
	private ArrayList<String> bunchOfDataForXmlFile;
	private String textFullDomain;
	private String xmlFullDomain;
	private File xmlFile;
	private File textFile;
	
	public LogFileCreator(Taxpayer taxp, String domain){
		createBunchOfUsefulData();
		outputManagerTaxpayer = taxp;
		this.domain = domain;
		initializeFullPathForTxtFile(outputManagerTaxpayer);
		initializeFullPathForXmlFile(outputManagerTaxpayer);
		textFile = new File(textFullDomain);
		xmlFile = new File(xmlFullDomain);
		initializeBunchOfOpeningTagData();
	}
	
	public void createLogTextFile(){
		completeBunchOfDataForTextFile();
		try{
			PrintWriter output = new PrintWriter(textFile);
			for(String myString: bunchOfDataForTextFile){
				output.println(myString);
			}
			output.close();
		}catch (IOException ex){
			System.out.printf("ERROR: %s\n", ex);
		}
	}

	public void createLogXmlFile(){
		completeBunchOfDataForXmlFile();
		try{
			PrintWriter output = new PrintWriter(xmlFile);
			for(String myString: bunchOfDataForXmlFile){
				output.println(myString);
			}
			output.close();
		}catch (IOException ex){
			System.out.printf("ERROR: %s\n", ex);
		}
	}
	
	private void completeBunchOfDataForTextFile(){
		createBunchOfUsefulData();
		bunchOfDataForTextFile = new ArrayList<String>();
		for(int i=0; i<bunchOfUsefulData.size(); i++){
			bunchOfDataForTextFile.add(createStartingTextFormat(
					bunchOfOpeningTagData.get(i)) +
					bunchOfUsefulData.get(i));
		};
	}
	
	private void completeBunchOfDataForXmlFile(){
		createBunchOfUsefulData();
		bunchOfDataForXmlFile = new ArrayList<String>();
		for(int i=0; i<bunchOfUsefulData.size(); i++){
			bunchOfDataForXmlFile.add(createStartingXmlFormat(
					bunchOfOpeningTagData.get(i)) +
					bunchOfUsefulData.get(i) +
					createFinishingXmlFormat(bunchOfOpeningTagData
							.get(i)));
		}
	}
	
	private void initializeBunchOfOpeningTagData(){
		bunchOfOpeningTagData.add("Name");
		bunchOfOpeningTagData.add("AFM");
		bunchOfOpeningTagData.add("Income");
		bunchOfOpeningTagData.add("BasicTax");
		bunchOfOpeningTagData.add("TaxIncrease");
		bunchOfOpeningTagData.add("TotalTax");
		bunchOfOpeningTagData.add("Receipts");
		bunchOfOpeningTagData.add("Entertainment");
		bunchOfOpeningTagData.add("Basic");
		bunchOfOpeningTagData.add("Travel");
		bunchOfOpeningTagData.add("Health");
		bunchOfOpeningTagData.add("Other");
	}
	
	private void createBunchOfUsefulData(){
		bunchOfUsefulData = 
				new ArrayList<String>();
		ArrayList<String> bunchOfData =
				outputManagerTaxpayer.getTaxpayerBunchOfData();
		bunchOfUsefulData.add(bunchOfData.get(0));
		bunchOfUsefulData.add(bunchOfData.get(1));
		bunchOfUsefulData.add(bunchOfData.get(3));
		bunchOfUsefulData.add(bunchOfData.get(4));
		bunchOfUsefulData.add(bunchOfData.get(5));
		bunchOfUsefulData.add(bunchOfData.get(6));
		bunchOfUsefulData.add(bunchOfData.get(7));
		bunchOfUsefulData.add(bunchOfData.get(8));
		bunchOfUsefulData.add(bunchOfData.get(9));
		bunchOfUsefulData.add(bunchOfData.get(10));
		bunchOfUsefulData.add(bunchOfData.get(11));
		bunchOfUsefulData.add(bunchOfData.get(12));
	}
	
	private String createStartingXmlFormat(String keyWord){
		return "<"+keyWord+"> ";
	}
	
	private String createFinishingXmlFormat(String keyWord){
		return " </"+keyWord+">";
	}
	
	private String createStartingTextFormat(String keyWord){
		return keyWord+": ";
	}
	
	private void initializeFullPathForXmlFile(Taxpayer taxp){
		xmlFullDomain = domain+bunchOfUsefulData.get(1)+"_LOG.xml";
	}
	
	private void initializeFullPathForTxtFile(Taxpayer taxp){
		textFullDomain = domain+bunchOfUsefulData.get(1)+"_LOG.txt";
	}
	
	public void setDomain(String domain){
		this.domain = domain;
	}
}
