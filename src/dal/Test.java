package dal;

import model.Customer;

public class Test {

	public static void main(String[] args) {
		
		CustomerDAO doa = new CustomerDAO();
		
		try {
			doa.createCustomer(new Customer("anna", "123"));
			Customer b = doa.getCustomer("anna");
			System.out.println(b.getUsername());
			doa.changePassword("anna", "124");
			Customer a = doa.getCustomer("anna");
			System.out.println(a.getPassword());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
