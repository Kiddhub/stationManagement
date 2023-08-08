package com.main.Controller.Admin;

import com.main.Model.Customer;
import com.main.View.Admin.BillManagement;
import com.main.View.Admin.EmployeeManagement;
import com.main.View.Admin.StationManagement;
import com.main.View.Employee.Bill;
import com.main.View.Login;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CusManagement implements Initializable {
    @FXML
    private TextField customerNameField;
    @FXML
    private DatePicker dobField;
    @FXML
    private TextField customerAddressField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextField customerIdCardField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField idField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> dob;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, String> phone;
    @FXML
    private TableColumn<Customer, String> idCard;
    @FXML
    private TableColumn<Customer, String> username;
    @FXML
    private TableColumn<Customer, String> password;

    @FXML
    private Button employeeButton;
    @FXML
    private Button stationButton;
    @FXML
    private Button billButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button deleteButton;


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        dob.setCellValueFactory(new PropertyValueFactory<Customer, String>("dob"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        idCard.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerIdCard"));
        username.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        ObservableList<Customer> customerList = Customer.getCustomerFromData();
        table.setItems(customerList);
    }

    public void deleteBtn(){
        String id = idField.getText();
        if (id.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Phai nhap ID");
        } else {
            boolean isDeleted = Customer.deleteCustomer(id);
            if (isDeleted) {
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Xoá thành công ");
                idField.setText("");
                ObservableList<Customer> customerList = Customer.getCustomerFromData();
                table.setItems(customerList);
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Xoá không thành công");
            }
        }
    }


    public void employeeBtn() throws Exception {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.start(new Stage());
        Stage currentStage = (Stage) employeeButton.getScene().getWindow();
        currentStage.close();
    }

    public void stationBtn() throws Exception {
        StationManagement stationManagement = new StationManagement();
        stationManagement.start(new Stage());
        Stage currentStage = (Stage) stationButton.getScene().getWindow();
        currentStage.close();
    }

    public void billBtn() throws Exception {
        BillManagement billManagement = new BillManagement();
        billManagement.start(new Stage());
        Stage currentStage = (Stage) billButton.getScene().getWindow();
        currentStage.close();
    }

    public void customerBtn() throws Exception {
        com.main.View.Admin.CusManagement cusManagement = new com.main.View.Admin.CusManagement();
        cusManagement.start(new Stage());
        Stage currentStage = (Stage) customerButton.getScene().getWindow();
        currentStage.close();
    }

    public void logOutBtn() throws Exception {
        Login login = new Login();
        login.start(new Stage());
        Stage currentStage = (Stage) logOutButton.getScene().getWindow();
        currentStage.close();
    }

}
