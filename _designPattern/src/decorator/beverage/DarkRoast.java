package decorator.beverage;

import decorator.Beverage;

public class DarkRoast extends Beverage{
	
	private String beverageNm = "다크 로스트";
	
	public String getBeverageNm() {
		return beverageNm;
	}

	@Override
	public int cost() {
		return 4000;
	}

}
