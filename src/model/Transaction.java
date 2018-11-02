package model;

public class Transaction {
	private int Accountwithdraw;
	private int Accountdeposit;
	private double amount;
	public int getAccountwithdraw() {
		return Accountwithdraw;
	}
	public void setAccountwithdraw(int accountwithdraw) {
		Accountwithdraw = accountwithdraw;
	}
	public int getAccountdeposit() {
		return Accountdeposit;
	}
	public void setAccountdeposit(int accountdeposit) {
		Accountdeposit = accountdeposit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
