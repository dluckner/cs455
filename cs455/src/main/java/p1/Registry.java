package p1;

public class Registry implements Runnable{
	private boolean debug = true;
	private boolean debug2 = false;
	private String name = "Reggy";
	public EventFactory eventFactory = null;
	Integer maxConnections;
	Integer maxRounds;
	
	
	public Registry(int maxConnections, int maxRounds) {
		this.maxConnections = maxConnections;
		this.maxRounds = maxRounds;
		if(debug2) {System.out.println(this.name+": Establishing...");}
		eventFactory = new EventFactory(0, this.name, maxConnections);
	}
	
	public Integer getPortNumber() {return eventFactory.getPortNumber();}
	
	public void run(){
		if(debug2) {System.out.println(this.name+": Up and Running!");}
		for(int i=0;i<maxRounds;i++) {
			for(int j=0;j<maxConnections;j++) {
				eventFactory.listenForConnection();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eventFactory.close();
	}
	

}
