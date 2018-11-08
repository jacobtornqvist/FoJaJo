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

	// Customer
	public void createCustomer(String username, String password) throws Exception {
		custDAO.createCustomer(new Customer(username, password));
	}
	public Customer login(String username, String password) throws Exception {
		return custDAO.login(new Customer(username, password));
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
	

	// BankAccount
	public void createBankAccount(int accNbr, String owner, String accName, double balance) throws Exception {
		baDAO.createBankAccount(new BankAccount(accNbr, owner, accName, balance));
	}

	public void deleteBankAccount(int accNbr) throws Exception {
		baDAO.deleteBankAccount(accNbr);
	}

	public BankAccount getBankAccount(int accNbr) throws Exception {
		return baDAO.getBankAccount(accNbr);
	}

	public ArrayList<BankAccount> getAllBankAccounts(String username) throws Exception {
		return baDAO.getAllBankAccounts(username);
	}

	// LogEntry
	public ArrayList<LogEntry> getAllLogEntries(int accountNbr)throws Exception {
		return logEntryDAO.getLogEntries(accountNbr);
	}
	// Transaction
	public void transfer(int fromAccount, int toAccount, double amount)throws Exception {
		transDAO.transfer(fromAccount, toAccount, amount);
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

	public void createBankAccount(String text) {
		// TODO Auto-generated method stub
		
	}
}
