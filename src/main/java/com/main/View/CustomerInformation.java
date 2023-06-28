package com.main.View;

import com.main.Controller.CustomerController;
import com.main.Controller.CustomerInformationController;
import com.main.Controller.EmployeeController;
import com.main.Model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerInformation extends Application {
    private Customer customer;

    public CustomerInformation(Customer customer) {
        this.customer = customer;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/main/customerInformation.fxml"));
        loader.setControllerFactory(c -> new CustomerInformationController(customer));
        Parent root = loader.load();
        Scene scene = new Scene(root, 335, 600);
        primaryStage.setTitle("Thông tin khách hàng");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
