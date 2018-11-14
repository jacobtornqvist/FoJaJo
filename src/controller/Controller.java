package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import dal.BankAccountDAO;
import dal.CustomerDAO;
import dal.LogEntryDAO;
import dal.TransactionDAO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.BankAccount;
import model.Customer;
import model.LogEntry;

public class Controller {
	private ObjectProperty<Customer> currentCustomerProperty;
	private ObjectProperty<BankAccount> currentBankAccountProperty;
	private CustomerDAO custDAO;
	private BankAccountDAO baDAO;
	private LogEntryDAO logEntryDAO;
	private TransactionDAO transDAO;
	{
		currentCustomerProperty = new SimpleObjectProperty<Customer>();
		currentBankAccountProperty = new SimpleObjectProperty<BankAccount>();
		custDAO = new CustomerDAO();
		baDAO = new BankAccountDAO();
		logEntryDAO = new LogEntryDAO();
		transDAO = new TransactionDAO();
	}

	public ObjectProperty<Customer> getCurrentCustomerProperty() {
		return currentCustomerProperty;
	}

	public Customer getCurrentCustomer() {
		return currentCustomerProperty.get();
	}

	// Customer
	public void createCustomer(String username, String password) throws Exception {
		custDAO.createCustomer(new Customer(username, password));
		setCurrentCustomer(custDAO.login(new Customer(username, password)));
	}
	public void login(String username, String password) throws Exception {
		setCurrentCustomer(custDAO.login(new Customer(username, password)));
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
	

	public void deleteBankAccount(int accNbr) throws Exception {
		baDAO.deleteBankAccount(accNbr);
	}

	public BankAccount getBankAccount(int accNbr) throws Exception {
		return baDAO.getBankAccount(accNbr);
	}

	public ArrayList<BankAccount> getAllBankAccounts() throws Exception {
		return baDAO.getAllBankAccounts(currentCustomerProperty.get().getUsername());
	}

	// LogEntry
	public ArrayList<LogEntry> getAllLogEntries(int accountNbr) throws Exception {
		return logEntryDAO.getLogEntries(accountNbr);
	}

	// Transaction
	public void transfer(int toAccount, double amount) throws Exception {
		transDAO.transfer(currentBankAccountProperty.get().getAccountNbr(), toAccount, amount);
	}

	public ArrayList<BankAccount> getBankAccounts(Customer c) {
		ArrayList<BankAccount> temp = new ArrayList<BankAccount>();
		temp.add(new BankAccount(1, "tempKontoNamn", "asf", 23));
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

	public void createBankAccount(String accName, int accNbr) throws Exception {
		baDAO.createBankAccount(new BankAccount(accNbr, currentCustomerProperty.get().getUsername(), accName, 0));
	}

	public ArrayList<LogEntry> getCurrentLogEntries() throws Exception {
		return getAllLogEntries(currentBankAccountProperty.get().getAccountNbr());
	}
}
