package p1;

public class Message_Register_Request implements Message {
	private int type;
	private String address;
	private int portNumber;
	
	public Message_Register_Request(int type, String address, int portNumber) {
		this.type = type;
		this.address = address;
		this.portNumber = portNumber;
	}
	
	public int getType() {return this.type;}
	public String getAddress() {return this.address;}
	public int getPortNumber() {return this.portNumber;}
}
