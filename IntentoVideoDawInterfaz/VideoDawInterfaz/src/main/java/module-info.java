module com.videodbd.videodawinterfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.videodbd.videodawinterfaz to javafx.fxml;
    exports com.videodbd.videodawinterfaz;
}