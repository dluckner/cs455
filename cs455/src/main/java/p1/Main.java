package p1;

public class Main {
	public static void main(String args[]) {
		String ipAddress = "192.168.0.105";
		String[] machineList = {"Apples","Butter","Candy","Danish","EggPlant","Fritters","Gravy","Ham","Icecream","Jelly","Kiwi"};
		//String[] machineList = {"Apples","Butter"};
		Integer maxRounds = 10;
		Runnable registry = new Registry(machineList.length, maxRounds);
		Integer registryPort = ((Registry) registry).getPortNumber();
		System.out.println("Register at Address: <"+ipAddress+"> and Port: ["+registryPort+"]");
		Thread registryThread = new Thread(registry);
		registryThread.start();
		Thread[] nodes = new Thread[machineList.length];
		for(int i=0;i<machineList.length;i++) {
			Runnable nextNode = new Node(machineList[i], ipAddress, registryPort, machineList.length, maxRounds);
			nodes[i] = new Thread(nextNode);
			nodes[i].start();
		}
	}
}
