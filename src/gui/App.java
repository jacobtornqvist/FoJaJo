package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
	private AppContext appContext;
	private Controller cont;
	private LoginPane loginPane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setHeight(600);
		primaryStage.setWidth(600);
		primaryStage.setTitle("FoJaJo Banking");

		appContext = new AppContext();
		cont = new Controller();
		loginPane = new LoginPane(cont);

		RootPane rootPane = new RootPane(cont, appContext);
		Label statusLabel = createStatusLabel();
		
		loginPane.setBottom(statusLabel);
		rootPane.setBottom(statusLabel);

	
		primaryStage.setScene(new Scene(loginPane));
		primaryStage.show();
		
		cont.getCurrentCustomerProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				primaryStage.getScene().setRoot(rootPane);
				primaryStage.setTitle("FoJaJo Banking - " + newValue.getUsername());
			}else {
				primaryStage.setTitle("FoJaJo Banking - Sign in");
				primaryStage.getScene().setRoot(loginPane);
			}
		});
		primaryStage.setOnHidden(e -> {
			if(cont.getCurrentCustomer() != null) ;
		});
	}

	private Label createStatusLabel() {
		Label statusLabel = new Label();
		statusLabel.textProperty().bind(appContext.getStatusStringProp());
		statusLabel.textFillProperty().bind(appContext.getStatusColorProp());
		statusLabel.setPadding(new Insets(10, 10, 10, 10));
		statusLabel.setMaxWidth(Double.MAX_VALUE);
		return statusLabel;
	}
}
