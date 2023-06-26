module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.main to javafx.fxml;
    opens com.main.Model to javafx.base;
    opens com.main.Controller to javafx.fxml;
    opens com.main.View to javafx.graphics;
    exports com.main;
}