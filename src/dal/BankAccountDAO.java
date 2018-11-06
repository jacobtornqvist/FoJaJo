package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.BankAccount;

public class BankAccountDAO {
	private ConnectionFactory conFact = new ConnectionFactory();
	
	public void createBankAccount(BankAccount account) throws Exception {
		final String call = "{call user_createBankAccount(?,?,?,?)}";
		try (Connection con = conFact.createConnection(); CallableStatement cs = con.prepareCall(call)) {
			cs.setInt(1, account.getAccountNbr());
			cs.setString(2, account.getCustomerOwner());
			cs.setString(3, account.getAccountName());
			cs.setDouble(4, account.getBalance());
			
			cs.execute();

		} catch (Exception e) {
			throw e;
		}

	}

	public void deleteBankAccount(int accNbr) throws Exception {
		final String call = "{call user_deleteBankAccount(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement cs = con.prepareCall(call)) {
			cs.setInt(1, accNbr);
			cs.execute();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public BankAccount getBankAccount(int accNbr) throws Exception {
		final String call = "{call user_getBankAccount(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement cs = con.prepareCall(call)) {
			cs.setInt(1, accNbr);
			ArrayList<BankAccount> account = new ArrayList<BankAccount>(mapResultSetToAccount(cs.executeQuery()));
			
			return account.isEmpty() ? null : account.get(0);

		} catch (Exception e) {
			
		}
		return null;

	}
	
	public ArrayList<BankAccount> getAllBankAccounts(String username) throws Exception {
		final String call = "{call user_getAllBankAccounts(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement cs = con.prepareCall(call)) {
			cs.setString(1, username);
			ArrayList<BankAccount> account = new ArrayList<BankAccount>(mapResultSetToAccount(cs.executeQuery()));
			
			return account.isEmpty() ? null : account;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public ArrayList<BankAccount> mapResultSetToAccount(ResultSet rs) {
		try {
			ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
			
			while(rs.next()) {
				BankAccount ba = new BankAccount(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				accounts.add(ba);
			}
			return accounts;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
