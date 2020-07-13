package factory;

public class PizzaMain {
	public static void main(String[] args) {
		PizzaStore pizzaStore = new NYPizzaStore();	//추상클래스는 인스턴스 생성이 불가능 하기 때문에, 상속하여 구현한 구현 클래스를 이용해야 한다
		pizzaStore.orderPizza("cheese");
		
		PizzaStore pizzaStore2 = new ChicagoPizzaStore();	//추상클래스는 인스턴스 생성이 불가능 하기 때문에, 상속하여 구현한 구현 클래스를 이용해야 한다
		pizzaStore2.orderPizza("cheese");
	}
}
