package com.example.clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClinicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClinicApplication.class.getResource("/com/example/clinic/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 972, 627);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

