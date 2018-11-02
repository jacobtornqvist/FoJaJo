package model;

public class Transaction {
	
	private int accountWithdraw;
	private int accountDeposit;
	private double amount;
	public int getAccountwithdraw() {
		return accountWithdraw;
	}
	public void setAccountwithdraw(int accountwithdraw) {
		accountWithdraw = accountwithdraw;
	}
	public int getAccountdeposit() {
		return accountDeposit;
	}
	public void setAccountdeposit(int accountdeposit) {
		accountDeposit = accountdeposit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
