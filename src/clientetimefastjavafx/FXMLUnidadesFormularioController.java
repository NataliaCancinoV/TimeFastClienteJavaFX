/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.UnidadDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Unidad;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLUnidadesFormularioController implements Initializable {

    private NotificadorOperaciones observador;
    private Unidad unidadEdicion;
    private boolean modoEdicion;

    private ObservableList<String> tipoUnidad = FXCollections.observableArrayList();
    private ObservableList<Colaborador> conductores = FXCollections.observableArrayList();

    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfiDInterno;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private ComboBox<String> comboTipoUnidad;
    @FXML
    private ComboBox<Colaborador> comboConductor;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarComboTipoUnidad();
        cargarComboConductores();
        comboTipoUnidad.setValue(tipoUnidad.get(0));
        tfiDInterno.setEditable(false);
    }

    public void inicializarValores(NotificadorOperaciones observador, Unidad unidadEdicion) {
        this.observador = observador;
        this.unidadEdicion = unidadEdicion;
        tfiDInterno.setDisable(true);
        if (unidadEdicion != null) {
            modoEdicion = true;
            tfVin.setDisable(true);
            tfiDInterno.setDisable(true);
            cargarDatosEdicion();
            comboConductor.setValue(null);
        }
    }

    private void cargarComboConductores() {
        List<Colaborador> conductoresWS = UnidadDAO.obtenerConductores();
        if (conductoresWS != null) {
            conductores.addAll(conductoresWS);            
            comboConductor.setItems(conductores);
        }        
    }

    private void cargarComboTipoUnidad() {
        tipoUnidad.add("Gasolina");
        tipoUnidad.add("Diesel");
        tipoUnidad.add("Electrica");
        tipoUnidad.add("Hibrida");
        comboTipoUnidad.setItems(tipoUnidad);
    }

    private void agregarUnidad(Unidad unidad) {
        Mensaje respuesta = UnidadDAO.agregarUnidad(unidad);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Registro Exitoso", "La unidad ha sido registrado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Nuevo registro", unidad.getMarca());
        } else {
            Utilidades.mostrarAlerta("Error registro", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cargarDatosEdicion() {
        tfVin.setText(unidadEdicion.getVin().toString());
        tfiDInterno.setText(unidadEdicion.getNii().toString());
        tfMarca.setText(unidadEdicion.getMarca());
        tfModelo.setText(unidadEdicion.getModelo());
        tfAnio.setText(unidadEdicion.getAnio());
        String posicionUnidad = unidadEdicion.getTipoUnidad();
        int posicionConductor = unidadEdicion.getIdConductor();
        int posUnidad = obtenerTipoUnidad(posicionUnidad);
        System.out.println("LA POSICION DE  "+posicionUnidad+" ES: "+posUnidad);
        //comboTipoUnidad.setValue(tipoUnidad.get(valorCombo));
        comboTipoUnidad.setValue(tipoUnidad.get(obtenerTipoUnidad(posicionUnidad)));
        comboConductor.setValue(conductores.get(obtenerConductor(posicionConductor)));

    }

    private Integer obtenerConductor(Integer idConductor) {
        int posicion = 0;
        for (int i = 0; i < conductores.size(); i++) {
            if (idConductor == conductores.get(i).getIdColaborador()) {
                posicion = i;
            }
        }
        return posicion;
    }

    private int obtenerTipoUnidad(String unidad) {
        int posicion = 0;
        System.out.println("OBTENER UNIDAD: " + unidad);
        for (int i = 0; i < tipoUnidad.size(); i++) {
            System.out.println("COMPARANDO " + unidad + " CON " + tipoUnidad.get(i));
            if (unidad.trim().equals(tipoUnidad.get(i).trim())) {
                posicion = i;
            }
        }
        return posicion;
    }

    private void editarUnidad(Unidad unidad) {
        Mensaje respuesta = UnidadDAO.editarUnidad(unidad);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Edición Exitosa", "La unidad ha sido editada correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Nueva edición", unidad.getMarca());
        } else {
            Utilidades.mostrarAlerta("Error en edición", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) comboConductor.getScene().getWindow();
        escenarioPrincipal.close();
    }

    private Integer obtenerNII(String anio, String vin) {
        char caracter1 = vin.charAt(0);
        char caracter2 = vin.charAt(1);
        char caracter3 = vin.charAt(2);
        char caracter4 = vin.charAt(3);
        String nii = anio + caracter1 + caracter2 + caracter3 + caracter4;
        return Integer.parseInt(nii);
    }

    private boolean datosNoVacios() {
        boolean valido = true;
        if (tfVin.getText().isEmpty()) {
            valido = false;
        }
        if (tfMarca.getText().isEmpty()) {
            valido = false;
        }
        if (tfModelo.getText().isEmpty()) {
            valido = false;
        }
        if (tfAnio.getText().trim().isEmpty()) {
            valido = false;
        }
        return valido;
    }

    private boolean datosValidos(String marca, String modelo, String año, String vin) {

        String regexMarcaModelo = "^[a-zA-Z0-9\\s\\-]+$";
        String regexAño = "^(19|20)\\d{2}$";
        String regexVIN = "^\\d{1,5}$";

        if (!marca.matches(regexMarcaModelo)) {
            System.out.println("Marca inválida");
            return false;
        }

        if (!modelo.matches(regexMarcaModelo)) {
            System.out.println("Modelo inválido");
            return false;
        }

        if (!año.matches(regexAño)) {
            System.out.println("Año inválido");
            return false;
        }

        if (!vin.matches(regexVIN)) {
            System.out.println("VIN inválido");
            return false;
        }

        return true;
    }

    @FXML
    private void guardarUnidad(ActionEvent event) {
        Colaborador conductor = comboConductor.getSelectionModel().getSelectedItem();
        Integer idConductor = 0;
        if (conductor != null) {
            idConductor = conductor.getIdColaborador();
        }
        Integer vin = Integer.parseInt(tfVin.getText().trim());
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String anio = tfAnio.getText().trim();
        String tipoUnidad = comboTipoUnidad.getSelectionModel().getSelectedItem();
        //  String nombreConductor = comboConductor.getSelectionModel().getSelectedItem().getNombre();
        Integer nii = obtenerNII(anio, tfVin.getText());

        System.out.println(" " + vin + " " + marca + " " + modelo + " " + anio + " " + nii + " " + tipoUnidad + " " + idConductor);
        if (datosNoVacios()) {
            if (datosValidos(marca, modelo, anio, anio)) {
                Unidad unidad = new Unidad(0, marca, modelo, anio, vin, tipoUnidad, nii, idConductor, "");
                if (!modoEdicion) {
                    agregarUnidad(unidad);
                } else {
                    Integer idUnidad = unidadEdicion.getIdUnidad();
                    Unidad unidadEdicion = new Unidad(idUnidad, marca, modelo, anio, vin, tipoUnidad, nii, idConductor, "");
                    editarUnidad(unidadEdicion);
                }
            } else {
                Utilidades.mostrarAlerta("Error al guardar datos", "Formato de datos invalidos", Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlerta("Error al guardar datos", "Campos Incompletos", Alert.AlertType.ERROR);
        }

    }

}
