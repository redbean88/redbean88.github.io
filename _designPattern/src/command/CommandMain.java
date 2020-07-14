package command;

public class CommandMain {
	public static void main(String[] args) {
		
		SimpleRemoteControl remote = new SimpleRemoteControl();
		LightOnCommand lightOn = new LightOnCommand(new Light());
		remote.setCommand(lightOn);
		remote.burronWasPressed();
	}
}
