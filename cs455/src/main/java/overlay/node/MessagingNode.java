package overlay.node;

import overlay.wireformats.*;

public class MessagingNode implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private String name;
	private String myAddress;
	private String registryAddress;
	private Integer registryPort;
	public EventFactory eventFactory = null;
	Integer maxConnections;
	Integer maxRounds;
	private Node[] nodeArray;
	
	public MessagingNode(String name, String myAddress, String registryAddress, Integer registryPort, Integer maxConnections, Integer maxRounds) {
		this.name = name;
		this.myAddress = myAddress;
		this.registryAddress = registryAddress;
		this.registryPort = registryPort;
		this.maxConnections = maxConnections;
		this.maxRounds = maxRounds;
		this.nodeArray = new Node[maxConnections];
		if(debug2) {System.out.println(this.name+": Establishing with register at:"+registryAddress+", Port: "+registryPort);}
		eventFactory = new EventFactory(1, this.name, this.myAddress, this.registryAddress, this.registryPort, maxConnections);
	}
	
	public void run(){
		//if(debug) {System.out.println(this.name+": Up and Running for:"+maxRounds+" rounds!");}
		Thread.currentThread().setName(this.name+"-MSGNODE");
		Thread factoryThread = new Thread(this.eventFactory);
		factoryThread.run();
		//sthis.nodeArray = factoryThread.getNodeArray();
//		eventFactory.connect(0, "Register", registryAddress, registryPort);
		try {
//			for(int i=0;i<1;i++) {
//			//for(int i=0;i<maxRounds;i++) {
//				if(debug) {System.out.println(this.name+" --- Round "+i+"...");}
//				Thread.sleep(1000);
//				Event nextEvent = this.eventFactory.getLastEvent();
//				if(nextEvent!=null) {
//					if(debug) {System.out.println(this.name+" retreived event...:"+nextEvent.getString());}
//				}
//			}
			Thread.sleep(10000);
			this.eventFactory.shutDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		eventFactory.cleanup();
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	Upon starting up, each messaging node should register its IP address, and port number with the registry.
	 * 	It should be possible for your system to register messaging nodes that are running on the same host
	 * 	but are listening to communications on different ports. 
	 */
	public void register() {
		
	}
	
	/*
	 * As per 2.2 from Interactions between the components
	 * 	When a messaging node exits it should deregister itself. It does so by sending a message to the registry. 
	 */
	public void deregister() {
		
	}
	
	/*
	 * As per 2.3 from Interactions between the components
	 * 
	 */
	public void overlay() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void publishWeights() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void initiate() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void sendMessage() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void verifyTaskCompletion() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	 
	 */
	public void trafficSummaries() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void printShortestPath() {
		
	}
	
	/*
	 * As per 2.1 from Interactions between the components
	 * 	
	 */
	public void exitOverlay() {
		
	}
}