package p1;

import overlay.*;
import overlay.node.*;

public class Main {
	public static void main(String args[]) {
//		testRegister tr = new testRegister();
//		testNode tn = new testNode();
//		Thread trt = new Thread(tr);
//		Thread tnt = new Thread(tn);
//		trt.start();
//		tnt.start();
		String ipAddress = "192.168.0.105";
		String[] machineList = {"Apples","Butter","Candy","Danish","EggPlant","Fritters","Gravy","Ham","Icecream","Jelly"};
		//String[] machineList = {"Apples","Butter"};
		Integer maxRounds = 10;
		Runnable registry = new Registry(ipAddress, machineList.length, 4, maxRounds);
		Integer registryPort = ((Registry) registry).getPortNumber();
		System.out.println("Register at Address: <"+ipAddress+"> and Port: ["+registryPort+"]");
		Thread registryThread = new Thread(registry);
		registryThread.start();
		Thread[] nodes = new Thread[machineList.length];
		for(int i=0;i<machineList.length;i++) {
			Runnable nextNode = new MessagingNode(machineList[i], ipAddress, ipAddress, registryPort, machineList.length, maxRounds);
			nodes[i] = new Thread(nextNode);
			nodes[i].start();
		}
	}
}
