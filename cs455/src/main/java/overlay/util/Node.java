package overlay.util;

import overlay.wireformats.EventFactory;

public class Node implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private String name;
	private String registryAddress;
	private Integer registryPort;
	public EventFactory eventFactory = null;
	Integer maxConnections;
	Integer maxRounds;
	
	public Node(String name, String registryAddress, Integer registryPort, Integer maxConnections, Integer maxRounds) {
		this.name = name;
		this.registryAddress = registryAddress;
		this.registryPort = registryPort;
		this.maxConnections = maxConnections;
		this.maxRounds = maxRounds;
		if(debug2) {System.out.println(this.name+": Establishing...");}
		eventFactory = new EventFactory(1, this.name, maxConnections);
	}
	
	public void run(){
		if(debug2) {System.out.println(this.name+": Up and Running!");}
		eventFactory.connect(0, "Register", registryAddress, registryPort);
//		for(int i=0;i<maxRounds;i++) {
//			
//		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eventFactory.close();
	}
}
