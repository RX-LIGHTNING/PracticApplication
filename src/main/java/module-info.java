module com.example.practicapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.postgresql.jdbc;
    requires java.sql;
    opens com.example.practicapp to javafx.fxml;
    exports com.example.practicapp;
    exports com.example.practicapp.objects;
    opens com.example.practicapp.objects to javafx.fxml;
}