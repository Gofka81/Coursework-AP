module com.example.courseworkap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.courseworkap to javafx.fxml;
    exports com.example.courseworkap;
    exports com.example.courseworkap.controller;
    opens com.example.courseworkap.controller to javafx.fxml;
}