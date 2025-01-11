/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.EnvioDAO;
import clientetimefastjavafx.modelo.DAO.UnidadDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Cliente;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Envio;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Unidad;
import clientetimefastjavafx.utilidades.Constantes;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLEnviosFormularioController implements Initializable {

    private boolean modoEdicion = false;
    private Envio envioEdicion;
    private NotificadorOperaciones observador;
    private Colaborador colaboradorInicioSesion = Constantes.colaboradorInicioSesion;

    private ObservableList<String> estatusEnvio = FXCollections.observableArrayList();
    private ObservableList<Colaborador> conductoresEnvio = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    private TextField tfEstado;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNoExterior;
    @FXML
    private TextField tfDestino;
    @FXML
    private TextField tfNoGuia;
    @FXML
    private TextField tfCosto;
    @FXML
    private ComboBox<Colaborador> comboConductor;
    @FXML
    private ComboBox<String> comboEstatus;
    @FXML
    private ComboBox<Cliente> comboCliente;
    @FXML
    private TextArea tfMotivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatosComboEstatus();
        cargarConductores();
        cargarClientes();
        comboConductor.setValue(conductoresEnvio.get(0));
        comboEstatus.setValue(estatusEnvio.get(0));
        comboCliente.setValue(clientes.get(0));
        tfMotivo.setDisable(true);
        tfMotivo.setText("Sin Motivo");
    }

    public void inicializarValores(NotificadorOperaciones observador, Envio envioEdicion) {
        this.observador = observador;
        this.envioEdicion = envioEdicion;
        if (envioEdicion != null) {
            modoEdicion = true;
            tfMotivo.setDisable(false);
            cargarDatosEdicion();
            tfNoGuia.setDisable(true);
        }
    }

    public void setColaboradorSesion(Colaborador colaborador) {

    }

    private void cargarDatosComboEstatus() {
        estatusEnvio.add("Pendiente");
        estatusEnvio.add("En Transito");
        estatusEnvio.add("Cancelado");
        estatusEnvio.add("Entregado");
        estatusEnvio.add("Detenido");
        comboEstatus.setItems(estatusEnvio);
    }

    private void cargarConductores() {
        List<Colaborador> conductores = UnidadDAO.obtenerConductores();
        if (conductores != null) {
            conductoresEnvio.addAll(conductores);
            comboConductor.setItems(conductoresEnvio);
        }
    }

    private void cargarClientes() {
        List<Cliente> clientesWS = EnvioDAO.obtenerClientes();
        if (clientesWS != null) {
            clientes.addAll(clientesWS);
            comboCliente.setItems(clientes);
        }
    }

    private void guardarEnvioFormulario(Envio envio) {
        Mensaje respuesta = EnvioDAO.agregarEnvio(envio);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Registro Exitoso", "El envio ha sido editador correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Edicion Registros", "");
        } else {
            Utilidades.mostrarAlerta("Error en registro", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cargarDatosEdicion() {
        tfEstado.setText(envioEdicion.getEstado());
        tfCiudad.setText(envioEdicion.getCiudad());
        tfDestino.setText(envioEdicion.getDestino());
        tfCodigoPostal.setText(envioEdicion.getCp());
        tfColonia.setText(envioEdicion.getColonia());
        tfNoGuia.setText(envioEdicion.getNoGuia().toString());
        tfCalle.setText(envioEdicion.getCalle());
        tfNoExterior.setText(envioEdicion.getNumero().toString());
        tfCosto.setText(envioEdicion.getCosto().toString());

        int posicionConductor = envioEdicion.getIdConductor();
        int posicionCliente = envioEdicion.getIdCliente();
        String estatusActual = envioEdicion.getEstatus();

        comboCliente.setValue(clientes.get(obtenerCliente(posicionCliente)));
        comboConductor.setValue(conductoresEnvio.get(obtenerConductor(posicionConductor)));
        comboEstatus.setValue(estatusEnvio.get(obtenerEstatus(estatusActual)));

    }

    private int obtenerConductor(int idConductor) {
        int posicion = 0;
        for (int i = 0; i < conductoresEnvio.size(); i++) {
            if (idConductor == conductoresEnvio.get(i).getIdColaborador()) {
                posicion = i;
            }
        }
        return posicion;
    }

    private int obtenerEstatus(String estatus) {
        int posicion = 0;
        for (int i = 0; i < estatusEnvio.size(); i++) {
            if (estatus.equals(estatusEnvio.get(i).toString())) {
                posicion = i;
            }
        }
        return posicion;
    }

    private int obtenerCliente(int idCliente) {
        int posicion = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (idCliente == clientes.get(i).getIdCliente()) {
                posicion = i;
            }
        }
        return posicion;
    }

    private void editarDatosEnvio(Envio envio) {
        try {
            String motivo = tfMotivo.getText();
            String envioCodificado = URLEncoder.encode(motivo, "UTF-8");
            envio.setMotivo(envioCodificado);
            Mensaje mensaje = EnvioDAO.editarEnvio(envio);
            if (!mensaje.isError()) {
                Utilidades.mostrarAlerta("Edición Exitosa", "El envio ha sido editador correctamente", Alert.AlertType.INFORMATION);
                cerrarVentana();
                observador.notificarOperacion("Edicion envio", envio.getIdEnvio().toString());
            } else {
                Utilidades.mostrarAlerta("Error edicion", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            System.out.println("Errir la editar envio : " + e.toString());
        }

    }

    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) comboConductor.getScene().getWindow();
        escenarioPrincipal.close();
    }

    @FXML
    private void guardarEnvio(ActionEvent event) {
        String estado = tfEstado.getText();
        String ciudad = tfCiudad.getText();
        String destino = tfDestino.getText();
        String codigoPostal = tfCodigoPostal.getText();
        String colonia = tfColonia.getText();
        Integer noGuia = Integer.parseInt(tfNoGuia.getText());
        String calle = tfCalle.getText();
        Integer noExterior = Integer.parseInt(tfNoExterior.getText().trim());
        Float costoEnvio = Float.parseFloat(tfCosto.getText().trim());
        Integer idConductor = comboConductor.getSelectionModel().getSelectedItem().getIdColaborador();
        String estatus = comboEstatus.getSelectionModel().getSelectedItem();
        Integer idCliente = comboCliente.getSelectionModel().getSelectedItem().getIdCliente();

        Envio envio = new Envio(0, idCliente, calle, noExterior, colonia, codigoPostal, ciudad, estado, destino, noGuia, costoEnvio, estatus, "", idConductor, "", 0, "", 0);

        if (!modoEdicion) {
            guardarEnvioFormulario(envio);
        } else {
            Integer idEnvio = this.envioEdicion.getIdEnvio();
            Envio envioEdicion = new Envio(idEnvio, idCliente, calle, noExterior, colonia, codigoPostal, ciudad, estado, destino, noGuia, costoEnvio, estatus, tfMotivo.getText(), idConductor, "", 0, "", colaboradorInicioSesion.getIdColaborador());
            editarDatosEnvio(envioEdicion);
        }
    }

    @FXML
    private void btnCancelar(MouseEvent event) {
        Stage stagaActual = (Stage) comboCliente.getScene().getWindow();
        stagaActual.close();
        Utilidades.mostrarAlerta("Alerta", "Cambios cancelados", Alert.AlertType.INFORMATION);
    }

}
