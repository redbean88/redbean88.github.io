package decorator.decorator;

import decorator.Beverage;

public class Mocha extends Decorator {
	private Beverage beverage;
	
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getBeverageNm() {
		return beverage.getBeverageNm() + " 모카";
	}

	@Override
	public int cost() {
		return beverage.cost() + 400;
	}

}
