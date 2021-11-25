module com.example.practicapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.practicapp to javafx.fxml;
    exports com.example.practicapp;
}