/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.EnvioDAO;
import clientetimefastjavafx.modelo.DAO.PaqueteDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Envio;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Paquete;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLPaquetesFormularioController implements Initializable {

    private NotificadorOperaciones observador;
    private ObservableList<Envio> envios = FXCollections.observableArrayList();
    private HashSet<Integer> numerosGenerados = new HashSet<>();

    private boolean modoEdicion = false;
    private Paquete paqueteEdicion;
    @FXML
    private TextField tfAncho;
    @FXML
    private TextField tfAlto;
    @FXML
    private TextField tfProdundidad;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private ComboBox<Envio> comboEnvio;
    @FXML
    private Button btnGuardarEnvio;
    @FXML
    private TextField tfPeso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatosCombobox();
        comboEnvio.setValue(envios.get(0));
    }

    public void inicializarValores(NotificadorOperaciones observador, Paquete paqueteEdicion) {
        this.observador = observador;
        this.paqueteEdicion = paqueteEdicion;
        if (paqueteEdicion != null) {
            modoEdicion = true;
            cargarDatosEdicion();
            comboEnvio.setDisable(true);
        }
    }

    private void cargarDatosCombobox() {
        List<Envio> listaWS = EnvioDAO.obtenerEnvios();
        if (listaWS != null) {
            envios.addAll(listaWS);
            comboEnvio.setItems(envios);
        }
    }

    private void cargarDatosEdicion() {
        tfAlto.setText(String.valueOf(paqueteEdicion.getAlto()));
        tfAncho.setText(String.valueOf(paqueteEdicion.getAncho()));
        tfProdundidad.setText(String.valueOf(paqueteEdicion.getProfundidad()));
        tfDescripcion.setText(paqueteEdicion.getDescripcion());
        Integer envio = paqueteEdicion.getEnvio();
        System.out.println("Envio: " + envio);
        comboEnvio.setValue(envios.get(obtenerEnvio(envio)));
    }

    private int obtenerEnvio(Integer idEnvio) {
        int posicion = 0;
        for (int i = 0; i < envios.size(); i++) {
            if (idEnvio == envios.get(i).getIdEnvio()) {
                posicion = i;
            }
        }
        return posicion;
    }

    private void agregarPaquete(Paquete paquete) {
        Mensaje respuesta = PaqueteDAO.agregarPaquete(paquete);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Registro Exitosi", "El paquete ha sido registrado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Registro Paquete", paquete.getNoPaquete().toString());
        } else {
            Utilidades.mostrarAlerta("Error registro de paquete", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarPaquete(Paquete paquete) {
        Mensaje respuesta = PaqueteDAO.editarPaquete(paquete);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Edicion Exitosa", "El paquete ha sido editado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Edicion paquete", paquete.getNoPaquete().toString());
        } else {
            Utilidades.mostrarAlerta("Error edición de paquete", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) comboEnvio.getScene().getWindow();
        escenarioPrincipal.close();
    }

    private Integer generarNumeroAleatorio() {
        Random random = new Random();
        Integer numero;

        do {
            numero = 1000 + random.nextInt(9000); // Número aleatorio entre 1000 y 9999
        } while (numerosGenerados.contains(numero));

        numerosGenerados.add(numero);

        return numero;
    }

    private boolean camposNoVacios() {
        boolean valido = true;
        if (tfAlto.getText().isEmpty()) {
            valido = false;
        }

        if (tfAncho.getText().isEmpty()) {
            valido = false;
        }

        if (tfDescripcion.getText().isEmpty()) {
            valido = false;
        }

        if (tfProdundidad.getText().isEmpty()) {
            valido = false;
        }
        return valido;
    }

    private boolean validarCampos() {
        // Regex para un número flotante con hasta un decimal
        String regex = "^[0-9]+(?:\\.[0-9])?$";

        String alto = tfAlto.getText();
        String ancho = tfAncho.getText();
        String profundidad = tfProdundidad.getText();
        String peso = tfPeso.getText();

        if (!alto.matches(regex)) {

            return false;
        }
        if (!ancho.matches(regex)) {
            return false;
        }
        if (!profundidad.matches(regex)) {

            return false;
        }
        if (!peso.matches(regex)) {
            return false;
        }

        return true;
    }

    @FXML
    private void guardarEnvio(ActionEvent event) {

        if (camposNoVacios()) {
            if (validarCampos()) {
                Integer noPaquete = generarNumeroAleatorio();
                String descripcion = tfDescripcion.getText();
                Float peso = Float.parseFloat(tfPeso.getText());
                Float alto = Float.parseFloat(tfAlto.getText());
                Float ancho = Float.parseFloat(tfAncho.getText());
                Float profundidad = Float.parseFloat(tfProdundidad.getText());
                Integer envioAsignado = comboEnvio.getSelectionModel().getSelectedItem().getIdEnvio();
                Paquete paquete = new Paquete(0, noPaquete, descripcion, peso, alto, ancho, profundidad, envioAsignado);

                if (!modoEdicion) {
                    agregarPaquete(paquete);
                } else {
                    Integer idPaquete = this.paqueteEdicion.getIdPaquete();
                    Paquete paqueteEditar = new Paquete(idPaquete, noPaquete, descripcion, peso, alto, ancho, profundidad, envioAsignado);
                    editarPaquete(paqueteEditar);
                }
            } else {
                Utilidades.mostrarAlerta("Error guardar paquete", "Campos invalidos", Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlerta("Error al guardar el paquete", "Campos incompletos", Alert.AlertType.ERROR);

        }

    }

}
