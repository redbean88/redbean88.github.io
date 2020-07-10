package decorator;

import decorator.beverage.HouseBlend;
import decorator.decorator.Mocha;

public class Cafe {
	public static void main(String[] args) {
		Beverage beverage = new Beverage() {
			
			@Override
			public int cost() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		System.out.println(beverage.getBeverageNm());	//아직 음료를 정하지 않았습니다
		System.out.println(beverage.cost());			// 0
		
		
		Beverage houseBlend = new HouseBlend();
		System.out.println(houseBlend.getBeverageNm());	//하우스 블렌딩
		System.out.println(houseBlend.cost());			//3500
		houseBlend = new Mocha(houseBlend);
		System.out.println(houseBlend.getBeverageNm());	//하우스 블렌딩 모카
		System.out.println(houseBlend.cost());			//3900
		
	}
}
