module org.example.ejemplofxclase {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejemplofxclase to javafx.fxml;
    exports org.example.ejemplofxclase;
}