package dal;

import java.sql.SQLException;
import Exception.*;

public class ExceptionHandler {
	public static Exception handelException(Exception e, Object o) {
		if(e instanceof NullPointerException) {
			
		}
		else if(e instanceof SQLException) {
			switch(((SQLException) e).getErrorCode()) {
			case 0: return new DatabaseConnectionException();//no connection
			case 2627: return new EntityAlreadyExistsException(o);//PK
			case 547: return new EntityValidationException(o);//FK
			case 2601: return new DatabaseConcurrencyException();//Uniqe
			case 50001: return new BalanceTooLowException();
			}
			return new DatabaseConnectionException();
		}
		
		return null;
	}

}
