package overlay.wireformats;

public class Message_Task_Complete extends Event{

	public Message_Task_Complete(Integer type, byte[] bytes) {
		super(6, bytes);
	}
	
	public Message_Task_Complete() {
		super(6, getBytes());
	}
	
	private static byte[] getBytes() {
		String emptyString = "";
		return emptyString.getBytes();
	}

}
