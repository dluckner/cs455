package overlay.wireformats;

public class Message_Register_Response extends Event{
	boolean debug = false;
	Integer type;
	String IPAddress;
	Integer portNumber;
	String info;
	Byte status;
	
	public Message_Register_Response(byte success, String info) {
		super(0, getBytes(success,info));
		this.type = 0;
		this.status = success;
		this.info = info;
	}
	
	public Message_Register_Response(Integer type, byte[] bytes) {
		super(0, bytes);
		this.type = 0;
	}
	
	public Integer getType() {return this.type;}
	
	private static byte[] getBytes(byte success, String info) {
		String output = success+","+info;
		return output.getBytes();
	}
}
