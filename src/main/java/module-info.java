module org.example.ticketofficecinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;
    requires javafx.media;


    opens org.example.ticketofficecinema to javafx.fxml;
    exports org.example.ticketofficecinema;
}