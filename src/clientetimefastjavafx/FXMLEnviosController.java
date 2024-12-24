/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.EnvioDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Envio;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLEnviosController implements Initializable, NotificadorOperaciones {

    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private TableColumn<?, ?> columnNoGuia;
    @FXML
    private TableColumn<?, ?> columnCliente;
    @FXML
    private TableColumn<?, ?> columnOrigen;
    @FXML
    private TableColumn<?, ?> columnDestino;
    @FXML
    private TableColumn<?, ?> columnCosto;
    @FXML
    private TableColumn<?, ?> columnConductor;
    @FXML
    private TableColumn<?, ?> columnEstatus;

    private ObservableList<Envio> envios;
    @FXML
    private TableView<Envio> tableEnvios;
    @FXML
    private Button btnEliminarEnvio;
    @FXML
    private Button cargarVistaFormularioEditar;
    @FXML
    private Button cargarVistaEnvioFormulario;
    @FXML
    private TextField tfBuscarEnvio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarDatosTabla();
    }

    @FXML
    private void cargarVistaColaborador(ActionEvent event) {
        try {
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLColaboradores.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaUnidades(ActionEvent event) {
        try {
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
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
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLClientes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaEnvios(ActionEvent event) {
    }

    @FXML
    private void cargarVistaPaquetes(ActionEvent event) {
        try {
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPaquetes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configurarTabla() {
        //NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        columnNoGuia.setCellValueFactory(new PropertyValueFactory("noGuia"));
        columnCliente.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        columnOrigen.setCellValueFactory(new PropertyValueFactory("estado"));
        columnDestino.setCellValueFactory(new PropertyValueFactory("destino"));
        columnCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        columnConductor.setCellValueFactory(new PropertyValueFactory("conductorAsignado"));
        columnEstatus.setCellValueFactory(new PropertyValueFactory("estatus"));

    }

    private void buscarEnvioNoGuia(Integer noGuia) {
        Envio envioWS = EnvioDAO.obtenerEnviosNoGuia(noGuia);
        System.out.println("Envio no guia: " + envioWS.getIdEnvio());
        if (envioWS != null) {
            envios.clear();
            envios.add(envioWS);
            tableEnvios.setItems(envios);
        }
    }

    public void cargarDatosTabla() {
        envios = FXCollections.observableArrayList();
        List<Envio> enviosWS = EnvioDAO.obtenerEnvios();
        if (enviosWS != null) {
            envios.addAll(enviosWS);
            tableEnvios.setItems(envios);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los envios, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    public void irFormulario(NotificadorOperaciones observador, Envio envio) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEnviosFormulario.fxml"));
            Parent root = loader.load();

            FXMLEnviosFormularioController controlador = loader.getController();
            controlador.inicializarValores(observador, envio);

            Stage escenario = new Stage();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Formulario Unidades");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede mostrar formulario de colaboradores, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cargarVistaFormularioEnvioEditar(ActionEvent event) {
        Envio envio = tableEnvios.getSelectionModel().getSelectedItem();
        irFormulario(this, envio);
    }

    @FXML
    private void cargarVistaEnvioFormulario(ActionEvent event) {
        irFormulario(this, null);
    }

    @Override
    public void notificarOperacion(String tipoOperacioin, String nombre) {
        System.out.println("Operacion: " + tipoOperacioin);
        cargarDatosTabla();
    }

    @FXML
    private void btnBuscarEnvio(MouseEvent event) {
        Integer noGuia = Integer.parseInt(tfBuscarEnvio.getText().trim());
        buscarEnvioNoGuia(noGuia);
    }

    @FXML
    private void irPantallaHistorial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLHistorialEstatus.fxml"));
            Parent root = loader.load();
            
            Stage escenario = new Stage();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Formulario Unidades");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
