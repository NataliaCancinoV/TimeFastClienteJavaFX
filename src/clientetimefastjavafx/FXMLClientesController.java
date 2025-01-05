/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.ClienteDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Cliente;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Mensaje;
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
public class FXMLClientesController implements Initializable, NotificadorOperaciones {

    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private TableColumn<?, ?> columNombre;
    @FXML
    private TableColumn<?, ?> columnApellidoPaterno;
    @FXML
    private TableColumn<?, ?> columnApellidoMaterno;
    @FXML
    private TableColumn<?, ?> columnTelefono;
    @FXML
    private TableColumn<?, ?> columnCorreo;
    @FXML
    private TableColumn<?, ?> columnDireccion;
    @FXML
    private TableView<Cliente> tableClientes;

    private ObservableList<Cliente> clientes;
    @FXML
    private TextField tfBuscarClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarContenidoTabla();
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
        columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        columnApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
    }

    private void cargarContenidoTabla() {
        clientes = FXCollections.observableArrayList();
        List<Cliente> listaClientesWS = ClienteDAO.obtenerClientes();
        if (listaClientesWS != null) {
            clientes.addAll(listaClientesWS);
            tableClientes.setItems(clientes);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los clientes, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarClientes() {
        String parametro = tfBuscarClientes.getText();
        if (parametro.equals("") || parametro.trim().equals("")) {
            Utilidades.mostrarAlerta("Error busqueda", "Campos de busqueda vacio", Alert.AlertType.INFORMATION);
        } else {
            clientes = FXCollections.observableArrayList();
            List<Cliente> listaWS = ClienteDAO.buscarCliente(parametro);
            if (listaWS != null) {
                clientes.addAll(listaWS);
                tableClientes.setItems(clientes);
            } else {
                Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los clientes, intentelo más tarde", Alert.AlertType.ERROR);
            }
        }

    }

    public void irFormulario(NotificadorOperaciones observador, Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLClientesFormulario.fxml"));
            Parent root = loader.load();

            FXMLClientesFormularioController controlador = loader.getController();
            controlador.inicializarValores(observador, cliente);

            Stage escenario = new Stage();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Formulario Cliente");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede mostrar formulario de colaboradores, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    private void eliminarCliente() {
        Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            Integer idCliente = tableClientes.getSelectionModel().getSelectedItem().getIdCliente();
            Mensaje mensaje = ClienteDAO.eliminarCliente(idCliente);
            if (!mensaje.isError()) {
                Utilidades.mostrarAlerta("Elminación Exitosa", "El Cliente ha sido elminado correctamente", Alert.AlertType.INFORMATION);
                notificarOperacion("Eliminar", "Cliente");
            } else {
                Utilidades.mostrarAlerta("Error en la elminación", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlerta("Error", "Debe seleleccionar un registro para eliminar", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void btnEliminarCliente(ActionEvent event) {
        eliminarCliente();
    }

    @FXML
    private void cargarVistaFormularioEditar(ActionEvent event) {
        Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            irFormulario(this, cliente);
        } else {
            Utilidades.mostrarAlerta("Error edición", "Debe seleccionar un registro para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cargarVistaFormularioClientes(ActionEvent event) {
        irFormulario(this, null);
    }

    @Override
    public void notificarOperacion(String tipoOperacioin, String nombre) {
        System.out.println("Tipo operacion " + tipoOperacioin);
        System.out.println("Nombe del Cliente :" + nombre);
        cargarContenidoTabla();
    }

    @FXML
    private void btnCerrarSesion(MouseEvent event) {
        
    }

    @FXML
    private void btnRefresh(MouseEvent event) {
        cargarContenidoTabla();
    }

}
