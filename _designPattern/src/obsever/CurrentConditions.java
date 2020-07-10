package obsever;

import obsever.element.Element;
import obsever.observer.Observer;
import obsever.subject.Subject;

/**
 * 상태 객체
 * @author User
 *
 */
public class CurrentConditions implements Observer, Element{
	private float temperature;
	private float humidity;
	private Subject subject;

	
	public CurrentConditions(Subject subject) {
		this.subject = subject;
		this.subject.registerobserver(this);	// 옵저버에 등록
	}

	@Override
	public void display() {
		System.out.println("Current conditions : "+temperature+" , "+humidity);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.display();
	}

}
