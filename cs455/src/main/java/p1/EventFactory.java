package p1;

import java.io.*;
import java.net.*;
import overlay.wireformats.*;

public class EventFactory implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private int type; // 0=>Registry, 1=>Messaging Node
	private String name;
	private Integer maxConnections;
	private Integer portNumber;
	ServerSocket serverSocket = null;
	Socket[] sockets = null;
	DataInputStream[] inputStreams = null;
	DataOutputStream[] outputStreams = null;
	private Event nextEvent = null;
	private int action;
	
	public EventFactory(int type, String name, Integer maxConnections) {
		this.type = type; // 0=>Registry, 1=>Messaging Node
		this.name = name;
		this.maxConnections = maxConnections;
		sockets = new Socket[maxConnections];
		inputStreams = new DataInputStream[maxConnections];
		outputStreams = new DataOutputStream[maxConnections];
		this.establishServerSocket();
		if(debug) {System.out.println("EF<"+this.name+"> is using port "+this.portNumber);}
	}
	
	public Integer getPortNumber() {return this.portNumber;}
	public Event getEvent() {return this.nextEvent;}
	
	private void establishServerSocket() {
		//for(int i=1025;i<=49151;i++) {
		for(int i=5000;i<=5100;i++) {
			try {
				serverSocket = new ServerSocket(i, this.maxConnections);
				this.portNumber = i;
				if(debug2) {System.out.println("EF<"+this.name+"> opened port number "+i);}
				break;
			} catch(IOException e) {
				if(debug2) {System.out.println("EF<"+this.name+"> could not use port = " + i);};
			}
		}
	}
	
	public void listenForConnection() {
		this.action = 0;
		this.run();
	}
	
	private int connection;
	private String connectName;
	private String connectAddress;
	private Integer connectPort;
	public void connect(int connection, String connectName, String connectAddress, Integer connectPort) {
		this.connection = connection;
		this.connectName = connectName;
		this.connectAddress = connectAddress;
		this.connectPort = connectPort;
		this.action = 1;
		this.run();
	}
	
	private final int listenForConnection = 0;
	private final int tryToConnect = 1;
	public void run() {
		try {
			switch(action) {
				case listenForConnection:
					if(debug2) {System.out.println("EF<"+this.name+"> Listening on Port "+this.portNumber);}
					Socket incomingConnectionSocket = serverSocket.accept();
					if(debug2) {System.out.println("EF<"+this.name+"> accepted a connection");}
					break;
				case tryToConnect:
					if(debug2) {System.out.println("EF<"+this.name+"> trying to connect to "+this.connectName);}
					this.tryToConnect(this.connection, this.connectName, this.connectAddress, this.connectPort);
					if(debug) {System.out.println("EF<"+this.name+"> has connected to "+this.connectName);}
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean tryToConnect(int connection, String connectName, String connectAddress, Integer connectPort) {
		try {
			Socket newSocket = new Socket(connectAddress, connectPort);
			sockets[connection] = newSocket;
			inputStreams[connection] = new DataInputStream(newSocket.getInputStream());
			outputStreams[connection] = new DataOutputStream(newSocket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return debug;
	}
	
	public void close() {
		if(debug) {System.out.println("EF<"+this.name+"> is closing...");}
		try {
		this.serverSocket.close();
		for(int i=0;i<1;i++) {
				if(this.type==1&&!sockets[i].equals(null)) {sockets[i].close();}
				if(this.type==1&&!inputStreams[i].equals(null)) {inputStreams[i].close();}
				if(this.type==1&&!outputStreams[i].equals(null)) {outputStreams[i].close();}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

