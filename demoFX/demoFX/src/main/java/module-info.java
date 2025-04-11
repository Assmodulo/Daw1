module org.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens org.example.demofx to javafx.fxml;
    exports org.example.demofx;
}