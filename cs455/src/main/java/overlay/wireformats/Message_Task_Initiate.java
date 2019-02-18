package overlay.wireformats;

public class Message_Task_Initiate extends Event{

	public Message_Task_Initiate(Integer type, byte[] bytes) {
		super(5, bytes);
	}
	
	public Message_Task_Initiate(int numberOfRounds) {
		super(5, getBytes(numberOfRounds));
	}
	
	private static byte[] getBytes(int numberOfRounds) {
		return Integer.toString(numberOfRounds).getBytes();
	}
}
