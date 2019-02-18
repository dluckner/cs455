package overlay.wireformats;

public class BitOpHelper {
	private static boolean debug = false;
	public static String byteArraytoString(byte[] byteArray) {
		String output = "";
		for(int i=0;i<byteArray.length;i++) {
			output += (char)byteArray[i];
		}
		if(debug) {System.out.println("BAtoS: <"+output+">");}
		return output;
	}
	public static byte[] StringToByteArray(String myString) {
		return myString.getBytes();
	}
	public static byte[] justTheData(byte[] byteArray) {
		byte[] data = new byte[byteArray.length-1];
		for(int i=0;i<data.length;i++) {
			data[i] = byteArray[i+1];
		}
		return data;
	}
}
