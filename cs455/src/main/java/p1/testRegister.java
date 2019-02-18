package p1;
import overlay.transport.*;
import overlay.node.*;
import overlay.wireformats.*;
import java.io.*;
import java.net.*;

public class testRegister implements Runnable{
	private ServerSocket myServerSocket;
	private Socket mysocket;
	private TCPSender sender;
	private TCPReceiver receiver;
	public testRegister() {
		try {
			this.myServerSocket = new ServerSocket(4000,1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		System.out.println("TRegi Running...");
		try {
			this.mysocket = myServerSocket.accept();
			this.sender = new TCPSender("TestR",mysocket);
			this.receiver = new TCPReceiver("TestR",mysocket);
			
//			Event msg = new Message_Register_Response((byte) 1,"Node Previously Registered");
			
//			Event msg = new Message_Register("192.168.0.105",4000);
			
//			Event msg = new Message_Deregister("192.168.0.105",4000);
			
			int weight = 4;
			Node node0 = new Node("TESTADDR",1000,2);
			Node node1 = new Node("TESTADDR",1001,2);
			Node node2 = new Node("TESTADDR",1002,2);
			node0.setConnection(node1, weight);
			node0.setConnection(node2, weight);
			Event msg = new Message_Nodes_List(node0);
			
//			int weight = 4;
//			Node node0 = new Node("TESTADDR0",1000,2);
//			Node node1 = new Node("TESTADDR1",1001,2);
//			Node node2 = new Node("TESTADDR2",1002,2);
//			node0.setConnection(node1, weight);
//			node0.setConnection(node2, weight);
//			Node nodeArray[] = node0.getConnectionsArray();
//			Event msg = new Message_Link_Weights(node0);
			
//			Event msg = new Message_Task_Initiate(3);
			
//			Event msg = new Message_Task_Complete();
			
//			Event msg = new Message_Task_Summary_Request();
			
			this.sender.sendData(msg.marshall());
			System.out.println("TRegi Done...");
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
