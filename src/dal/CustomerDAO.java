package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerDAO {

	ConnectionFactory conFact = new ConnectionFactory();

	public void createCustomer(Customer customer) throws Exception {
		final String call = "{call user_createCustomer(?,?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)) {
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.execute();
		} catch (Exception e) {
			throw ExceptionHandler.handleException(e, customer);
		}
	}

	public Customer login(Customer customer) throws Exception {
		final String call = "{call user_logIn(?,?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)) {
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.execute();
			ArrayList<Customer> customers = mapResultsetToCustomer(stmt.getResultSet());
			return (!customers.isEmpty() ? customers.get(0) : null);
		} catch (Exception e) {
			throw ExceptionHandler.handleException(e, customer);
		}
	}

	public Customer getCustomer(String username) throws Exception {
		final String call = "{call user_getCustomer(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)) {
			stmt.setString(1, username);
			stmt.execute();
			ArrayList<Customer> customers = mapResultsetToCustomer(stmt.getResultSet());
			return (!customers.isEmpty() ? customers.get(0) : null);
		} catch (Exception e) {
			throw ExceptionHandler.handleException(e, null);
		}
	}

	public ArrayList<Customer> mapResultsetToCustomer(ResultSet result) throws Exception {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		while (result.next()) {
			customers.add(new Customer(result.getString(1), result.getString(2)));
		}
		return customers;
	}

	public void changePassword(String username, String newPassword) throws Exception {
		final String call = "{call user_changePassword(?,?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)) {
			stmt.setString(1, username);
			stmt.setString(2, newPassword);
			stmt.execute();
		} catch (Exception e) {
			throw ExceptionHandler.handleException(e, username);
		}
	}

	public void deleteCustomer(String username) throws Exception {
		final String call = "{call user_deleteCustomer(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)) {
			stmt.setString(1, username);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(((SQLException) e).getErrorCode());
			throw ExceptionHandler.handleException(e, username);
		}
	}
}
