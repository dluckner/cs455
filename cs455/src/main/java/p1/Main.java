package p1;

public class Main {
	public static void main(String args[]) {
		String swordfish = "swordFish";
		String shark = "shark";
		String tuna = "tuna";
		String cod = "cod";
		String blowfish = "blowfish";
		
		Registry reg = new Registry(swordfish);
		
		reg.register(tuna);
		reg.register(shark);
		reg.register(cod);
		reg.register(blowfish);
		reg.connect(tuna, shark);
		reg.connect(tuna, cod);
		reg.connect(tuna, blowfish);
		reg.connect(shark,cod);
		reg.connect(shark, blowfish);
		reg.connect(cod, blowfish);
		reg.unregister(tuna);
		reg.unregister(blowfish);
		
		reg.showDetails(cod);
		reg.showAllConnections();
		System.out.println("~~~DONE~~~");
	}
}