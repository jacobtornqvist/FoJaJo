package dal;

import java.sql.SQLException;

import Exceptions.*;

public class ExceptionHandler {
	public static Exception handleException(Exception e, Object o) {
		if (e instanceof SQLException) {
			switch (((SQLException) e).getErrorCode()) {
			case 2627:
				return new EntityAlreadyExistsException(o);// PK
			case 547:
				return new EntityValidationException(o);// FK
			case 2601:
				return new DatabaseConcurrencyException();// Unique
			case 18456:
				return new DatabaseConnectionException();// Sign in error to database
			case 50001:
				return new BalanceTooLowException();//
			}
			return new DatabaseConnectionException();
		}
		return e;

	}

}
