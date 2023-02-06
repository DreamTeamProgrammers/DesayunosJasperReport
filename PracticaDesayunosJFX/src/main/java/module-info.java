module com.mycompany.practicadesayunosjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.base;
    requires jasperreports;
    requires javafx.swing;

    opens models;
    opens com.mycompany.practicadesayunosjfx to javafx.fxml;
    exports com.mycompany.practicadesayunosjfx;
}

