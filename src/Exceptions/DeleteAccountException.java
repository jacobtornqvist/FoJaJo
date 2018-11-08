package Exceptions;

public class DeleteAccountException extends Exception {

	private static final long serialVersionUID = 1L;
	private Object o;
	public DeleteAccountException(Object o){
		setO(o);
	}
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	

}
