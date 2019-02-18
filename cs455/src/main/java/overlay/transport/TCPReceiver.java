package overlay.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import overlay.wireformats.*;

public class TCPReceiver implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private String myNode;
	private Socket socket;
	private DataInputStream din;
	private Event newEvent = null;
	
	public TCPReceiver(String name, Socket socket) throws IOException {
		this.socket = socket;
		this.myNode = name;
		din = new DataInputStream(socket.getInputStream());
		if(debug2) {System.out.println("SR<"+this.myNode+"> - Has opened Reciever");}
	}
	
	public Event getEvent() {
		Event lastEvent = this.newEvent;
		this.newEvent = null;
		return lastEvent;
	}
	
	public void run() {
		Thread.currentThread().setName(this.myNode+"-RCVRTHREAD");
		int dataLength;
		try {
		if(debug2) {System.out.println("SR<"+this.myNode+"> - Listening...");}
		//while (socket != null) {
		dataLength = din.readInt();
		
		byte[] data = new byte[dataLength];
		din.readFully(data, 0, dataLength);
		int type = data[0];
		this.newEvent = new Event(type, data);
		if(debug) {System.out.println("SR<"+this.myNode+"> Received Event Type="+this.newEvent.getType()+": <"+this.newEvent.getString()+">");}
		//}
		} catch (SocketException se) {
			System.out.println("SR<"+this.myNode+"> SocketException: "+se.getMessage());
		} catch (IOException ioe) {
			System.out.println("SR<"+this.myNode+"> IOException: "+ioe.getMessage());
		}
	}
	
	public void cleanup() {
		try {
			this.socket.close();
			this.din.close();
		} catch (IOException e) {
			if(debug)System.out.println("SR<"+this.myNode+"> IOException: CLEANUP()");
			e.printStackTrace();
		}
	}
}
