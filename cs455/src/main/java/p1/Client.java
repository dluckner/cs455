package p1;
import java.io.*;
import java.net.*;

public class Client {
	static String SERVER_ADDRESS = "swordfish.cs.colostate.edu";
	static Integer PORT = 5001;
	
	public static void main(String[] args) throws IOException{
		Socket socketToTheServer = null;
		DataInputStream inputStream = null;
		DataOutputStream outputStream = null;
		
		try {
			socketToTheServer = new Socket(SERVER_ADDRESS, PORT);
			inputStream = new DataInputStream(socketToTheServer.getInputStream());
			outputStream = new DataOutputStream(socketToTheServer.getOutputStream());
		} catch(IOException e) {
			System.out.println("Client::main::creating_the_sockets: " + e);
		}
		
		System.out.println("Connected to the server!");
		
		try {
			Integer msgLength = 0;
			//Try to read an integer from our input stream. This will block if there is nothing.
			msgLength = inputStream.readInt();
			
			//If we got here that means there was an integer to read and we have the
			//  length of the rest of the message
			System.out.println("Received Message length of: " + msgLength);
			
			//Try to read the incoming message.
			byte[] incomingMessage = new byte[msgLength];
			inputStream.readFully(incomingMessage, 0, msgLength);
			
			//You could have used .readByte[] incomingMessage), however this will read
			//  *potentially* incomingMessage.length byte, maybe less
			//  whereas .readfully(...) will read exactly msgLength number of bytes
			
			System.out.println("Received Message: " + new String(incomingMessage, 0));
			
			//Now, let's respond
			String mym = "CS455";
			byte[] msgToServer = mym.getBytes();
			Integer msgToServerLength = msgToServer.length;
			//Our self-inflicted protocol says that we send the length first
			outputStream.writeInt(msgToServer.length);
			//Then we can send the message
			outputStream.write(msgToServer, 0, msgToServerLength);
			
			//Close streams and the sockets
			inputStream.close();
			outputStream.close();
			socketToTheServer.close();
		} catch(IOException e) {
			System.out.println("CLIENT::main::talking_to_the_server:: " + e);
		}
	}
}