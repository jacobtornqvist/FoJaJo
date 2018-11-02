package model;

import java.util.List;

public class Customer {
	
	private String username;
	private String password;
	private List<BankAccount> bankAccounts;
	private TransactionLog transLog;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public TransactionLog getTransLog() {
		return transLog;
	}
	public void setTransLog(TransactionLog transLog) {
		this.transLog = transLog;
	}

	
	

}
