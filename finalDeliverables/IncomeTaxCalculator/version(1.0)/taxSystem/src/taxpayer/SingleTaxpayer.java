package taxpayer;

public class SingleTaxpayer extends Taxpayer {
	public double getBasicTax() {
		float income = getIncome();
		if (0 >= income && income < 24680) {
			return 5.35 * income / 100;
		}
		else if (24680 >= income && income < 81080) {
			return 1320.38 + 7.05 * (income - 24680) / 100;
		}
		else if (81080 >= income && income < 90000) {
			return 5296.58 + 7.85 * (income - 81080) / 100;
		}
		else if (90000 >= income && income < 152540) {
			return 5996.80 + 7.85 * (income - 90000) / 100;
		}
		else {
			return 10906.19 + 9.85 * (income - 152540) / 100;
		}
	}
}
