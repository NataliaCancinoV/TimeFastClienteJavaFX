/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.PaqueteDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Paquete;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLPaquetesController implements Initializable, NotificadorOperaciones {

    private ObservableList<Paquete> paquetes;
    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private TableColumn<?, ?> columnEnvio;
    @FXML
    private TableColumn<?, ?> columnPeso;
    @FXML
    private TableColumn<?, ?> columnAncho;
    @FXML
    private TableColumn<?, ?> columnAlto;
    @FXML
    private TableColumn<?, ?> columnProfundidad;
    @FXML
    private TableColumn<?, ?> columnDescripcion;
    @FXML
    private TableView<Paquete> tablePaquetes;

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
        columnEnvio.setCellValueFactory(new PropertyValueFactory("envio"));//NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        columnPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        columnAncho.setCellValueFactory(new PropertyValueFactory("ancho"));
        columnAlto.setCellValueFactory(new PropertyValueFactory("alto"));
        columnProfundidad.setCellValueFactory(new PropertyValueFactory("profundidad"));
        columnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));

    }

    private void cargarInformacionTabla() {
        //LAS TABLAS NO SOPORTAN LIST SI NO UN OBSERVABLE LIST-> PORQUE USA UN PATRON DE DISÑEO OBSERVER
        paquetes = FXCollections.observableArrayList();
        List<Paquete> listaWS = PaqueteDAO.obtenerPaquetes();
        if (listaWS != null) {
            paquetes.addAll(listaWS);
            tablePaquetes.setItems(paquetes);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los paquetes, intentelo más tarde", Alert.AlertType.ERROR);

        }

    }

    private void elminarPaquete() {
        Integer idPaquete = tablePaquetes.getSelectionModel().getSelectedItem().getIdPaquete();        
        System.out.println("ID PAQUETE: "+idPaquete);
        Mensaje respuesta = PaqueteDAO.eliminarPaquete(idPaquete);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Eliminación Exitosa", "El paquete ha sido eliminado correctamente", Alert.AlertType.INFORMATION);
            notificarOperacion("Elminacion paquete", "");
        } else {
            Utilidades.mostrarAlerta("Error eliminar", "El paquete no ha sido registrado correctamente", Alert.AlertType.ERROR);

        }
    }

    public void irFormulario(NotificadorOperaciones observador, Paquete paquete) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPaquetesFormulario.fxml"));
            Parent root = loader.load();

            FXMLPaquetesFormularioController controlador = loader.getController();
            controlador.inicializarValores(observador, paquete);

            Stage escenario = new Stage();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Formulario Paquetes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede mostrar formulario de colaboradores, intentelo más tarde", Alert.AlertType.ERROR);
        }
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
    private void cargarVsitaEnvios(ActionEvent event) {
        try {
            Stage stageActual = (Stage) colaboradoresViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLEnvios.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void notificarOperacion(String tipoOperacioin, String nombre) {
        //7throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Operacion: " + tipoOperacioin);
        cargarInformacionTabla();
    }

    @FXML
    private void cargarVistaPaqeuteEdicion(ActionEvent event) {
        Paquete paquete = tablePaquetes.getSelectionModel().getSelectedItem();
        irFormulario(this, paquete);
    }

    @FXML
    private void cargarVistaPaqueteFormulario(ActionEvent event) {
        irFormulario(this, null);
    }

    @FXML
    private void cargarVistaPaquetes(ActionEvent event) {
    }

    @FXML
    private void btnElminarPaquete(ActionEvent event) {
        elminarPaquete();
        notificarOperacion("Eliminar" ,"Colaborador");
    }

    @FXML
    private void cargarVistaPaqeuteEdicion(MouseEvent event) {
        Paquete paquete = tablePaquetes.getSelectionModel().getSelectedItem();
        irFormulario(this, paquete);
    }

    @FXML
    private void cargarVistaPaqueteFormulario(MouseEvent event) {
        irFormulario(this, null);
    }

}
