package strategy.behavior;

public class Squack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("아무소리도 나지 않습니다.");
	}

}
