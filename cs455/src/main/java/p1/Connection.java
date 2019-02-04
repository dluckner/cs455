package p1;

import java.io.*;
import java.net.*;

public class Connection implements Runnable{
	int type;
	int server = 0;
	ServerSocket serverSocket = null;
	Socket sockets = null;
	DataInputStream inputStreams = null;
	DataOutputStream outputStreams = null;
	
	public Connection(int type, String address, Integer port, Integer maxConnections) {
		this.type = type;
		if(type == 0) {
			try {
				serverSocket = new ServerSocket(port, maxConnections);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(type == 1) {
			
		}
	}
	
	public void run() {
		if(this.type == server) {server();}
	}
	
	private void server() {
		try {
			Socket incomingConnectionSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Server::main::accepting_connections: " + e);
		}
	}
}
