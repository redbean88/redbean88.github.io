package obsever.subject;

import obsever.observer.Observer;

public interface Subject {
	public void notifyObservers();
	void registerobserver(Observer observer);
	void removeObserver(Observer observer);
}
