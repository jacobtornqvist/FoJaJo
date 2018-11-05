package gui;

import controller.Controller;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
	
	private AppContext appContext;
	private CustomerPane custPane;
	private BankAccountPane bankAccountPane;
	
	public RootPane(Controller cont, AppContext appContext) {
		setId("root-pane");
		this.appContext = appContext;
		custPane = new CustomerPane(cont, appContext);
		bankAccountPane = new BankAccountPane(cont, appContext);
		
		setCenter(custPane);
		
		cont.getCurrentBankAccountProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				setCenter(bankAccountPane);
			}else {
				setCenter(custPane);
			}
		});
	}
	
}
