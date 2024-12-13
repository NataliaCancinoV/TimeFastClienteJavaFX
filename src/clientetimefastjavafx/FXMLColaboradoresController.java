/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.ColaboradorDAO;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLColaboradoresController implements Initializable {

    @FXML
    private TableColumn<?, ?> tc_nopersonal;
    @FXML
    private TableColumn<?, ?> tc_nombre;
    @FXML
    private TableColumn<?, ?> tc_apellidopaterno;
    @FXML
    private TableColumn<?, ?> tc_apellidomaterno;
    @FXML
    private TableColumn<?, ?> tc_curp;
    @FXML
    private TableColumn<?, ?> tc_correo;
    @FXML
    private TableColumn<?, ?> tc_rol;
    @FXML
    private TableView<Colaborador> table_colaboradores;

    private ObservableList<Colaborador> colaboradores;
    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private Button unidadesViewBtn;
    @FXML
    private Button clientesViewBtn;
    @FXML
    private Button enviosViewBtn;
    @FXML
    private Button paquetesViewBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla() {
        tc_nopersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));//NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        tc_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tc_apellidopaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        tc_apellidomaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        tc_curp.setCellValueFactory(new PropertyValueFactory("curp"));
        tc_correo.setCellValueFactory(new PropertyValueFactory("correo"));
        tc_rol.setCellValueFactory(new PropertyValueFactory("nombreColaborador"));
    }

    private void cargarInformacionTabla() {
        //LAS TABLAS NO SOPORTAN LIST SI NO UN OBSERVABLE LIST-> PORQUE USA UN PATRON DE DISÑEO OBSERVER
        colaboradores = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerColaboradores();
        if (listaWS != null) {
            colaboradores.addAll(listaWS);
            table_colaboradores.setItems(colaboradores);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los colaboradores, intentelo más tarde", Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void cargarVistaUnidades(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLUnidades.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaClientes(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLClientes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVsitaEnvios(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLEnvios.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaPaquetes(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPaquetes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
