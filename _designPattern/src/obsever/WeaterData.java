package obsever;

import java.util.ArrayList;
import java.util.List;

import obsever.observer.Observer;
import obsever.subject.Subject;
/**
 * 
 * @author User
 *
 */
public class WeaterData implements Subject{
	private List<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;

	{
		this.observers = new ArrayList<>();
	}

	public void measurementsChanged(){ this.notifyObservers(); }
	
	public void setMeasurementsChanged(float t, float h, float p){	//값이 세팅된다고 가정.
		this.temperature = t;
		this.humidity = h;
		this.pressure = p;
		this.measurementsChanged();	//셋팅시 각 condition에 생태 변경
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this.temperature, this.humidity, this.pressure);
		}
	}

	@Override
	public void registerobserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if(observers.contains(observer)) observers.remove(observer);
	}

}
