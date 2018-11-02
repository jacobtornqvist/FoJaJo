package model;

public class TransactionLog {
	
	private double amount;
	private String fromTo;
	private double currentAmount;
	private Customer customer;
	
	public double getAmmount() {
		return amount;
	}
	public void setAmmount(double ammount) {
		this.amount = ammount;
	}
	public String getFromTo() {
		return fromTo;
	}
	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}
	public double getCurrentAmmount() {
		return currentAmount;
	}
	public void setCurrentAmmount(double currentAmmount) {
		this.currentAmount = currentAmmount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
