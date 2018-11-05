package controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.BankAccount;
import model.Customer;
import model.LogEntry;

public class Controller {
	private ObjectProperty<Customer> currentCustomerProperty;
	private ObjectProperty<BankAccount> currentBankAccountProperty;
	{
		currentCustomerProperty = new SimpleObjectProperty<Customer>();
		currentBankAccountProperty = new SimpleObjectProperty<BankAccount>();
	}

	public void logInUser(String accName, String accPass) {
		currentCustomerProperty.set(new Customer("asd", "asd"));
	}

	public ObjectProperty<Customer> getCurrentCustomerProperty() {
		return currentCustomerProperty;
	}

	public Customer getCurrentCustomer() {
		return currentCustomerProperty.get();
	}

	public ArrayList<BankAccount> getBankAccounts(Customer c) {
		ArrayList<BankAccount> temp = new ArrayList<BankAccount>();
		temp.add(new BankAccount(1, "tempKontoNamn", "asf" , 23));
		return temp;
	}

	public void setCurrentCustomer(Customer c) {
		currentCustomerProperty.set(c);
	}

	public ObjectProperty<BankAccount> getCurrentBankAccountProperty() {
		return currentBankAccountProperty;
	}

	public void setCurrentBankAccount(BankAccount b) {
		currentBankAccountProperty.set(b);
	}
	
	public BankAccount getCurrentBankAccount() {
		return currentBankAccountProperty.get();
	}

	public ArrayList<LogEntry> getLogEntries(BankAccount b) {
		ArrayList<LogEntry> temp = new ArrayList<LogEntry>();
		temp.add(new LogEntry(1, 23, "Pelle", new Timestamp(System.currentTimeMillis()), 30));
		return temp;
	}
}
