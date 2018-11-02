package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Customer;

public class Controller {
	private ObjectProperty<Customer> currentCustomerProperty;
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

}
