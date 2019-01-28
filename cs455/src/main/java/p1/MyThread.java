package p1;
/*
 * Note: somethead.start() invokes somethread.run() as new thread
 */
public class MyThread extends Thread {
		private String name;
		public MyThread(String name){
			this.name = name;
		}
		public void run() {
			System.out.println("My name is "+this.name);
		}
	}
