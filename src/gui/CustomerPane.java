package gui;

import controller.Controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.BankAccount;
import model.Customer;

public class CustomerPane extends GridPane {
	private Controller cont;
	private AppContext appContext;

	private Label customerNameLbl, bankAccountLbl;
	private TableView<BankAccount> bankAccountsTable;
	private Button logoutButton;

	public CustomerPane(Controller cont, AppContext appContext) {
		this.cont = cont;
		initComponents();
		initListeners();
		loadCustomer(cont.getCurrentCustomer());
	}

	private void initComponents() {
		customerNameLbl = new Label("Användarnamn");
		customerNameLbl.setFont(Font.font(30));
		bankAccountLbl = new Label("Bankkonton");
		logoutButton = new Button("Logga ut");
		bankAccountsTable = new TableView<BankAccount>();

		TableColumn<BankAccount, String> nameCol = new TableColumn<BankAccount, String>("Kontonamn");
		nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAccountName()));
		TableColumn<BankAccount, Number> idCol = new TableColumn<BankAccount, Number>("Kontonummer");
		idCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAccountNbr()));
		TableColumn<BankAccount, String> amountCol = new TableColumn<BankAccount, String>("Saldo");
		amountCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBalance() + " kr"));

		bankAccountsTable.getColumns().add(nameCol);
		bankAccountsTable.getColumns().add(idCol);
		bankAccountsTable.getColumns().add(amountCol);

		bankAccountsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		setPadding(new Insets(10));
		add(customerNameLbl, 0, 0);
		add(logoutButton, 1, 0);
		GridPane.setHalignment(logoutButton, HPos.RIGHT);
		GridPane.setHgrow(logoutButton, Priority.ALWAYS);
		add(bankAccountLbl, 0, 1);
		add(bankAccountsTable, 0, 5, 2, 1);
		GridPane.setValignment(bankAccountsTable, VPos.BOTTOM);
		GridPane.setVgrow(bankAccountsTable, Priority.ALWAYS);
	}

	private void initListeners() {
		logoutButton.setOnAction(e -> {
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
	}

	private void loadCustomer(Customer c) {
		customerNameLbl.setText(c == null ? "" : c.getUsername());
		bankAccountsTable.setItems(c == null ? null : FXCollections.observableArrayList(cont.getBankAccounts(c)));
	}
}
