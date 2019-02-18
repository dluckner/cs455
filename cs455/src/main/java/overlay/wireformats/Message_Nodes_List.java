package overlay.wireformats;
import overlay.node.*;
public class Message_Nodes_List extends Event{

	public Message_Nodes_List(Integer type, byte[] bytes) {
		super(type, bytes);
	}
	
	public Message_Nodes_List(Node messageNode) {
		super(3, getBytes(messageNode));
	}
	
	private static byte[] getBytes(Node messageNode) {
		Node[] connectionList = messageNode.getConnectionsArray();
		String output = ""+connectionList.length;
		for(int i=0;i<connectionList.length;i++) {
			output+=","+connectionList[i].getAddress()+","+connectionList[i].getPort();
		}
		return output.getBytes();
	}
}
