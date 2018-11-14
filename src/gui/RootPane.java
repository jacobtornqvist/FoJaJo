package gui;

import controller.Controller;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
	
	
	public RootPane(Controller cont, AppContext appContext) {

		setCenter(new CustomerPane(cont, appContext));
		
		cont.getCurrentBankAccountProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				setCenter(new BankAccountPane(cont, appContext));
			}else {
				setCenter(new CustomerPane(cont, appContext));
			}
		});
	}
	
}
