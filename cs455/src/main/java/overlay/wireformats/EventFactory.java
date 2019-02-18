package overlay.wireformats;

import java.io.*;
import java.net.*;
import overlay.wireformats.*;
import overlay.transport.*;

public class EventFactory implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private int type; // 0=>Registry, 1=>Messaging Node
	private String name;
	private String myAddress;
	private String registryAddress;
	private Integer registryPortNumber;
	private Integer maxConnections;
	private TCPServerThread server;
	private Event nextEvent = null;
	private int action;
	private boolean shutDown = false;
	
	public EventFactory(int type, String name, String myAddress, String registryAddress, Integer registryPortNumber, Integer maxConnections) {
		this.type = type; // 0=>Registry, 1=>Messaging Node
		this.name = name;
		this.myAddress = myAddress;
		this.registryAddress = registryAddress;
		this.registryPortNumber = registryPortNumber;
		this.maxConnections = maxConnections;
		if(debug2) {System.out.println("EF<"+this.name+"> attempting Event Factory");}
		try {
			this.server = new TCPServerThread(name, type, myAddress, maxConnections);
			if(this.type==1) {this.server.connectToRegister(registryAddress, registryPortNumber);}
			if(this.type==1) {Thread serverThread = new Thread(this.server);serverThread.start();}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(debug2) {System.out.println("EF<"+this.name+"> Event Factory built");}
	}
	
	public void registryAcceptConnections(int numberOfNodes) {this.server.registryAcceptConnections(numberOfNodes);}
	
	public Integer getPortNumber() {return this.server.getPortNumber();}
	public Event getLastEvent() {return this.server.getLastEvent();}
	public int getNumberOfConnections() {return this.server.getNumberOfConnections();}
	
	public void shutDown() {this.server.shutdown(); this.shutDown = true;}
	public String getConnectionAddress(int index) {return this.server.getConnectionAddress(index);}
	public Integer getConnectionPortNumber(int index) {return this.server.getConnectionPortNumber(index);}
	
	public void run() {
		Thread.currentThread().setName(this.name+"-EVENTFACT");
//		if(debug) {System.out.println("EF<"+this.name+"> up and running!");}
//		int count = 10;
//		while(!shutDown) {
//			Event tempEvent = server.getLastEvent();
//			if(tempEvent!=null) {
//				if(debug) {System.out.println("EF<"+this.name+"> grabbing Event...");}
//				nextEvent = tempEvent;
//			}
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			count--;
//			if(count == 0)shutDown = true;
//		}
	}
	
	public void sendMessage(String address, Integer port, byte[] data) {
		this.server.sendMessage(address, port, data);
	}
	public void sendMessage(int session, byte[] data) {
		this.server.sendMessage(session, data);
	}
	
//	public void listenForConnection() {
//		this.action = 0;
//		this.run();
//	}
//	
//	private int connection;
//	private String connectName;
//	private String connectAddress;
//	private Integer connectPort;
//	public void connect(int connection, String connectName, String connectAddress, Integer connectPort) {
//		this.connection = connection;
//		this.connectName = connectName;
//		this.connectAddress = connectAddress;
//		this.connectPort = connectPort;
//		this.action = 1;
//		this.run();
//	}
//	
//	private final int listenForConnection = 0;
//	private final int tryToConnect = 1;
//	public void run() {
//		try {
//			switch(action) {
//				case listenForConnection:
//					if(debug2) {System.out.println("EF<"+this.name+"> Listening on Port "+this.portNumber);}
//					Socket incomingConnectionSocket = serverSocket.accept();
//					if(debug2) {System.out.println("EF<"+this.name+"> accepted a connection");}
//					break;
//				case tryToConnect:
//					if(debug2) {System.out.println("EF<"+this.name+"> trying to connect to "+this.connectName);}
//					this.tryToConnect(this.connection, this.connectName, this.connectAddress, this.connectPort);
//					if(debug) {System.out.println("EF<"+this.name+"> has connected to "+this.connectName);}
//					break;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public boolean tryToConnect(int connection, String connectName, String connectAddress, Integer connectPort) {
//		try {
//			Socket newSocket = new Socket(connectAddress, connectPort);
//			sockets[connection] = newSocket;
//			inputStreams[connection] = new DataInputStream(newSocket.getInputStream());
//			outputStreams[connection] = new DataOutputStream(newSocket.getOutputStream());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return debug;
//	}
	
	public void cleanup() {
		this.server.cleanup();
	}
}

