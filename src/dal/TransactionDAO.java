package dal;

import java.sql.CallableStatement;
import java.sql.Connection;


public class TransactionDAO {	
	
	ConnectionFactory conFact = new ConnectionFactory();

	public void transfer(int fromAccount, int toAccount, double amount)throws Exception {
		final String call = "{call user_transfer(?,?,?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)){
			stmt.setInt(1, fromAccount);
			stmt.setInt(2, toAccount);
			stmt.setDouble(3, amount);
			stmt.execute();
		} catch (Exception e) {
			throw e;
		}
	}



}
