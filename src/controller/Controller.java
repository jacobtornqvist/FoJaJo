package controller;

import java.util.ArrayList;

import dal.CustomerDAO;
import dal.LogEntryDAO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Customer;
import model.LogEntry;

public class Controller {
	private ObjectProperty<Customer> currentCustomerProperty;
	private CustomerDAO custDAO;
	private LogEntryDAO logEntryDAO;
	{
		currentCustomerProperty = new SimpleObjectProperty<Customer>();
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
//Customer
	public void createCustomer(String username, String password) throws Exception {
		custDAO.createCustomer(new Customer(username, password));
	}
	public Customer getCustomer(String username) throws Exception {
		return custDAO.getCustomer(username);
	}
	public void updatePassword(String username, String newPassword) throws Exception {
		custDAO.changePassword(username, newPassword);
	}
	public void deleteCustomer(String username) throws Exception {
		custDAO.deleteCustomer(username);
	}
//BankAccount
//LogEntry
	public ArrayList<LogEntry> getAllLogEntries(int accountNbr){
		return logEntryDAO.getLogEntries(accountNbr);
	}

}
