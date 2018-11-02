package dal;

import java.sql.ResultSet;
import model.Transaction;


public class TransactionDAO {	
	ResultSet result = null;

	public Boolean transfer(Transaction transaction)throws Exception {
		try {
			updateTransactionLog(transaction);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

	public void updateTransactionLog(Transaction transaction)throws Exception {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	

}
