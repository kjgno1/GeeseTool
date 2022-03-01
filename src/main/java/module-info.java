module com.ptn.geesetool {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.jsoup;
    requires opencsv;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires org.controlsfx.controls;
    requires org.flywaydb.core;
    requires c3p0;
    requires java.net.http;
    requires javafx.graphics;
    requires thumbnailator;

    opens com.ptn.geesetool.controllers to javafx.fxml;
    exports com.ptn.geesetool;
    exports com.ptn.geesetool.controllers;
    opens com.ptn.geesetool.model;
}