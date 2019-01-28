package p1;

import java.util.ArrayList;
import java.util.Collections;

public class Node {
	private boolean db = !true;
	private int number;
	private String name;
	private ArrayList<Integer> connections = new ArrayList<Integer>();
	
	public Node(int number, String name){
		this.number = number;
		this.name = name;
	}
	
	public boolean isDebugOn() {return this.db;}
	public int getNumber() {return this.number;}
	public String getName() {return this.name+"["+this.number+"]";}
	public String toString() {return this.name;}
	public ArrayList<Integer> getConnections() {return this.connections;}
	
	public boolean addConnection(int newConnectionNumber) {
		if(this.db && this.isConnectedTo(newConnectionNumber)) {
			System.out.println(this.name+" ["+this.number+"] already knows: "+newConnectionNumber);
			return false;
		}
		if(this.isConnectedTo(newConnectionNumber)) {
			return false;
		}
		if(this.db) {System.out.println("\t"+this.getName()+": now knows '"+newConnectionNumber+"'");}
		connections.add(newConnectionNumber);
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
		connections.remove(connections.indexOf(connectionNumber));
		return true;
	}
	
	public void clearConnections() {
		this.connections.clear();
	}
	
	public void sortConnections() {
		Collections.sort(this.connections);
	}
	
	public boolean isConnectedTo(int value) {
		return connections.contains(value);
	}

}
