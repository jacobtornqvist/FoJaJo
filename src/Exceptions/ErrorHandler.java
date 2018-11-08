package Exceptions;

import model.BankAccount;
import model.Customer;

public class ErrorHandler {
	public static String handleException(Exception e) {
		if(e instanceof DatabaseConnectionException) {
			return "Kan inte etablera kontakt med databasen";
		}
		else if(e instanceof EntityAlreadyExistsException) {
			if(((EntityAlreadyExistsException) e).getO() instanceof Customer) {
				return "Anv�ndare: "+ ((Customer) ((EntityAlreadyExistsException) e).getO()).getUsername() + " finns redan"; 
			}
			else if(((EntityAlreadyExistsException) e).getO() instanceof BankAccount) {
				return "Kontonummer: "+ ((BankAccount) ((EntityAlreadyExistsException) e).getO()).getAccountNbr() +" finns redan";
			}
		}
		else if(e instanceof LoginException) {
			return "Anv�ndarnamn eller l�senord �r felaktikt f�r : " + ((Customer) ((LoginException) e).getO()).getUsername(); 
		}
		else if(e instanceof DeleteAccountException) {
			return "Kan inte ta bort konto. Finns pengar kvar p� konoto: " + ((DeleteAccountException) e).getO().toString();
		}
		else if(e instanceof EntityNotFoundException) {
			return "Entity not found Exception";
		}
		else if(e instanceof EntityValidationException) {
			return "Entity validation Exception";
		}
		else if(e instanceof BalanceTooLowException) {
			return "Det finns inte tillr�ckligt mycket pengar p� kontot.";
		}
		return null;
	}

}
