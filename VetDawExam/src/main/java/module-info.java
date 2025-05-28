module org.example.vetdawexam {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.vetdawexam to javafx.fxml;
    exports org.example.vetdawexam;
}