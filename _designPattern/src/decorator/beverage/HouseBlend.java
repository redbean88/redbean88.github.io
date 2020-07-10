package decorator.beverage;

import decorator.Beverage;

public class HouseBlend extends Beverage{
	
	//private String beverageNm = "하우스 블렌딩";
	
	public String getBeverageNm() {
		return "하우스 블렌딩";
	}

	@Override
	public int cost() {
		return 3500;
	}

}
