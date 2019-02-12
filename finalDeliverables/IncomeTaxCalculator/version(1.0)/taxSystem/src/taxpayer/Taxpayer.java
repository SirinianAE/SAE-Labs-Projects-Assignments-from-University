package taxpayer;

import java.util.ArrayList;
import receipt.Receipt;

public abstract class Taxpayer {
	private String name = "nobody";
	private int afm = 0;
	private String familyStatus = "none";
	private float income = 0;
	private ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	private String taxpayerInformationString = "";
	
	public abstract double getBasicTax();
	
	public int getTotalReceiptsGathered(){
		return receipts.size();
	}
	
	public int getCategoryNumberOfReceipts(String receiptCategory){
		int categoryNumberOfReceipts = 0;
		for(int i=0; i<receipts.size(); i++){
			if(receipts.get(i).getCategory().equals(receiptCategory)){
				categoryNumberOfReceipts += 1;
			}
		}
		return categoryNumberOfReceipts;
	}
	
	public double getCategoryReceiptAmountPaid(String receiptCategory){
		double categoryReceiptAmountPaid = 0;
		for(int i=0; i<receipts.size(); i++){
			if(receipts.get(i).getCategory().equals(receiptCategory)){
				categoryReceiptAmountPaid += receipts.get(i).getAmountPaid();
			}
		}
		return categoryReceiptAmountPaid;
	}
	//TODO income na al
	public double getTaxIncrease(){
		double receiptTotalAmountPaid = this.getReceiptTotalAmountPaid();
		if(receiptTotalAmountPaid >= 0*income/100 && receiptTotalAmountPaid < 20*income/100){
			return 8*income / 100;
		}
		else if(receiptTotalAmountPaid >= 20*income/100 && receiptTotalAmountPaid < 40*income/100){
			return 4*income / 100;
		}
		else if(receiptTotalAmountPaid >= 40*income/100 && receiptTotalAmountPaid < 60*income/100){
			return (-1)*15*income / 100;
		}
		else if(receiptTotalAmountPaid >= 60*income/100){
			return (-1)*30*income / 100;
		}
		else{
			System.out.println("Something went wrong calculating tax increase!");
			return -1;
		}
	}
	
	private double getReceiptTotalAmountPaid(){
		double receiptTotalAmountPaid = 0;
		for(int i=0; i<receipts.size(); i++){
			receiptTotalAmountPaid += receipts.get(i).getAmountPaid();
		}
		return receiptTotalAmountPaid;
	}
	
	public double getTotalTax(){
		double basicTax = this.getBasicTax();
		double taxIncrease = this.getTaxIncrease();
		return basicTax + taxIncrease;
	}
	
	public ArrayList<Receipt> getReceipts(){
		return receipts;
	}
	
	public void setReceipts(ArrayList<Receipt> receipts){
		this.receipts = receipts;
	}
	
	public Taxpayer getInitializedTaxpayer(ArrayList<String> myList){
		initializeTaxpayer(myList);
		return this;
	}
	
	public void initializeTaxpayer(ArrayList<String> myList){
		name = myList.get(0);
        myList.remove(0);
        afm = Integer.parseInt(myList.get(0));
        myList.remove(0);
        familyStatus = myList.get(0);
        myList.remove(0);
        income = Float.parseFloat(myList.get(0));
        myList.remove(0);
        int i = 0;
        while(i<myList.size()){
        	ArrayList<String> receiptDataList = new ArrayList<String>();
        	Receipt newReceipt = new Receipt();
        	for(int j=i; j<i+9; j++){
        		receiptDataList.add(myList.get(j));
        	}
        	newReceipt.initializeReceipt(receiptDataList);
        	receipts.add(newReceipt);
        	i=i+9;
        }
	}
	
	public void createTaxpayerInformationString(){
		taxpayerInformationString = " name : " + name + " afm : " + afm + " family status : " 
				+ familyStatus + " income : " + income + "/n";
		for(int i=0; i<receipts.size(); i++){
			taxpayerInformationString += " receipt code: " +  receipts.get(i).getReceiptCode();
			taxpayerInformationString += " date of issue: " + receipts.get(i).getDateOfIssue();
			taxpayerInformationString += " category: " + receipts.get(i).getCategory();
			taxpayerInformationString += " amount paid: " + receipts.get(i).getAmountPaid();
			taxpayerInformationString += " company name: " + receipts.get(i).getSeller().getCompanyName();
			taxpayerInformationString += " country: " + receipts.get(i).getSeller().getCountry();
			taxpayerInformationString += " city: " + receipts.get(i).getSeller().getCity();
			taxpayerInformationString += " street: " + receipts.get(i).getSeller().getStreet();
			taxpayerInformationString += " street number: " + receipts.get(i).getSeller().getStreetNumber();
		}
	}
	
	public String getFamilyStatus(){
		return familyStatus;
	}
	
	public void setFamilyStatus(String familyStatus){
		this.familyStatus = familyStatus;
	}
	
	public float getIncome(){
		return income;
	}
	
	public void setIncome(float income){
		this.income = income;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getAfm(){
		return afm;
	}
	
	public void setAfm(int afm){
		this.afm = afm;
	}
	
	public String getTaxpayerInformationString(){
		return taxpayerInformationString;
	}
}