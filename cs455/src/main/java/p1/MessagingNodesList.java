package p1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingNodesList {
	private boolean debug = true;
	private boolean debug2 = true;
	private int type; // 0=>Registry, 1=>Messaging Node
	private String name;
	private Integer maxConnections;
	static Integer port;
	ServerSocket[] serverSockets = null;
	Socket[] sockets = null;
	DataInputStream[] inputStreams = null;
	DataOutputStream[] outputStreams = null;
	
	public MessagingNodesList(int type, String name, Integer maxConnections) {
		this.type = type; // 0=>Registry, 1=>Messaging Node
		this.name = name;
		this.maxConnections = maxConnections;
		sockets = new Socket[maxConnections];
		serverSockets = new ServerSocket[maxConnections];
		inputStreams = new DataInputStream[maxConnections];
		outputStreams = new DataOutputStream[maxConnections];
		if(type==0) {this.establishServerSocketArray();}
	}
	
	private void establishServerSocketArray() {
		if(debug) {System.out.println(this.name+": Obtaining Sockets");}
		for(int i=0;i<this.maxConnections;i++) {
			this.establishPortNumber(i);
		}
	}
	
	private void establishPortNumber(int index) {
		//for(int i=1025;i<=49151;i++) {
		for(int i=5000;i<=5020;i++) {
			try {
				serverSockets[index] = new ServerSocket(i, this.maxConnections);
				if(debug2) {System.out.println(this.name+": opened port number "+i);}
				break;
			} catch(IOException e) {
				if(debug2) {System.out.println(this.name+": could not use port = " + i);};
			}
		}
	}
}
