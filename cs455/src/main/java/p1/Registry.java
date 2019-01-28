package p1;

import java.util.ArrayList;

public class Registry {
	private boolean db = false;
	String name;
	int index = 0;
	private ArrayList<Node> Nodes = new ArrayList<Node>();
	private ArrayList<String> NodeNames = new ArrayList<String>();
	private ArrayList<Integer> NodeValues = new ArrayList<Integer>();
	public Registry(String name) {
		this.name = name;
		this.register(this.name);
	}
	
	public boolean isDebugOn() {return this.db;}
	public String getName() {return this.name;}
	public int getIndex() {return this.index;}
	public int getOverlaySize() {return this.Nodes.size();}
	
	public void showAllNodes() {
		System.out.println(this.name+": Registered Nodes...");
		for(int i=0;i<Nodes.size();i++) {
			System.out.println("\t" + NodeNames.get(i) + " => '" + Nodes.get(i).getName()+ "'");
		}
	}
	
	public void showAllConnections() {
		String output = this.name+": Connections...\n";
		for(int i=0;i<Nodes.size();i++) {
			output += "\t" + Nodes.get(i).getName() + ": {";
			ArrayList<Integer> currentConnectionsList = Nodes.get(i).getConnections();
			for(int j=0;j<Nodes.size();j++) {
				if(Nodes.get(i).isConnectedTo(j)) {
					String nextConnection = Nodes.get(j).getName();
					output += nextConnection + ", ";
				}
			}
			if(currentConnectionsList.size()>0) {output=output.substring(0,output.length()-2);}
			output += "}\n";
		}
		System.out.print(output);
	}
	
	public void showDetails(String myNode) {
		int position = this.NodeNames.lastIndexOf(myNode);
		if (position ==-1) {System.out.print(this.name+": Does not contain '"+myNode+"'");return;}
		ArrayList<Integer> friends = Nodes.get(position).getConnections();
		System.out.println("Showing: "+Nodes.get(position).getName()+"="+friends.size()+"...");
		for(int i=0;i<index;i++) {
			if(Nodes.get(position).isConnectedTo(i)) {System.out.println("\t"+Nodes.get(i).getName());}
		}
	}
	
	public void register(String newNodeName) {
		if(this.db) {System.out.println(this.name+": adding '"+newNodeName+"'");}
		if(!NodeNames.contains(newNodeName)) {
			Node buildNode = new Node(this.index, newNodeName);
			Nodes.add(buildNode);
			NodeNames.add(newNodeName);
			NodeValues.add(index);
			this.connect(this.name, newNodeName);
			this.index++;
		}
		else {
			this.connect(this.name, newNodeName);
		}
	}
	
	public void unregister(String deleteNodeName) {
		if(this.db) {System.out.println(this.name+": removing '"+deleteNodeName+"'");}
		int position = NodeNames.indexOf(deleteNodeName);
		//System.out.println("POS: "+position);
		if(position>=0) {
			for(int i=0;i<Nodes.size();i++) {
				Nodes.get(i).removeConnection(position);
			}
			Nodes.get(position).clearConnections();
			//Nodes.remove(position);
			//NodeNames.remove(position);
			//NodeValues.remove(position);
		}
	}
	
	public void connect(String host, String newFriend) {
		if(this.db && !host.equals(this.name)) {System.out.println(this.name+": connecting '"+host+"' to '"+newFriend+"'");}
		int positionHost = NodeNames.indexOf(host);
		int positionNewFriend = NodeNames.indexOf(newFriend);
		//if(this.db && !host.equals(this.name)) {System.out.println(this.name+": connecting '"+positionHost+"' to '"+positionNewFriend+"'");}
		if(positionHost == 0 && positionNewFriend == 0) {Nodes.get(positionHost).addConnection(positionNewFriend);return;}
		if(this.db && positionHost==-1) {System.out.println("\t'"+host+"' does not exist in "+this.name);return;}
		if(this.db && positionNewFriend==-1) {System.out.println("\t'"+host+"' does not exist in "+this.name);return;}
		Nodes.get(positionHost).addConnection(positionNewFriend);
		Nodes.get(positionNewFriend).addConnection(positionHost);
	}
}