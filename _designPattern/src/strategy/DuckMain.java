package strategy;

public class DuckMain {
	public static void main(String[] args) {

		Duck duck = new MallardDuck();
		duck.performFly();
		duck.performQuack();
		
	}
}
