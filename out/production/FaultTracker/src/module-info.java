module FaultTracker{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires java.naming;
    requires javafx.web;
    requires java.desktop;
    opens View;
    opens Model;
    opens Controller;
}