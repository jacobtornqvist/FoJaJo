package gui;

import controller.Controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.BankAccount;
import model.LogEntry;

public class BankAccountPane extends GridPane {
	private Controller cont;
	private AppContext appContext;

	private Label accountNameLbl, transactionHistoryLbl;
	private TableView<LogEntry> logTable;
	private Button backButton, transferButton;
	private TransactionPane transactionPane;

	public BankAccountPane(Controller cont, AppContext appContext) {
		this.cont = cont;
		this.appContext = appContext;
		initComponents();
		initListeners();
	}

	private void initComponents() {
		accountNameLbl = new Label("Användarnamn");
		accountNameLbl.setFont(Font.font(30));
		transactionHistoryLbl = new Label("Transaktionshistorik");
		backButton = new Button("Tillbaka");
		transferButton = new Button("Ny överföring");
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
		add(transferButton, 0, 6, 2, 1);
		GridPane.setHgrow(transferButton, Priority.ALWAYS);
		transferButton.setMaxWidth(Double.MAX_VALUE);
	}

	private void initListeners() {
		backButton.setOnAction(e -> {
			cont.setCurrentBankAccount(null);
		});

		cont.getCurrentBankAccountProperty().addListener((observable, oldValue, newValue) -> {
			loadBankAccount(newValue);
		});

		transferButton.setOnAction(e -> {
			getChildren().remove(transactionPane);
			transactionPane = new TransactionPane();
			add(transactionPane, 0, 7, 2, 1);
		});

	}

	private void loadBankAccount(BankAccount b) {
		accountNameLbl.setText(b == null ? "" : b.getAccountName());
		logTable.setItems(b == null ? null : FXCollections.observableArrayList(cont.getLogEntries(b)));
	}

	private class TransactionPane extends GridPane {
		private Label titleLabel;
		private Button addButton, closeButton;
		private TextField recieverInput, amountInput;

		protected TransactionPane() {
			super();
			init();
			setStyle("-fx-border-color: -fx-text-box-border");
			setPadding(new Insets(20));
		}

		public void init() {
			setHgap(10);
			setVgap(10);
			setMinHeight(USE_PREF_SIZE);
			setPadding(new Insets(20, 0, 0, 0));
			titleLabel = new Label("Överföring till konto");
			titleLabel.setStyle("-fx-font-weight: bold;");

			add(titleLabel, 0, 0, 2, 1);
			Label accNameLabel = new Label("Kontonamn (*):");
			recieverInput = new TextField();
			recieverInput.setMaxWidth(Double.MAX_VALUE);
			GridPane.setHgrow(recieverInput, Priority.ALWAYS);

			Label amountLabel = new Label("Belopp (*):");
			amountInput = new TextField();

			amountInput.setMaxWidth(Double.MAX_VALUE);
			GridPane.setHgrow(amountInput, Priority.ALWAYS);

			add(accNameLabel, 0, 1);
			add(recieverInput, 1, 1);
			add(amountLabel, 0, 2);
			add(amountInput, 1, 2);

			addButton = new Button("Skicka");
			addButton.setMaxWidth(Double.MAX_VALUE);
			closeButton = new Button("X");
			add(addButton, 1, 5);
			add(closeButton, 3, 0);
			addButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
				if (!isValid()) {
					actionEvent.consume();
					appContext.setError(getValidationErrorMessage());
				}
			});

			addButton.setOnAction(e -> {
				cont.createBankAccount(recieverInput.getText());
			});

			closeButton.setOnAction(e -> {
				((Pane) getParent()).getChildren().remove(this);
			});
		}

		private boolean isValid() {
			double d = 0;
			try {
				d = Double.parseDouble(amountInput.getText());
			} catch (Exception e) {
			}
			return !(recieverInput.getText().isEmpty() || amountInput.getText().isEmpty() || d == 0);
		}

		protected String getValidationErrorMessage() {
			return "Vänligen kontrollera att alla fält är korrekt ifyllda.";
		}
	}
}
