module com.example.imersaojava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires lombok;
    requires java.desktop;
    requires spring.beans;


    opens com.example.imersaojava to javafx.fxml;
    exports com.example.imersaojava;
}