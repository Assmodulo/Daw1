module org.example.videodawbd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.videodawbd to javafx.fxml;
    exports org.example.videodawbd;
}