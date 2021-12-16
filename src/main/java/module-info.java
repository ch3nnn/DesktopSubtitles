module cn.ch3nnn.desktopsubtitle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens cn.ch3nnn.desktopsubtitle to javafx.fxml;
    exports cn.ch3nnn.desktopsubtitle;
}