package p1;

public interface Message {
	public final int Register_Request = 0;
	public final int Register_Response = 1;
	abstract int getType();
}
