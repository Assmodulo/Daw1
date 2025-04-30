module org.example.interfazvideodaw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.interfazvideodaw to javafx.fxml;
    exports org.example.interfazvideodaw;
}