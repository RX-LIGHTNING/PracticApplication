module com.example.practicapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;
    requires org.apache.commons.compress;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.desktop;
    opens com.example.practicapp to javafx.fxml;
    exports com.example.practicapp;
    exports com.example.practicapp.objects;
    opens com.example.practicapp.objects to javafx.fxml;

}