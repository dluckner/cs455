package overlay.wireformats;

public class Event {
	public final Integer REGISTER_RESPONSE = 0;
	public final Integer REGISTER_REQUEST = 1;
	public final Integer DEREGISTER_REQUEST = 2;
	public final Integer MESSAGING_NODES_LIST = 3;
	public final Integer LINK_WEIGHTS = 4;
	public final Integer TASK_INITIATE = 5;
	public final Integer TASK_COMPLETE = 6;
	public final Integer PULL_TRAFFIC_SUMMARY = 7;
	public final Integer TRAFFIC_SUMMARY = 8;
	
	private final int type;
	private final byte[] bytes;
	
	public Event(Integer type, byte[] bytes) {
		this.type = type;
		this.bytes = bytes;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public byte[] marshall() {
		byte output[] = new byte[bytes.length+1];
		output[0]=Byte.valueOf((Integer.toString(type)));
		for(int i=0;i<bytes.length;i++) {output[i+1]=bytes[i];}
		return output;
	}
	
	public String getString() {
		return BitOpHelper.byteArraytoString(this.bytes).substring(1);
	}

	public void printDetails() {
		System.out.println("	MessageType: "+this.getType()+", Data: "+this.getString());
	}
}
