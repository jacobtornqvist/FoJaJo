package Exceptions;

public class LoginException extends Exception{

	private static final long serialVersionUID = 1L;
	private Object o;
	public LoginException(Object o) {
		setO(o);
	}
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	

}
