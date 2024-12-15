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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLClientesFormularioController implements Initializable {

    private NotificadorOperaciones observador;
    private Cliente clienteEdicion;
    private boolean modoEdicion = false;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumExterior;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Button btnGuardarCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarValores(NotificadorOperaciones observador, Cliente clienteEdicion) {
        this.observador = observador;
        this.clienteEdicion = clienteEdicion;
        if (clienteEdicion != null) {
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }

    private void agregarCliente(Cliente cliente) {
        Mensaje respuesta = ClienteDAO.agregarCliente(cliente);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Registro Exitoso", "El Cliente ha sido registrado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Nuevo registro", cliente.getNombre());
        } else {
            Utilidades.mostrarAlerta("Error registro", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    private void editarDatosColaborador(Cliente cliente){
        Mensaje respuesta = ClienteDAO.editarCliente(cliente);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Edicitón Exitosa", "El Cliente ha sido registrao correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Edición registros", cliente.getNombre());
        } else {
            Utilidades.mostrarAlerta("Error Edición", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }   
    }
    
    private void cargarDatosEdicion(){
    tfNombre.setText(clienteEdicion.getNombre());
    tfApellidoMaterno.setText(clienteEdicion.getApellidoMaterno());
    tfApellidoPaterno.setText(clienteEdicion.getApellidoPaterno());
    tfCalle.setText(clienteEdicion.getCalle());
    tfNumExterior.setText(clienteEdicion.getNumero().toString());
    tfColonia.setText(clienteEdicion.getColonia());
    tfCodigoPostal.setText(clienteEdicion.getCp());
    tfTelefono.setText(clienteEdicion.getTelefono());
    tfCorreo.setText(clienteEdicion.getCorreo());    
    }

    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) btnGuardarCliente.getScene().getWindow();
        escenarioPrincipal.close();
    }

    @FXML
    private void guardarCliente(ActionEvent event) {
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String telefono = tfTelefono.getText();
        String correo = tfCorreo.getText();
        String codigoPostal = tfCodigoPostal.getText();
        String colonia = tfColonia.getText();
        String calle = tfCalle.getText();
        Integer numExterior = Integer.parseInt(tfNumExterior.getText());
        Cliente clienteRegistrar = new Cliente(0, nombre, apellidoPaterno, apellidoMaterno, calle, numExterior, colonia, codigoPostal, telefono, correo, "");;
        if (!modoEdicion) {
            agregarCliente(clienteRegistrar);
        } else {
            Integer idCliente = this.clienteEdicion.getIdCliente();
            Cliente clienteEditar = new Cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, calle, numExterior, colonia, codigoPostal, telefono, correo, "");;       
            editarDatosColaborador(clienteEditar);
        }
    }

}
