package decorator.decorator;

import decorator.Beverage;

public class Whip extends Decorator {
	private Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getBeverageNm() {
		return beverage.getBeverageNm() + " 휘핑크림";
	}

	@Override
	public int cost() {
		return beverage.cost() + 500;
	}

}
