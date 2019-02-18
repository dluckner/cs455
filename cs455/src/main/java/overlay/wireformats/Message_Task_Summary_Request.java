package overlay.wireformats;

public class Message_Task_Summary_Request extends Event{

	public Message_Task_Summary_Request(Integer type, byte[] bytes) {
		super(7, bytes);
	}
	
	public Message_Task_Summary_Request() {
		super(7, getBytes());
	}
	
	private static byte[] getBytes() {
		String emptyString = "";
		return emptyString.getBytes();
	}

}
