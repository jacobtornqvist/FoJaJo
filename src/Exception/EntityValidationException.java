package Exception;

public class EntityValidationException extends Exception{

	private static final long serialVersionUID = 1L;

	public EntityValidationException(Object o) {
		super("Kunde inte hitta: " + o.toString().toUpperCase());
	}

}
