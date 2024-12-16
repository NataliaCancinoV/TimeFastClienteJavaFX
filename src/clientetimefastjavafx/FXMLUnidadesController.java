/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.UnidadDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Unidad;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLUnidadesController implements Initializable, NotificadorOperaciones {

    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private TableColumn<?, ?> columnVIN;
    @FXML
    private TableColumn<?, ?> columnIDInterno;
    @FXML
    private TableColumn<?, ?> columnMarca;
    @FXML
    private TableColumn<?, ?> columnModelo;
    @FXML
    private TableColumn<?, ?> columnAnio;
    @FXML
    private TableColumn<?, ?> columnConductor;
    @FXML
    private TableColumn<?, ?> columnTipounidad;
    @FXML
    private TableView<Unidad> tableUnidades;
    private ObservableList<Unidad> unidades;

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
    private void cargarVistaColaboradores(ActionEvent event) {
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
        try {
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
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
        columnVIN.setCellValueFactory(new PropertyValueFactory("vin"));
        columnIDInterno.setCellValueFactory(new PropertyValueFactory("nii"));
        columnMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        columnModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        columnAnio.setCellValueFactory(new PropertyValueFactory("anio"));
        columnConductor.setCellValueFactory(new PropertyValueFactory("nombreConductor"));
        columnTipounidad.setCellValueFactory(new PropertyValueFactory("tipoUnidad"));
    }

    public void irFormulario(NotificadorOperaciones observador, Unidad unidad) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUnidadesFormulario.fxml"));
            Parent root = loader.load();

            FXMLUnidadesFormularioController controlador = loader.getController();
            controlador.inicializarValores(observador, unidad);

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

    private void eliminarUnidad() {
        Integer idUnidad = tableUnidades.getSelectionModel().getSelectedItem().getIdUnidad();
        //String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la eliminación: ");
        Mensaje respuesta = UnidadDAO.eliminarUnidad(idUnidad,"Ejemplo");
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Elminación exitosa", "La unidad ha sido eliminada correctamente", Alert.AlertType.INFORMATION);
            notificarOperacion("Elminacion de unidad","" );
        }else{
            Utilidades.mostrarAlerta("Error en la elminación", "La unidad no ha sido eliminada correctamente "+respuesta.getMensaje(), Alert.AlertType.ERROR);           
        }
    }

    private void cargarDatosTabla() {
        unidades = FXCollections.observableArrayList();
        List<Unidad> unidadesWS = UnidadDAO.obtenerUndiades();
        if (unidadesWS != null) {
            unidades.addAll(unidadesWS);
            tableUnidades.setItems(unidades);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de las unidades, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void notificarOperacion(String tipoOperacioin, String nombre) {
        System.out.println("Operacion: " + tipoOperacioin);
        cargarDatosTabla();
    }

    @FXML
    private void btnElminarUnidad(ActionEvent event) {
        eliminarUnidad();
        
    }

    @FXML
    private void cargarVistaUnidadEditar(ActionEvent event) {
        Unidad unidad = tableUnidades.getSelectionModel().getSelectedItem();
        irFormulario(this, unidad);
    }

    @FXML
    private void cargarVistaUnidadFormulario(ActionEvent event) {
        irFormulario(this, null);
    }

}
