package gui;

import Exceptions.ErrorHandler;
import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class LoginPane extends BorderPane {
	private Controller cont;
	private AppContext appContext;

	private Label headerLbl, accNameLbl, accPassLbl;
	private TextField accNameInput, accPassInput;
	private Button loginBtn, createAccBtn;
	private GridPane contentGrid;

	{
		componentsInit();
		listenersInit();

	}

	public LoginPane(Controller cont, AppContext appContext) {
		this.cont = cont;
		this.appContext = appContext;
	}

	private void componentsInit() {
		contentGrid = new GridPane();
		contentGrid.setVgap(5);
		contentGrid.setMaxSize(150, 250);

		headerLbl = new Label("Logga in");
		headerLbl.setFont(Font.font(30));
		accNameLbl = new Label("Användarnamn:");
		accPassLbl = new Label("Lösenord:");
		accNameInput = new TextField();
		accPassInput = new PasswordField();
		loginBtn = new Button("Sign in");
		GridPane.setHgrow(loginBtn, Priority.ALWAYS);
		loginBtn.setMaxWidth(Double.MAX_VALUE);
		createAccBtn = new Button("Create account");
		GridPane.setHgrow(createAccBtn, Priority.ALWAYS);
		createAccBtn.setMaxWidth(Double.MAX_VALUE);

		contentGrid.add(headerLbl, 0, 0);
		contentGrid.add(accNameLbl, 0, 1);
		contentGrid.add(accNameInput, 0, 2);
		contentGrid.add(accPassLbl, 0, 3);
		contentGrid.add(accPassInput, 0, 4);
		contentGrid.add(loginBtn, 0, 5);
		contentGrid.add(createAccBtn, 0, 6);

		setCenter(contentGrid);
	}

	private void listenersInit() {
		loginBtn.setOnAction(x -> {
			if (accNameInput.getText().isEmpty() || accPassInput.getText().isEmpty()) {
				appContext.setError("Vänligen kontrollera att alla fält är korrekt ifyllda.");
				return;
			}
			try {
				cont.login(accNameInput.getText(), accPassInput.getText());
			} catch (Exception e) {
				appContext.setError(ErrorHandler.handleException(e));
			}
		});

		createAccBtn.setOnAction(x -> {
			if (accNameInput.getText().isEmpty() || accPassInput.getText().isEmpty()) {
				appContext.setError("Vänligen kontrollera att alla fält är korrekt ifyllda.");
				return;
			}
			try {
				cont.createCustomer(accNameInput.getText(), accPassInput.getText());
			} catch (Exception e) {
				appContext.setError(ErrorHandler.handleException(e));
			}
		});
	}
}