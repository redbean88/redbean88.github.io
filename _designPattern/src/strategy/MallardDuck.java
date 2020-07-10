package strategy;

import strategy.behavior.FlyWithWings;
import strategy.behavior.Quack;

public class MallardDuck extends Duck {

	{
		super.flyBehavior = new FlyWithWings();
		super.quackBehavior = new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("청둥 오리");
	}

}
