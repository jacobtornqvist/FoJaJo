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
	public double getAmmount() {
		return amount;
	}
	public void setAmmount(double ammount) {
		this.amount = ammount;
	}
	
}
