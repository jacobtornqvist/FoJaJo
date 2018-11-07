package Exceptions;

public class DatabaseConnectionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DatabaseConnectionException() {
		super("Kunde inte etablera kontakt med databasen");
	}
	
}
