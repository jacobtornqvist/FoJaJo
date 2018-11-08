package gui;

import controller.Controller;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
	
	private CustomerPane custPane;
	private BankAccountPane bankAccountPane;
	
	public RootPane(Controller cont, AppContext appContext) {
		setId("root-pane");
		custPane = new CustomerPane(cont, appContext);
		bankAccountPane = new BankAccountPane(cont, appContext);
		
		setCenter(custPane);
		
		cont.getCurrentBankAccountProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				setCenter(bankAccountPane);
				appContext.clear();
			}else {
				setCenter(custPane);
				appContext.clear();
			}
		});
	}
	
}
