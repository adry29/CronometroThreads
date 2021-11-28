module com.mycompany.cronometro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.cronometro to javafx.fxml;
    exports com.mycompany.cronometro;
}
