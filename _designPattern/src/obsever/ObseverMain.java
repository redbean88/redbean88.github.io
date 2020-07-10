package obsever;

public class ObseverMain {
	public static void main(String[] args) {
		WeaterData weatherDate= new WeaterData();
		CurrentConditions currentConditions = new CurrentConditions(weatherDate); 
		weatherDate.setMeasurementsChanged(80, 70, 60.1f);
	}
}
