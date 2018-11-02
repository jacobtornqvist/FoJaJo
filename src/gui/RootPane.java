package gui;

import controller.Controller;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
	
	private AppContext appContext;
	public RootPane(Controller cont, AppContext appContext) {
		setId("root-pane");
		
	}
}
