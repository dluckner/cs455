package overlay.transport;
import java.io.*;
import java.net.*;

import overlay.wireformats.BitOpHelper;

public class TCPSender {
	private boolean debug = false;
	private boolean debug2 = false;
	private String myNode;
	private Socket socket;
	private DataOutputStream dout;
	
	public TCPSender(String name, Socket socket) throws IOException {
		this.myNode = name;
		this.socket = socket;
		
		dout = new DataOutputStream(socket.getOutputStream());
		if(debug2) {System.out.println("SS<"+this.myNode+"> - Has opened Sender");}
	}
	
	public void sendData(byte[] dataToSend) throws IOException {
		int dataLength = dataToSend.length;
		dout.writeInt(dataLength);
		dout.write(dataToSend, 0, dataLength);
		dout.flush();
		if(debug) {System.out.println("SS<"+this.myNode+"> has sent message: "+BitOpHelper.byteArraytoString(dataToSend));}
	}
	
	public void cleanup() {
		try {
			this.socket.close();
			this.dout.close();
		} catch (IOException e) {
			if(debug)System.out.println("GOTCHA-A");
			e.printStackTrace();
		}
	}
}
