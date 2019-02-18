package overlay.wireformats;

public class Message_Deregister extends Event{
	boolean debug = false;
	Integer type;
	String IPAddress;
	Integer portNumber;
	
	public Message_Deregister(String address, int portNumber) {
		super(2, getBytes(address,portNumber));
		this.type = 2;
		this.IPAddress = address;
		this.portNumber = portNumber;
		String convertString = address+portNumber;
		byte[] bytes = BitOpHelper.StringToByteArray(address+portNumber);
		if(debug) {System.out.println("Building Register Message: ");}
		if(debug) {System.out.println("	To Address: "+address+" , To Port: "+portNumber);}
	}
	
	public Message_Deregister(Integer type, byte[] bytes) {
		super(2, bytes);
		this.type = type;
		this.IPAddress = BitOpHelper.byteArraytoString(bytes).substring(0,bytes.length-4);
		this.portNumber = Integer.parseInt(BitOpHelper.byteArraytoString(bytes).substring(bytes.length-4,bytes.length));
		if(debug) {System.out.println("Building Register Message: ");}
		if(debug) {System.out.println("	To Address: <"+this.IPAddress+">, To Port: <"+this.portNumber+">");}
	}
	
	public Integer getType() {return this.type;}
	
	public String getAddress() {return this.IPAddress;}
	
	public Integer getPortNumber() {return this.portNumber;}
	
	private static byte[] getBytes(String address, int portNumber) {
		String output = address+","+portNumber;
		return output.getBytes();
	}
}
