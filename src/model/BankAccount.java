package model;

public class BankAccount {
	
	private int accountNbr;
	private String accountName;
	private double balance;
	private String customerOwner;
	
	
	public BankAccount() {}
	
	public BankAccount(int accountNbr, String accountName, double balance, String customerOwner) {
		this.accountNbr = accountNbr;
		this.accountName = accountName;
		this.balance = balance;
		this.customerOwner = customerOwner;		
	}

	public String getCustomerOwner() {
		return customerOwner;
	}
	public void setCustomerOwner(String customerOwner) {
		this.customerOwner = customerOwner;
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
