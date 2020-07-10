package strategy.behavior;
/**
 * 행동 구현체
 * 인터페이스를 통해 구현을 강제한다
 * @author User
 *
 */
public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("날지 못해요");
	}

}
