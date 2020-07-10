package decorator;

/**
 * 전체 음료의 가격을 정의 하는 추상 클래스
 * @author User
 *
 */
public abstract class Beverage {
	//private String beverageNm = "아직 음료를 정하지 않았습니다"; ?? 왜필요하지

	public String getBeverageNm() {
		return "아직 음료를 정하지 않았습니다";
	}

	public abstract int cost();
}
