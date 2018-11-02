package model;

public class TransactionLog {
	
	private double ammount;
	private String fromTo;
	private double currentAmmount;
	private Customer customer;
	
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public String getFromTo() {
		return fromTo;
	}
	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}
	public double getCurrentAmmount() {
		return currentAmmount;
	}
	public void setCurrentAmmount(double currentAmmount) {
		this.currentAmmount = currentAmmount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
