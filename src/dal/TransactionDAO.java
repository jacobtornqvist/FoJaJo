package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.BankAccount;
import model.Customer;
import model.Transaction;
import model.TransactionLog;

public class TransactionDAO {
	
	Connection con = null;
	ResultSet result = null;
	CallableStatement stmt;
	LoginUtil login;
	
	public Connection createConnection() throws SQLException {
		return con = DriverManager.getConnection(login.getUrl(), login.getUsername(), login.getPassword());
	}
	public BankAccount createBankAccount(BankAccount account)throws Exception{
		try {
			String call = "{call createbankaccount(?,?,?,?,?)}";
			stmt = createConnection().prepareCall(call);
			stmt.setInt(1, account.getAccountNbr());
			stmt.setString(2, account.getAccountName());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return account;
		
	}
	public Customer createCustomer(Customer customer)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return customer;
		
	}
	public TransactionLog createTransactionLog(TransactionLog transactionLog)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transactionLog;
	}
	public Customer getCustomer(String username)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public List<Customer> getAllCustomer(String searchString)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public BankAccount getBankAccount(int accountNbr)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public List<BankAccount> getCustomerBankAcount(String username)throws Exception{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}return null;
		
	}
	public Boolean transfer(Transaction transaction)throws Exception {
		try {
			updateTransactionLog(transaction);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public Boolean login(Customer customer)throws Exception {
		try {
			
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
