module com.example.courseworkap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mail;


    opens com.example.courseworkap to javafx.fxml;
    exports com.example.courseworkap;
}