package p1;
import java.io.*;
import java.net.*;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
public class Server extends Thread{
	static Integer OUR_PORT = 5001;
	
	public void run(){
		Integer NUM_POSSIBLE_CONNECTIONS = 1;
		ServerSocket ourServerSocket = null;
		System.out.println("Starting up Server...");
		
		try {
			ourServerSocket = new ServerSocket(OUR_PORT, NUM_POSSIBLE_CONNECTIONS);
		} catch(IOException e) {
			System.out.println("Clients::main::creating_the_sockets:: " + e);
		}
		try {
			//Block on accepting connections. Once it has received a connection it will return a socket for us to use
			Socket incomingConnectionSocket = ourServerSocket.accept();
			
			System.out.println("Server: We recieved a connection!");
			
			//We have yet to block again, so we can handle this connection however we would like to
			//For now, let's send a message and then wait for the response.
			DataInputStream inputStream = new DataInputStream(incomingConnectionSocket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(incomingConnectionSocket.getOutputStream());
			
			//Let's send a message to our new friend
			String myMsg = "Server: What class is this video for?";
			byte[] msgToClient = myMsg.getBytes();
			Integer msgToClientLength = msgToClient.length;
			
			//Our self-inflicted protocol says we need to send the length first
			outputStream.writeInt(msgToClientLength);
			//Then we can send the message
			outputStream.write(msgToClient, 0 ,msgToClientLength);
			
			//Now we wait for the response
			Integer msgLength = 0;
			//Try to read an Integer from our input stream. This will block if there is nothing.
			msgLength = inputStream.readInt();
			
			//If we get here that means there was an integer to
			//  read and we have gotten the length of the rest of the next message
			System.out.println("Server: Received a message of length of: " + msgLength);
			
			//Try to read the incoming message
			byte[] incomingMessage = new byte[msgLength];
			inputStream.readFully(incomingMessage, 0, msgLength);
			
			//You could have used .readByte[] incomingMessage), however this will read
			//  *potentially* incomingMessage.length byte, maybe less
			//  whereas .readfully(...) will read exactly msgLength number of bytes
			
			System.out.println("Server: Received Message: " + new String(incomingMessage,0));
			
			//Close Stream and Sockets
			inputStream.close();
			outputStream.close();
			incomingConnectionSocket.close();
			ourServerSocket.close();
			
		} catch (IOException e) {
			System.out.println("Server::main::accepting_connections: " + e);
		}
	}
}
