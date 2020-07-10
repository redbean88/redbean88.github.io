package strategy.behavior;
/**
 * 행동 구현체
 * 인터페이스를 통해 구현을 강제한다
 * @author User
 *
 */
public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("꽥꽥");
	}

}
