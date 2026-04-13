module com.example.clinic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clinic to javafx.fxml;
    opens com.example.clinic.controladores to javafx.fxml;
    opens com.example.clinic.modelo to javafx.fxml;
    opens com.example.clinic.repositorio to javafx.fxml;
    opens com.example.clinic.servicio to javafx.fxml;
    exports com.example.clinic;
    exports com.example.clinic.repositorio;
    exports com.example.clinic.modelo;
    exports com.example.clinic.controladores;
    exports com.example.clinic.servicio;
}