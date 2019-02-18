package overlay.node;

import overlay.wireformats.*;

public class Registry implements Runnable{
	private boolean debug = true;
	private boolean debug2 = true;
	private boolean debug3 = false;
	private String name = "Reggy";
	private String myAddress;
	private int numberOfNodes;
	public EventFactory eventFactory = null;
	private Integer maxConnections;
	private Integer maxRounds;
	private Node[] nodeArray;
	private int nodesConnected = 0;
	
	public Registry(String myAddress, int numberOfNodes, int maxConnections, int maxRounds) {
		this.myAddress = myAddress;
		this.numberOfNodes = numberOfNodes;
		this.maxConnections = maxConnections;
		this.maxRounds = maxRounds;
		this.nodeArray = new Node[numberOfNodes];
		if(debug2) {System.out.println(this.name+": Establishing...");}
		this.eventFactory = new EventFactory(0, this.name, this.myAddress, this.myAddress, null, numberOfNodes);
	}
	
	public Integer getPortNumber() {return eventFactory.getPortNumber();}
	
	public void run(){
		Thread.currentThread().setName(this.name+"-REGNODE");
		this.eventFactory.registryAcceptConnections(numberOfNodes);
		for(int i=0;i<numberOfNodes;i++) {
			this.doEvent(this.eventFactory.getLastEvent());
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.buildOverlay();
		//this.listMessagingNodes();
		this.eventFactory.shutDown();
//		Thread factoryThread = new Thread(this.eventFactory);
//		factoryThread.start();
//		if(debug2) {System.out.println(this.name+": All Nodes accounted for! Adding them now...");}
		eventFactory.cleanup();
	}
	
	public void doEvent(Event nextEvent) {
		switch(nextEvent.getType()) {
			case 1:
				this.register(nextEvent.getString());
				break;
		}
	}
	
	/*
	 * As per 1.1 Registry: A.
	 * 	Allows messaging nodes to register themselves. This is performed when a messaging node starts
	 * 	up for the first time.
	 */
	public void register(String eventString) {
		String[] tokens = eventString.split(",");
		String address = tokens[0];
		int port = Integer.parseInt(tokens[1]);
		nodeArray[nodesConnected] = new Node(address, port, maxConnections);
		nodesConnected++;
	}
	
	/*
	 * As per 1.1 Registry: B.
	 * 	Allows messaging nodes to unregister themselves. This is performed when a messaging node
	 * 	leaves the overlay.
	 */
	public void deregister() {
		
	}
	
	/*
	 * As per 1.1 Registry: C.
	 * 	Enables the construction of the overlay by orchestrating connections that a messaging node 
	 * 	initiates with other messaging nodes in the system. Based on its knowledge of the messaging
	 * 	nodes (through function A) the registry informs messaging nodes about the other messaging
	 * 	nodes that they should connect to. 
	 */
	public void buildOverlay() {
		int repititions = this.maxConnections/2;
		for(int j=0;j<numberOfNodes;j++) {
			for(int i=0;i<repititions;i++) {
				int next = (i+j+1)%numberOfNodes;
				int weight = 5;
				if(debug3) {System.out.println("OVERLAY===>Connecting: ["+j+","+next+"]");}
				nodeArray[j].setConnection(nodeArray[next],weight);
				nodeArray[next].setConnection(nodeArray[j],weight);
			}
		}
		for(int i=0;i<numberOfNodes;i++) {
			Event msg = new Message_Nodes_List(nodeArray[i]);
			if(debug3) {System.out.println("OVERLAY===>Connecting: "+nodeArray[i].getAddress()+","+nodeArray[i].getPort());}
			eventFactory.sendMessage(i, msg.marshall());
		}
	}
	
	/*
	 * As per 1.1 Registry: D.
	 * 	Assign and publish weights to the links connecting any two messaging nodes in the overlay. The
	 * 	weights these links take will range from 1-10. 
	 */
	public void publishWeights() {
		
	}
	
	/*
	 * As per 3.1 Registry:
	 * 	This should result in information about the messaging nodes (hostname, and port-number) being
	 * 	listed. Information for each messaging node should be listed on a separate line.
	 */
	public void listMessagingNodes() {
		System.out.println("Listing Messaging Nodes...");
		for(int i=0;i<numberOfNodes;i++) {
			System.out.println("\tConnection "+i+": HostName='"+nodeArray[i].getAddress()+"' Port-Number=["+nodeArray[i].getPort()+"]");
		}
	}

	/*
	 * As per 3.1 Registry:
	 * 	This should list information about links comprising the overlay. Each link’s information should be on
	 * 	a separate line and include information about the nodes that it connects and the weight of that link. For
	 * 	example, carrot.cs.colostate.edu:2000 broccoli.cs.colostate.edu:5001 8, indicates that the
	 * 	link is between two messaging nodes (carrot.cs.colostate.edu:2000) and
	 * 	(broccoli.cs.colostate.edu:5001) with a link weight of 8.
	 */
	public void listWeights() {
		System.out.println("Listing Messaging Node Connection Weights...");
		for(int i=0;i<numberOfNodes;i++) {
			nodeArray[i].printConnections();
		}
	}

	/*
	 * As per 3.1 Registry:
	 * 	This should result in the registry setting up the overlay. It does so by sending messaging nodes
	 * 	messages containing information about the messaging nodes that it should connect to. The registry
	 * 	tracks the connection counts for each messaging node and will send the MESSAGING_NODES_LIST
	 * 	message (see Section 2.3) to every messaging node. A sample specification of this command is setupoverlay 4 that will result in the creation of an overlay where each messaging node is connected to
	 * 	exactly 4 other messaging nodes in the overlay. You should handle the error condition where the number
	 * 	of messaging nodes is less than the connection limit that is specified.
	 */
	public void setupOverLay() {
		
	}
	
	/*
	 * As per 3.1 Registry:
	 * 	This should result in a Link_Weights message being sent to all registered nodes in the overlay. This
	 * 	command is issued once after the setup-overlay command has been issued. This also allows all nodes
	 * 	in the system to be aware of not just all the nodes in the system, but also the complete set of links in
	 * 	the system.
	 */
	public void sendLinkWeights() {
		
	}
	
}
