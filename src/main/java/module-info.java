module com.yyin.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires commons.email;
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
    requires kaptcha;
    requires java.desktop;
    requires jna.platform;
    requires jna;
    requires javafx.media;
    requires jaudiotagger;
    requires lombok;
    requires javax.annotation;
    requires fastjson;
    requires jlayer;

    opens com.yyin.testfx to javafx.fxml;
    exports com.yyin.testfx.controllers;
    opens com.yyin.testfx.controllers to javafx.fxml;
    exports com.yyin.testfx.utils;
    exports com.yyin.testfx.models;
    exports com.yyin.testfx;
    exports com.yyin.testfx.dao.in;
    exports com.yyin.testfx.dao;
    exports com.yyin.testfx.controllers.main;
    opens com.yyin.testfx.controllers.main to javafx.fxml;
}