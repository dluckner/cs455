package p1;

public class Dumbtest implements Runnable{
	String name;
	int sleepTime;
	public Dumbtest(String name, int sleepTime) {
		this.name = name;
		this.sleepTime = sleepTime;
	}
	public void run() {
		System.out.println("Running: "+this.name+"...");
		try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.name+" has runned!");
	}
}
