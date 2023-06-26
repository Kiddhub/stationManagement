package com.main.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeManagement extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/main/employeeManagement.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 1120, 500);
//        stage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
