package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	private AppContext appContext;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("FoJaJo Banking");

		appContext = new AppContext();
		
		RootPane root = new RootPane();
		Label statusLabel = createStatusLabel();
		
		BorderPane appPane = new BorderPane();
		appPane.setCenter(root);
		appPane.setBottom(statusLabel);
		appPane.getBottom().setId("status-bar");
		
		primaryStage.setScene(new Scene(appPane));
		primaryStage.show();
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
