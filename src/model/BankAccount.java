package model;

public class BankAccount {
	
	private Customer customer;
	private String accountName;
	private int accountNbr;
	private double  balance;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getAccountNbr() {
		return accountNbr;
	}
	public void setAccountNbr(int accountNbr) {
		this.accountNbr = accountNbr;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
