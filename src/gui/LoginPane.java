package gui;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class LoginPane extends BorderPane{
	private Controller cont;
	
	private Label headerLbl, accNameLbl, accPassLbl;
	private TextField accNameInput, accPassInput;
	private Button loginBtn, createAccBtn;
	private GridPane contentGrid;
	
	{
		componentsInit();
		listenersInit();
		
	}
	public LoginPane(Controller cont) {
		this.cont = cont;
	}
	
	private void componentsInit() {
		contentGrid = new GridPane();
		contentGrid.setVgap(5);
		contentGrid.setMaxSize(150, 250);
		
		headerLbl = new Label("Sign in");
		headerLbl.setFont(Font.font(30));
		accNameLbl = new Label("Username:");
		accPassLbl = new Label("Password:");
		accNameInput = new TextField();
		accPassInput = new TextField();
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
		loginBtn.setOnAction(e -> {
			cont.logInUser(accNameInput.getText(), accPassInput.getText());
		});
	}
}