package dal;

import java.sql.ResultSet;
import model.Transaction;
import model.TransactionLog;

public class TransactionDAO {
	
	
	ResultSet result = null;
	
	


	public TransactionLog createTransactionLog(TransactionLog transactionLog)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transactionLog;
	}

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
