package Assignment1;

public class Node {
	private boolean db = false;
	private int nodeNumber;
	private int[] connections;
	public Node(int nodeNumber, int graphSize) {
		if(db) {System.out.println("Creating Node: "+nodeNumber);}
		this.nodeNumber = nodeNumber;
		this.connections = new int[graphSize];
		for(int i=0;i<graphSize;i++) {connections[i]--;}
	}
	public boolean isDebugOn() {return this.db;}
	public int getNumber() {return this.nodeNumber;}
	public int[] getConnections() {return this.connections;}
	public boolean addConnection(int newConnectionNumber, int color) {
		if(this.isConnectedTo(newConnectionNumber)!=-1) {
			return false;
		}
		this.connections[newConnectionNumber]=color;
		return true;
	}
	public int isConnectedTo(int value) {
		return connections[value];
	}
	public int getConnectionColor(int node) {
		return connections[node];
	}
	public void printConnections() {
		for(int i=0;i<6;i++) {System.out.print(connections[i]);}
		System.out.println();
	}
}

//public class Node {
//	private boolean db = !true;
//	private int number;
//	private String name;
//	private int weight;
//	//int bigNumber = 1000000000;
//	int bigNumber = 100;
//	private ArrayList<Integer> connections = new ArrayList<Integer>();
//	private ArrayList<Integer> connectionsWeights = new ArrayList<Integer>();
//	
//	public Node(int number){
//		this.number = number;
//	}
//	
//	public boolean isDebugOn() {return this.db;}
//	public int getNumber() {return this.number;}
//	public String getName() {return this.name+"["+this.number+"="+this.weight+"]";}
//	public int getWeight() {return this.weight;}
//	public String toString() {return this.name;}
//	public ArrayList<Integer> getConnections() {return this.connections;}
//	
//	public boolean addConnection(int newConnectionNumber, int weight) {
//		if(this.db && this.isConnectedTo(newConnectionNumber)) {
//			System.out.println(this.name+" ["+this.number+"] already knows: "+newConnectionNumber);
//			return false;
//		}
//		if(this.isConnectedTo(newConnectionNumber)) {
//			return false;
//		}
//		if(this.db) {System.out.println("\t"+this.getName()+": now knows '"+newConnectionNumber+"'");}
//		connections.add(newConnectionNumber);
//		connectionsWeights.add(weight);
//		return true;
//	}
//	
//	public boolean removeConnection(int connectionNumber) {
//		if(this.db && !connections.contains(connectionNumber)) {
//			System.out.println(this.name+" ["+this.number+"] doesn't know: "+connectionNumber);
//			return false;
//		}
//		if(!connections.contains(connectionNumber)) {
//			return false;
//		}
//		if(this.db) {System.out.println("\t"+this.name+": Forgetting "+connectionNumber);}
//		int position = connections.indexOf(connectionNumber);
//		connections.remove(position);
//		connectionsWeights.remove(position);
//		return true;
//	}
//	
//	public void clearConnections() {
//		this.connections.clear();
//		this.connectionsWeights.clear();
//	}
//		
//	public boolean isConnectedTo(int value) {
//		return connections.contains(value);
//	}
//	
//	public int edgeWeight(int value) {
//		if(connections.indexOf(value)==-1) {return this.bigNumber;}
//		return connectionsWeights.get(connections.indexOf(value));
//	}
//
//}
