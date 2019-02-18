package overlay.wireformats;

import overlay.node.*;

public class Message_Link_Weights extends Event{

	public Message_Link_Weights(Integer type, byte[] bytes) {
		super(4, bytes);
	}
	
	public Message_Link_Weights(Node messageNode) {
		super(4, getBytes(messageNode));
	}

	private static byte[] getBytes(Node messageNode) {
		String output = ""+messageNode.getConnectionsArray().length;
		for(int i=0;i<messageNode.getConnectionsArray().length;i++) {
			output+=","+toLinkInfoFormat((Node)messageNode, (Node)messageNode.getConnection(i), messageNode.getConnectionWeight(i));
		}
		return output.getBytes();
	}

	private static String toLinkInfoFormat(Node a, Node b, int weight) {
		return a.getHostName()+":"+a.getPort()+" "+b.getHostName()+":"+b.getPort()+" "+weight;
	}
	
}
