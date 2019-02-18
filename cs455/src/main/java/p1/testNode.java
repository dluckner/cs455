package p1;
import overlay.transport.*;
import overlay.node.*;
import overlay.wireformats.*;
import java.io.*;
import java.net.*;

public class testNode implements Runnable{
	private ServerSocket myServerSocket;
	private Socket mysocket;
	private TCPSender sender;
	private TCPReceiver receiver;
	public testNode() {
		try {
			this.myServerSocket = new ServerSocket(4001,1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		System.out.println("TNode Running...");
		try {
			this.mysocket = new Socket("192.168.0.105", 4000);
			this.sender = new TCPSender("TestR",mysocket);
			this.receiver = new TCPReceiver("TestR",mysocket);
			this.receiver.run();
			Event lastEvent = this.receiver.getEvent();
			System.out.println("TNode: Event Found: Type="+lastEvent.getType()+" <"+lastEvent.getString()+">");
			System.out.println("TNode Done...");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
	}
	public void close() {
		this.sender.cleanup();
		this.receiver.cleanup();
		try {
			this.myServerSocket.close();
			this.mysocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
