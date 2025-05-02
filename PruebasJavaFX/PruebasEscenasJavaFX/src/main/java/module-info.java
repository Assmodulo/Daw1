module com.videodbd.pruebasescenasjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.videodbd.pruebasescenasjavafx to javafx.fxml;
    exports com.videodbd.pruebasescenasjavafx;
}