package decorator.decorator;

import decorator.Beverage;
/**
 * 음료를 꾸며주는 데코레이터 
 * 추상메소드를 쓰는 이유가 여기서 나온다
 * 인터페이스를 통한 메소드 강제에서 벗어날수 있다
 * 현재 cost 메소드를 구현 하지 않았다
 * @author User
 *
 */
public abstract class Decorator extends Beverage {

	//public abstract String getBeverageNm();//추상 메소드를 통해 구현체에 해당 메소드 구현은 알려준다 //필요성을 모르겠다
}
