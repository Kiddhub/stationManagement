package com.main.Controller;

import com.main.Model.Customer;
import com.main.View.CustomerInformation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerController {
    @FXML
    private Button logoutButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button createBillButton;
    private String username;

    public CustomerController(String username){
        this.username = username;
    }
    public void updateEmployeeOnAction() throws Exception{
        Customer customer = Customer.searchCustomer(username);
        CustomerInformation customerInformation = new CustomerInformation(customer);
        customerInformation.start(new Stage());
        Stage updateStage = (Stage) updateButton.getScene().getWindow();
        updateStage.close();

    }
}
