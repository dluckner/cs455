package p1;

public class Main {
	public static void main(String args[]) {

		Thread myServer = new Server();
		Thread myClient = new Client();
		myServer.start();
		myClient.start();
		
		/*
		 * Test Register
		 */
//		String swordfish = "swordFish";
//		String shark = "shark";
//		String tuna = "tuna";
//		String cod = "cod";
//		String blowfish = "blowfish";
//		
//		Registry reg = new Registry(swordfish, 10);
//		reg.register(tuna, 11);
//		reg.register(shark, 12);
//		reg.register(cod, 13);
//		reg.register(blowfish, 14);
//		reg.connect(tuna, shark, 20);
//		reg.connect(tuna, cod, 30);
//		reg.connect(tuna, blowfish, 40);
//		reg.connect(shark,cod, 50);
//		reg.connect(shark, blowfish, 60);
//		reg.connect(cod, blowfish, 70);
//		//reg.unregister(tuna);
//		//reg.unregister(blowfish);
//		
//		reg.showDetails(cod);
//		reg.showAllConnections();
//		reg.showEdgeWeights();
		System.out.println("~~~DONE~~~");
	}
}
