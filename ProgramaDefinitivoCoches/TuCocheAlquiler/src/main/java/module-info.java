module com.coches.tucochealquiler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.coches.tucochealquiler to javafx.fxml;
    exports com.coches.tucochealquiler;
}