package taxpayer;

public class TaxpayerFactory {
	
	public HeadOfHouseholdTaxpayer createHeadOfHouseholdTaxpayer(){
		HeadOfHouseholdTaxpayer newHeadOfHouseholdTaxpayer = new HeadOfHouseholdTaxpayer();
		return newHeadOfHouseholdTaxpayer;
	}

	public MarriedFilingJointlyTaxpayer createMarriedFilingJointlyTaxpayer(){
		MarriedFilingJointlyTaxpayer newMarriedFilingJointlyTaxpayer = new MarriedFilingJointlyTaxpayer();
		return newMarriedFilingJointlyTaxpayer;
	}
	
	public MarriedFilingSeparatelyTaxpayer createMarriedFilingSeparatelyTaxpayer(){
		MarriedFilingSeparatelyTaxpayer newMarriedFilingSeparatelyTaxpayer = new MarriedFilingSeparatelyTaxpayer();
		return newMarriedFilingSeparatelyTaxpayer;
	}
	
	public SingleTaxpayer createSingleTaxpayer(){
		SingleTaxpayer newSingleTaxpayer = new SingleTaxpayer();
		return newSingleTaxpayer;
	}
}
