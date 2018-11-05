package dal;

import model.Customer;

public class Test {

	public static void main(String[] args) {
		
		CustomerDAO doa = new CustomerDAO();

		try {
			Customer a = doa.getCustomer("helge");
			System.out.println(a.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
