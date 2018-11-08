package Exceptions;

public class ErrorHandler {
	public static String handleException(Exception e) {
		if(e instanceof DatabaseConnectionException) {
			
		}
		else if(e instanceof DatabaseConcurrencyException) {
			return "Could not connect to database";
		}
		else if(e instanceof EntityAlreadyExistsException) {
			
		}
		else if(e instanceof EntityNotFoundException) {
			
		}
		else if(e instanceof EntityValidationException) {
			
		}
		else if(e instanceof BalanceTooLowException) {
			
		}
		return null;
	}

}
