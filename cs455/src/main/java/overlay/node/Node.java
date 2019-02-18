package overlay.node;

public class Node{
	private String myAddress;
	private Integer myServerPort;
	private int maxConnections;
	private Node[] connections;
	private int[] connectionWeights;
	private int numberOfConnections;
	
	public Node(String myAddress, Integer myServerPort, int maxConnections) {
		this.myAddress = myAddress;
		this.myServerPort = myServerPort;
		this.maxConnections = maxConnections;
		this.connections = new Node[maxConnections];
		this.connectionWeights = new int[maxConnections];
		this.numberOfConnections = 0;
	}
	
	public String getAddress() {return this.myAddress;}
	public Integer getPort() {return this.myServerPort;}
	public Node[] getConnectionsArray() {return this.connections;}
	public Node getConnection(int index) {return this.connections[index];}
	public void setConnection(Node newConnection, int weight) {
		this.connections[numberOfConnections]=newConnection;
		this.connectionWeights[numberOfConnections]=weight;
		this.numberOfConnections++;
	}
	
	public void printConnections() {
//		for(int i=0;i<this.maxConnections;i++) {
//			System.out.println("Node: <"+myServerPort+">"+connections[i].getAddress()+">,<"+connections[i].getPort()+">");
//		}
	}

	public String getHostName() {
		return this.getAddress();
	}

	public int getConnectionWeight(int i) {
		return connectionWeights[i];
	}
}
