package decorator.decorator;

import decorator.Beverage;

public class Soy extends Decorator {
	private Beverage beverage;
	
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getBeverageNm() {
		return beverage.getBeverageNm() + " 콩";
	}

	@Override
	public int cost() {
		return beverage.cost() + 700;
	}

}
