package Exceptions;

public class EntityAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	private Object o;
	public EntityAlreadyExistsException() {
		
	}
	public EntityAlreadyExistsException(Object o) {
		this.o = o;
	}
	
	public Object getObject() {
		return o;
	}

}
