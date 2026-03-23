package com.example.clinic.controladores;

import com.example.clinic.modelo.Paciente;
import com.example.clinic.servicio.PacienteService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class PacienteController {
    @FXML
    private TableView<Paciente> tablaPacientes;
    @FXML
    private TableColumn<Paciente, String> colCurp;
    @FXML
    private TableColumn<Paciente, String> colNombre;
    @FXML
    private TableColumn<Paciente, Integer> colEdad;
    @FXML
    private TableColumn<Paciente, String> colTelefono;
    @FXML
    private TableColumn<Paciente, String> colAlergias;
    @FXML
    private TableColumn<Paciente, String> colEstatus;

    private ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    @FXML
    private Label lblTotal;
    @FXML
    private Label lblActivo;
    @FXML
    private Label lblInactivo;



    @FXML
    private Label lblMsg;
    private PacienteService service = new PacienteService();

    public void initialize() {
        configTable();
        listaPacientes.addListener((ListChangeListener<Paciente>) change -> {
            Resumen();
        });
        loadFile();
        tablaPacientes.setItems(listaPacientes);
        Resumen();
    }

    private void loadFile() {
        try{
            List<Paciente> items = service.loadList();
            listaPacientes.clear();
            listaPacientes.addAll(items);
            lblMsg.setText("Datos cargados con normalidad");
            lblMsg.setStyle("-fx-text-fill: green");
        } catch (Exception e) {
            lblMsg.setText("Error...");
            lblMsg.setStyle("-fx-text-fill: red");
        }
    }
    private  void configTable(){
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colAlergias.setCellValueFactory(new PropertyValueFactory<>("alergias"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }
    private void Resumen(){
        long total = listaPacientes.size();
        long activos = listaPacientes.stream().filter(p -> p.getEstatus().equalsIgnoreCase("Activo")).count();
        long inactivos = total - activos;

        lblTotal.setText(String.valueOf(total));
        lblActivo.setText(String.valueOf(activos));
        lblInactivo.setText(String.valueOf(inactivos));
    }
    @FXML
    private void handleNuevo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clinic/Formulario.fxml"));
            Parent root = loader.load();
            FormController formCtrl = loader.getController();
            if (formCtrl != null) {
                formCtrl.setListaCompartida(this.listaPacientes);
            }
            Scene scene = new Scene(root, 450, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Registrar Paciente");

            Stage currentStage = (Stage) tablaPacientes.getScene().getWindow();
            currentStage.close();
            stage.show();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lblMsg.setText("Error al abrir el formulario");
        }
    }
}
