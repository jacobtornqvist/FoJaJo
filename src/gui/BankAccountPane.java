package gui;

import controller.Controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.BankAccount;
import model.LogEntry;

public class BankAccountPane extends GridPane {
	private Controller cont;
	private AppContext appContext;

	private Label accountNameLbl, transactionHistoryLbl;
	private TableView<LogEntry> logTable;
	private Button backButton;

	
	public BankAccountPane(Controller cont, AppContext appContext) {
		this.cont = cont;
		initComponents();
		initListeners();
	}
	
	private void initComponents() {
		accountNameLbl = new Label("Användarnamn");
		accountNameLbl.setFont(Font.font(30));
		transactionHistoryLbl = new Label("Transaktionshistorik");
		backButton = new Button("Tillbaka");
		logTable = new TableView<LogEntry>();
		
		TableColumn<LogEntry, String> counterCol = new TableColumn<LogEntry, String>("Motpart");
		counterCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCounterParty()));
		TableColumn<LogEntry, Number> amountCol = new TableColumn<LogEntry, Number>("Belopp");
		amountCol.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAmount()));
		TableColumn<LogEntry, String> timestampCol = new TableColumn<LogEntry, String>("Datum");
		timestampCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLogTime().toString()));
		
		logTable.getColumns().add(counterCol);
		logTable.getColumns().add(amountCol);
		logTable.getColumns().add(timestampCol);

		logTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		setPadding(new Insets(10));
		add(accountNameLbl, 0, 0);
		add(backButton, 1, 0);
		GridPane.setHalignment(backButton, HPos.RIGHT);
		GridPane.setHgrow(backButton, Priority.ALWAYS);
		add(transactionHistoryLbl, 0, 4);
		add(logTable, 0, 5, 2, 1);
		GridPane.setValignment(logTable, VPos.BOTTOM);
		GridPane.setVgrow(logTable, Priority.ALWAYS);
	}
	
	private void initListeners() {
		backButton.setOnAction(e -> {
			cont.setCurrentBankAccount(null);
		});
		
		cont.getCurrentBankAccountProperty().addListener((observable, oldValue, newValue) -> {
			loadBankAccount(newValue);
		});
		
	}
	
	private void loadBankAccount(BankAccount b) {
		accountNameLbl.setText(b == null ? "" : b.getAccountName());
		logTable.setItems(b == null ? null : FXCollections.observableArrayList(cont.getLogEntries(b)));
	}
}
