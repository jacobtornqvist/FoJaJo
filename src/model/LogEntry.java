package model;

import java.sql.Timestamp;

public class LogEntry {
	
	private int logID; 
	private double amount;
	private String counterParty;
	private Timestamp logTime;
	private int accountNbr;
	
	public LogEntry(int logID, double amount, String counterParty, Timestamp logTime, int accountNbr) {
		setLogID(logID);
		setAmount(amount);
		setCounterParty(counterParty);
		setLogTime(logTime);
		setAccountNbr(accountNbr);
	}
	
	public int getLogID() {
		return logID;
	}
	public void setLogID(int logID) {
		this.logID = logID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCounterParty() {
		return counterParty;
	}
	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	public int getAccountNbr() {
		return accountNbr;
	}
	public void setAccountNbr(int accountNbr) {
		this.accountNbr = accountNbr;
	}
	



}
