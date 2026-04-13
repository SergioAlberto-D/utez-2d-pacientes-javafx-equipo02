package com.example.clinic.controladores;

import com.example.clinic.modelo.Paciente;
import com.example.clinic.servicio.PacienteService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FormController {
    @FXML private TextField txtCurp, txtName, txtEdad, txtTelefono, txtAlergias;
    private Paciente Editar;
    private ObservableList<Paciente> lista;
    private PacienteService service = new PacienteService();
    public void setListaCompartida(ObservableList<Paciente> lista) {
        this.lista = lista;
    }
    public void Edition(Paciente paciente, ObservableList<Paciente> listaCompartida) {
        this.Editar = paciente;
        this.lista = listaCompartida;
        txtCurp.setPromptText(paciente.getCurp());
        txtName.setPromptText(paciente.getNombre());
        txtEdad.setPromptText(paciente.getEdad());
        txtTelefono.setPromptText(paciente.getTelefono());
        txtAlergias.setPromptText(paciente.getAlergias());
        txtCurp.setEditable(false);
        txtCurp.setOpacity(0.7);
    }
    @FXML
    private void onaddPaciente(){
        String curp = txtCurp.getText().isEmpty() ? txtCurp.getPromptText() : txtCurp.getText();
        String name = txtName.getText().isEmpty() ? txtName.getPromptText() : txtName.getText();
        String edad = txtEdad.getText().isEmpty() ? txtEdad.getPromptText() : txtEdad.getText();
        String telefono = txtTelefono.getText().isEmpty() ? txtTelefono.getPromptText() : txtTelefono.getText();
        String alergias = txtAlergias.getText().isEmpty() ? txtAlergias.getPromptText() : txtAlergias.getText();

        try {
            if (Editar !=null){
                Editar.nombreProperty().set(name);
                Editar.edadProperty().set(edad);
                Editar.telefonoProperty().set(telefono);
                Editar.alergiasProperty().set(alergias);

                service.guardarCambios(lista);
                mostrarMensaje("Éxito", "Paciente actualizado correctamente.");
            }else {
                service.addPaciente(curp, name, edad, telefono, alergias, lista);
                mostrarMensaje("Éxito", "Paciente registrado correctamente.");
            }
            txtCurp.clear();
            txtEdad.clear();
            txtAlergias.clear();
            txtName.clear();
            txtTelefono.clear();
            volver();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        } catch (IOException e) {
            mostrarError("Error al mandar el archivo"+e.getMessage());
        }
    }
    private void mostrarMensaje(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText("No se pudo registrar al paciente");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clinic/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 807, 707));
            stage.setTitle("Sistema Clínico - Main");
            stage.show();
            Stage currentStage = (Stage) txtCurp.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
