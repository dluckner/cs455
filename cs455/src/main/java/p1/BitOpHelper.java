package p1;

public class BitOpHelper {
	public static String byteArraytoString(byte[] byteArray) {
		String output = "";
		for(int i=0;i<byteArray.length;i++) {
			output += (char)byteArray[i];
		}
		return output;
	}
}
