module com.yyin.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires jdk.management;
    requires jdk.naming.rmi;
    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires commons.dbutils;
    requires java.sql;
    requires druid;

    opens com.yyin.testfx to javafx.fxml;
    exports com.yyin.testfx;
    exports com.yyin.testfx.controllers;
    opens com.yyin.testfx.controllers to javafx.fxml;
    exports com.yyin.testfx.utils;
}