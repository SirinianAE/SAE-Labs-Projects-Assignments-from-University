package taxpayer;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
	public double getBasicTax(){
		float income = getIncome();
		if (0>=income && income<36080){
			return 5.35*income / 100;
		}
		else if(36080>=income && income<90000){
			return 1930.28 + 7.05*(income - 36080)/100;
		}
		else if(90000>=income && income<143350){
			return 5731.64 + 7.05*(income - 90000)/100;
		}
		else if(143350>=income && income<254240){
			return 9492.82 + 7.85*(income - 143350)/100;
		}
		else{
			return 18197.69 + 9.85*(income - 254240)/100;
		}
	}
}