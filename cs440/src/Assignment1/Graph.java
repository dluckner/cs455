package Assignment1;

import java.util.ArrayList;

public class Graph{
	private boolean db = false;
	private int graphSize;
	private ArrayList<Node> Nodes = new ArrayList<Node>();
	public Graph(int graphSize){
		this.graphSize = graphSize;
		for(int i=0;i<graphSize;i++) {
			Nodes.add(new Node(i, graphSize));
			this.connect(i, i, -2);
		}
	}
	public int getGraphSize() {return this.graphSize;}
	public void connect(int node1, int node2, int color) {
		Nodes.get(node1).addConnection(node2, color);
		Nodes.get(node2).addConnection(node1, color);
	}
	public void connect(int[] nodes, int color) {
		this.connect(nodes[0], nodes[1], color);
	}
	public void showConnections() {
		String output = "";
		for(int j=0;j<this.graphSize;j++) {
			output += "[";
			for(int i=0;i<this.graphSize;i++) {
				output += "\t"+Nodes.get(j).isConnectedTo(i);
			}
			output+="\t]\n";
		}
		System.out.println(output);
	}
	public boolean areNodesConnected(int node1, int node2) {
		if(Nodes.get(node1).isConnectedTo(node2)==-1) {return false;}
		return true;
	}
	public boolean isNodeOccupying(int color, int node1, int node2) {
		if(getEdgeOwner(node1, node2)==color) {
			return true;
		}
		return false;
	}
	public int getEdgeOwner(int node1, int node2) {
		return Nodes.get(node1).getConnectionColor(node2);
	}
}

//public class Graph {
//	private boolean db = false;
//	int index = 0;
//	int weight;
//	int graphSize;
//	//int bigNumber = 1000000000;
//	int bigNumber = 100;
//	private ArrayList<Node> Nodes = new ArrayList<Node>();
//	public Graph(int graphSize) {
//		this.graphSize = graphSize;
//		for(int i=0;i<graphSize;i++) {
//			this.register(new Node(i));
//		}
//	}
//	
//	public boolean isDebugOn() {return this.db;}
//	public int getIndex() {return this.index;}
//	public int getOverlaySize() {return this.Nodes.size();}
//	
//	public void showAllNodes() {
//		System.out.println("Registered Nodes...");
//		for(int i=0;i<Nodes.size();i++) {
//			System.out.println("\t" + Nodes.get(i) + " => '" + Nodes.get(i).getName()+ "'");
//		}
//	}
//	
//	public void showAllConnections() {
//		String output = "Connections...\n";
//		for(int i=0;i<Nodes.size();i++) {
//			output += "\t" + Nodes.get(i).getName() + ": {";
//			ArrayList<Integer> currentConnectionsList = Nodes.get(i).getConnections();
//			for(int j=0;j<Nodes.size();j++) {
//				if(Nodes.get(i).isConnectedTo(j)) {
//					String nextConnection = Nodes.get(j).getName();
//					output += nextConnection + ", ";
//				}
//			}
//			if(currentConnectionsList.size()>0) {output=output.substring(0,output.length()-2);}
//			output += "}\n";
//		}
//		System.out.print(output);
//	}
//	
//	public void showDetails(String myNode) {
//		int position = this.Nodes.lastIndexOf(myNode);
//		if (position ==-1) {System.out.print("Graph does not contain '"+myNode+"'");return;}
//		ArrayList<Integer> friends = Nodes.get(position).getConnections();
//		System.out.println("Showing: "+Nodes.get(position).getName()+"="+friends.size()+"...");
//		for(int i=0;i<index;i++) {
//			if(Nodes.get(position).isConnectedTo(i)) {System.out.println("\t"+Nodes.get(i).getName());}
//		}
//	}
//	
//	public void showEdgeWeights() {
//		int[][] edgeMatrix = this.getEdgeMatrix();
//		int length = edgeMatrix.length;
//		String output = "Edge Matrix...\n";
//		for(int j=0;j<length;j++) {
//			output += "\t|";
//			for(int i=0;i<length;i++) {
//				output += edgeMatrix[j][i] + "\t";
//			}
//			output += "|\n";
//		}
//		System.out.print(output);
//	}
//	
//	public int[][] getEdgeMatrix(){
//		int length = Nodes.size();
//		int[][] edgeMatrix = new int[length][length];
//		for(int j=0;j<length;j++) {
//			for(int i=0;i<length;i++) {
//				edgeMatrix[j][i] = Nodes.get(i).edgeWeight(j);
//			}
//			edgeMatrix[j][0] = this.bigNumber;
//			edgeMatrix[0][j] = this.bigNumber;
//		}
//		return edgeMatrix;
//	}
//	
//	public void register(int nodeNumber) {
//		if(this.db) {System.out.println("Adding '"+nodeNumber+"'");}
//		if(!NodeNames.contains(nodeNumber)) {
//			Node buildNode = new Node(nodeNumber);
//			Nodes.add(buildNode);
//			NodeNames.add(""+nodeNumber);
//			this.index++;
//		}
//	}
//	
//	public void unregister(String deleteNodeName) {
//		if(this.db) {System.out.println("Removing '"+deleteNodeName+"'");}
//		int position = NodeNames.indexOf(deleteNodeName);
//		if(position>=0) {
//			for(int i=0;i<Nodes.size();i++) {
//				Nodes.get(i).removeConnection(position);
//			}
//			Nodes.get(position).clearConnections();
//			//Nodes.remove(position);
//			//NodeNames.remove(position);
//			//NodeValues.remove(position);
//		}
//	}
//	
//	public void connect(String host, String newFriend, int weight) {
//		int positionHost = NodeNames.indexOf(host);
//		int positionNewFriend = NodeNames.indexOf(newFriend);
//		if(positionHost == 0 && positionNewFriend == 0) {Nodes.get(positionHost).addConnection(positionNewFriend, weight);return;}
//		Nodes.get(positionHost).addConnection(positionNewFriend, weight);
//		Nodes.get(positionNewFriend).addConnection(positionHost, weight);
//	}
//}