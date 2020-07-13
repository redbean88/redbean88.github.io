package factory;

public class SimplePizzaFactory {
	public Pizza createPizza(String type){ //이런 경우에는 static메소드로 선언하는 경우가 종종 있음.

		Pizza pizza = null;

		if(type.equals("cheese")) pizza = new CheesePizza();
		if(type.equals("pepper")) pizza = new PepperoniPizza();
		if(type.equals("clam")) pizza = new ClamPizza();
		if(type.equals("veggie")) pizza = new VeggiePizza();
		return pizza;
	}


}
