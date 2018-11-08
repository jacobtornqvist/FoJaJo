package dal;

import java.sql.SQLException;


import Exceptions.*;

public class ExceptionHandler {

	public static Exception handleException(Exception e, Object o) {
		if(e instanceof SQLException) {
			switch(((SQLException) e).getErrorCode()) {
			case 2627: return new EntityAlreadyExistsException(o);//PK
			case 547: return new EntityValidationException();//FK 
			case 18456: return new DatabaseConnectionException();//Sign in error to database 
			case 50001: return new BalanceTooLowException();//
			case 50002: return new EntityNotFoundException();
			case 50003: return new DeleteAccountException(o);
			case 50004: return new Exceptions.LoginException(o);

			}
			return new DatabaseConnectionException();
		}
		return e;

	}

}
