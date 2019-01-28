package p1;

import java.util.ArrayList;

public class Node {
	private boolean db = !true;
	private int number;
	private String name;
	private int weight;
	//int bigNumber = 1000000000;
	int bigNumber = 100;
	private ArrayList<Integer> connections = new ArrayList<Integer>();
	private ArrayList<Integer> connectionsWeights = new ArrayList<Integer>();
	
	public Node(int number, String name, int weight){
		this.number = number;
		this.name = name;
		this.weight = weight;
	}
	
	public boolean isDebugOn() {return this.db;}
	public int getNumber() {return this.number;}
	public String getName() {return this.name+"["+this.number+"="+this.weight+"]";}
	public int getWeight() {return this.weight;}
	public String toString() {return this.name;}
	public ArrayList<Integer> getConnections() {return this.connections;}
	
	public boolean addConnection(int newConnectionNumber, int weight) {
		if(this.db && this.isConnectedTo(newConnectionNumber)) {
			System.out.println(this.name+" ["+this.number+"] already knows: "+newConnectionNumber);
			return false;
		}
		if(this.isConnectedTo(newConnectionNumber)) {
			return false;
		}
		if(this.db) {System.out.println("\t"+this.getName()+": now knows '"+newConnectionNumber+"'");}
		connections.add(newConnectionNumber);
		connectionsWeights.add(weight);
		return true;
	}
	
	public boolean removeConnection(int connectionNumber) {
		if(this.db && !connections.contains(connectionNumber)) {
			System.out.println(this.name+" ["+this.number+"] doesn't know: "+connectionNumber);
			return false;
		}
		if(!connections.contains(connectionNumber)) {
			return false;
		}
		if(this.db) {System.out.println("\t"+this.name+": Forgetting "+connectionNumber);}
		int position = connections.indexOf(connectionNumber);
		connections.remove(position);
		connectionsWeights.remove(position);
		return true;
	}
	
	public void clearConnections() {
		this.connections.clear();
		this.connectionsWeights.clear();
	}
		
	public boolean isConnectedTo(int value) {
		return connections.contains(value);
	}
	
	public int edgeWeight(int value) {
		if(connections.indexOf(value)==-1) {return this.bigNumber;}
		return connectionsWeights.get(connections.indexOf(value));
	}

}
