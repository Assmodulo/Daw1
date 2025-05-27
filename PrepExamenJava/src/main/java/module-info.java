module com.videodbd.prepexamenjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.videodbd.prepexamenjava to javafx.fxml;
    exports com.videodbd.prepexamenjava;
}