package factory;

public class NYPizzaStore extends PizzaStore {

	@Override
	Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type.equals("cheese")) pizza = new NYStyleCheesePizza();
		if(type.equals("pepper")) pizza = new PepperoniPizza();
		if(type.equals("clam")) pizza = new ClamPizza();
		if(type.equals("veggie")) pizza = new VeggiePizza();
		return pizza;
	}

}
