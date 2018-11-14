package gui;

import java.text.DecimalFormat;
import Exceptions.ErrorHandler;
import controller.Controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.BankAccount;
import model.Customer;

public class CustomerPane extends GridPane {
	private Controller cont;
	private AppContext appContext;
	private CreateAccountPane createAccPane;
	private Label customerNameLbl, bankAccountLbl;
	private TableView<BankAccount> bankAccountsTable;
	private Button logoutBtn, addAccountBtn, removeAccountBtn;

	public CustomerPane(Controller cont, AppContext appContext) {
		this.cont = cont;
		this.appContext = appContext;
		initComponents();
		initListeners();
		loadCustomer(cont.getCurrentCustomer());
	}

	private void initComponents() {
		customerNameLbl = new Label("Användarnamn");
		customerNameLbl.setFont(Font.font(30));
		bankAccountLbl = new Label("Bankkonton");
		logoutBtn = new Button("Logga ut");
		addAccountBtn = new Button("Lägg till konto");
		removeAccountBtn = new Button("Ta bort konto");
		bankAccountsTable = new TableView<BankAccount>();

		TableColumn<BankAccount, String> nameCol = new TableColumn<BankAccount, String>("Kontonamn");
		nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAccountName()));
		TableColumn<BankAccount, Number> idCol = new TableColumn<BankAccount, Number>("Kontonummer");
		idCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAccountNbr()));
		TableColumn<BankAccount, String> amountCol = new TableColumn<BankAccount, String>("Saldo");
		DecimalFormat df = new DecimalFormat("#.##");
		amountCol.setCellValueFactory(data -> new SimpleStringProperty(df.format(data.getValue().getBalance()) + " kr"));

		bankAccountsTable.getColumns().add(nameCol);
		bankAccountsTable.getColumns().add(idCol);
		bankAccountsTable.getColumns().add(amountCol);

		bankAccountsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		setPadding(new Insets(10));
		setVgap(5);
		setHgap(5);
		add(customerNameLbl, 0, 0);
		add(logoutBtn, 1, 0);
		GridPane.setHalignment(logoutBtn, HPos.RIGHT);
		GridPane.setHgrow(logoutBtn, Priority.ALWAYS);
		add(bankAccountLbl, 0, 1);
		add(bankAccountsTable, 0, 5, 2, 1);
		add(addAccountBtn, 0, 6);
		GridPane.setHgrow(addAccountBtn, Priority.ALWAYS);
		addAccountBtn.setMaxWidth(Double.MAX_VALUE);
		add(removeAccountBtn, 1, 6);
		GridPane.setHgrow(removeAccountBtn, Priority.ALWAYS);
		removeAccountBtn.setMaxWidth(Double.MAX_VALUE);
		removeAccountBtn.disableProperty().bind(Bindings.isNull(bankAccountsTable.getSelectionModel().selectedItemProperty()));
		GridPane.setValignment(bankAccountsTable, VPos.BOTTOM);
		GridPane.setVgrow(bankAccountsTable, Priority.ALWAYS);
	}

	private void initListeners() {
		logoutBtn.setOnAction(e -> {
			cont.setCurrentCustomer(null);
		});

		cont.getCurrentCustomerProperty().addListener((observable, oldValue, newValue) -> {
			loadCustomer(newValue);
		});

		bankAccountsTable.setRowFactory(tv -> {
			TableRow<BankAccount> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					cont.setCurrentBankAccount(row.getItem());
				}
			});
			return row;
		});

		addAccountBtn.setOnAction(e -> {
			getChildren().remove(createAccPane);
			createAccPane = new CreateAccountPane();
			add(createAccPane, 0, 7, 2, 1);
		});
		
		removeAccountBtn.setOnAction(x -> {
			try {
				cont.deleteBankAccount(bankAccountsTable.getSelectionModel().getSelectedItem().getAccountNbr());
				bankAccountsTable.setItems(FXCollections.observableArrayList(cont.getAllBankAccounts()));
				appContext.setSuccess("Kontot har tagits bort.");
			}catch (Exception e) {
				appContext.setError(ErrorHandler.handleException(e));
			}
		});
	}

	private void loadCustomer(Customer c) {
		customerNameLbl.setText(c == null ? "" : c.getUsername());
		try {
			bankAccountsTable.setItems(c == null ? null : FXCollections.observableArrayList(cont.getAllBankAccounts()));
		} catch (Exception e) {
			appContext.setError(ErrorHandler.handleException(e));
		}
	}

	private class CreateAccountPane extends GridPane {
		private Label titleLabel;
		private Button addButton, closeButton;
		private TextField accNameInput, accNbrInput;

		protected CreateAccountPane() {
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
			titleLabel = new Label("Skapa nytt konto");
			titleLabel.setStyle("-fx-font-weight: bold;");

			add(titleLabel, 0, 0, 2, 1);
			Label accNameLabel = new Label("Kontonamn (*):");
			accNameInput = new TextField();
			accNameInput.setMaxWidth(Double.MAX_VALUE);
			GridPane.setHgrow(accNameInput, Priority.ALWAYS);

			Label accNbrLabel = new Label("Kontonummer (*):");
			accNbrInput = new TextField();
			accNbrInput.setMaxWidth(Double.MAX_VALUE);
			GridPane.setHgrow(accNbrInput, Priority.ALWAYS);

			add(accNameLabel, 0, 1);
			add(accNameInput, 1, 1);
			add(accNbrLabel, 0, 2);
			add(accNbrInput, 1, 2);

			addButton = new Button("Lägg till");
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

			addButton.setOnAction(x -> {
				try {
					cont.createBankAccount(accNameInput.getText(), Integer.valueOf(accNbrInput.getText()));
					bankAccountsTable.setItems(FXCollections.observableArrayList(cont.getAllBankAccounts()));
					appContext.setSuccess("Kontot med namn: " + accNameInput.getText() + " och kontonummer: " + accNbrInput.getText() + " har skapats.");
				} catch (Exception e) {
					appContext.setError(ErrorHandler.handleException(e));
				}
			});

			closeButton.setOnAction(e -> {
				((Pane) getParent()).getChildren().remove(this);
			});
		}

		private boolean isValid() {
			try {
				Integer.valueOf(accNbrInput.getText());
			} catch (Exception e) {
				return false;
			}
			return !(accNameInput.getText().isEmpty() || accNbrInput.getText().isEmpty());
		}

		protected String getValidationErrorMessage() {
			return "Vänligen kontrollera att alla fält är korrekt ifyllda.";
		}
	}
}
