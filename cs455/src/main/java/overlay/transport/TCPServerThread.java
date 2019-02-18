package overlay.transport;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import overlay.wireformats.*;
import overlay.node.*;

public class TCPServerThread implements Runnable {
	private boolean debug = true;
	private boolean debug2 = false;
	private String myNode;
	private String myAddress;
	private int maxConnections;
	private Integer portNumber;
	private ServerSocket serverSocket;
	private int connectionCount;
	private TCPSender[] senders;
	private TCPReceiver[] receivers;
	private Node[] connections;
	private TCPSender registrySender;
	private TCPReceiver registryReceiver;
	private ArrayList<Event> eventQueue;
	private ArrayList<Node> nodesToConnectWith;
	private int type;
	private boolean shutDown = false;

	public TCPServerThread(String name, int type, String myAddress, int maxConnections) throws IOException {
		this.myNode = name;
		this.myAddress = myAddress;
		this.type = type;
		this.maxConnections = maxConnections;
		this.connectionCount = 0;
		eventQueue = new ArrayList<Event>();
		this.establishServerSocket();
		this.receivers = new TCPReceiver[maxConnections];
		this.senders = new TCPSender[maxConnections];
		this.connections = new Node[maxConnections];
		if(debug2&&this.type==0) {System.out.println("SC<"+this.myNode+"> - I am a register Thread!");}
		if(debug2&&this.type==1) {System.out.println("SC<"+this.myNode+"> - I am a m-node Thread!");}
	}
	
	public void shutdown() {this.shutDown=true;}
	public String getConnectionAddress(int index) {return connections[index].getAddress();}
	public Integer getConnectionPortNumber(int index) {return connections[index].getPort();}
	public ArrayList<Event> getEventQueue() {return this.eventQueue;}
	
	public void connectToRegister(String registryAddress, Integer registryPortNumber) {
		try {
			if(debug2) {System.out.println("SC<"+this.myNode+"> - attempt to register "+registryAddress+", Port: "+registryPortNumber);}
			Socket registrySocket = new Socket(registryAddress, registryPortNumber);
			this.registryReceiver = new TCPReceiver(myNode, registrySocket);
			this.registrySender = new TCPSender(myNode, registrySocket);
			Event msg = new Message_Register(myAddress,portNumber);
			this.registrySender.sendData(msg.marshall());
			if(debug2) {System.out.println("SC<"+this.myNode+"> - connected to register "+registryAddress+", Port: "+registryPortNumber);}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void obtainConnectionList() {
		if(debug2) {System.out.println("SC<"+this.myNode+"> - Waiting for list");}
		registryReceiver.run();
		if(debug2) {System.out.println("SC<"+this.myNode+"> - received list "+registryReceiver.getEvent().getString());}
	}
	
	public void registryAcceptConnections(int numberOfNodes) {
		if(debug) {System.out.println("SC<"+this.myNode+"> - waiting for "+numberOfNodes+" nodes...");}
		try {
		this.receivers = new TCPReceiver[numberOfNodes];
		this.senders = new TCPSender[numberOfNodes];
		for(int i=0;i<numberOfNodes;i++) {
			Socket nextSocket = this.serverSocket.accept();
			//Note that getInetAddress adds / for some reason... Verify this on cs servers
			String IPAddress = nextSocket.getInetAddress().toString().substring(1);
			Node nextNode = new Node(IPAddress, nextSocket.getPort(), this.maxConnections);
			connections[i] = nextNode;
			receivers[i] = new TCPReceiver(myNode, nextSocket);
			senders[i] = new TCPSender(myNode, nextSocket);
			this.connectionCount++;
		}
		if(debug) {System.out.println("SC<"+this.myNode+"> - All Messaging Nodes Accounted For!");}
		for(int i=0;i<receivers.length;i++) {
			receivers[i].run();
			eventQueue.add(receivers[i].getEvent());
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumberOfConnections() {return this.connectionCount;}
	
	public Integer getPortNumber() {return this.portNumber;}
	public synchronized Event getLastEvent() {
		if(eventQueue.size()<1) {return null;}
		Event last = eventQueue.get(0);
		eventQueue.remove(0);
		return last;
	}
//	public Event getLastEvent() {
//		if(eventQueue.size()<1) {
//			if(debug) {System.out.println("SC<"+this.myNode+"> - No Events Availible to grab");}
//			return null;
//		}
//		Event lastEvent = this.eventQueue.get(0);
//		//eventQueue.remove(0);
//		if(debug) {System.out.println("SC<"+this.myNode+"> - Pushing EVENT");}
//		return lastEvent;
//	}
	
	public void sendMessage(int session, byte[] data) {
		try {
			this.senders[session].sendData(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String address, Integer port, byte[] data) {
		int session = this.findSession(address, port);
		this.sendMessage(session, data);
	}
	
	public int findSession(String address, Integer port) {
		for(int i=0;i<senders.length;i++) {
			;
		}
		return 0;
	}
	
	public void run(){
		Thread.currentThread().setName(this.myNode+"-SVRTHREAD");
		this.obtainConnectionList();
//		Thread testThread = new Thread(this.registryReceiver);
//		testThread.start();
	}
	
	private void establishServerSocket() {
		//for(int i=1025;i<=49151;i++) {
		for(int i=5000;i<=5100;i++) {
			try {
				this.serverSocket = new ServerSocket(i, this.maxConnections);
				this.portNumber = i;
				if(debug2) {System.out.println("SC<"+this.myNode+"> opened port number "+i);}
				break;
			} catch(IOException e) {
				if(debug2) {System.out.println("SC<"+this.myNode+"> could not use port = " + i);};
			}
		}
	}
	
	private Socket establishSocket() {
		//for(int i=1025;i<=49151;i++) {
		for(int i=5000;i<=5100;i++) {
			try {
				this.serverSocket = new ServerSocket(i, this.maxConnections);
				this.portNumber = i;
				if(debug2) {System.out.println("SC<"+this.myNode+"> opened port number "+i);}
				break;
			} catch(IOException e) {
				if(debug2) {System.out.println("SC<"+this.myNode+"> could not use port = " + i);};
			}
		}
		return null;
	}
	
	public void cleanup() {
		if(debug) {System.out.println("SC<"+this.myNode+"> is closing...");}
		for(int i=0;i<connectionCount;i++) {
			this.receivers[i].cleanup();
			this.senders[i].cleanup();
		}
	}
}
