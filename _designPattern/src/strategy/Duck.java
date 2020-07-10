package strategy;

import strategy.behavior.FlyBehavior;
import strategy.behavior.QuackBehavior;
/**
 * 오리 추상화 객체
 * 기능은 인터페이스로 추상화 한다
 * @author User
 *
 */
public abstract class Duck {

	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;

	public void swim(){
		System.out.println("물에 떠있습니다.");
	}
	
	public abstract void display();

	public void performQuack(){
		quackBehavior.quack();
	}

	public void performFly(){
		flyBehavior.fly();
	}

}
