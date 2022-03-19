module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires org.hibernate.commons.annotations;

    opens com.example.demo to javafx.fxml;
    opens com.example.demo.model.dao to org.hibernate.orm.core, javafx.base;
    exports com.example.demo.ui;
    exports com.example.demo;
    opens com.example.demo.ui to javafx.fxml;
}