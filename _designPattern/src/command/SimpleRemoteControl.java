package command;

/**
 * 인보커 클래스
 * @author User
 *
 */
public class SimpleRemoteControl {
	Command slot;

	{
	}

	public void setCommand(Command slot) {
		this.slot = slot;
	}
	
	public void  burronWasPressed() {
		slot.execute();
	}
	
}
